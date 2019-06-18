package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.controller.core.request.SendPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.request.ReceivePushReadRequest;
import com.bkm.bkmpushnotification.core.dao.AllowedTokenDao;
import com.bkm.bkmpushnotification.core.dao.PushLogDao;
import com.bkm.bkmpushnotification.core.domain.*;
import com.bkm.bkmpushnotification.core.enums.MobileDeviceType;
import com.bkm.bkmpushnotification.core.enums.ReturnCode;
import com.bkm.bkmpushnotification.utility.BkmConstants;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
@Service(value = "PushNotificationService")
public class PushNotificationServiceImpl implements PushNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

    @Autowired
    PlatformPropertyService platformPropertyService;

    @Autowired
    AllowedTokenDao allowedTokenDao;

    @Autowired
    PushLogDao pushLogDao;

    @Override
    public BkmBaseRestResponse sendPushNotification(SendPushNotificationRequest request) {

        if (!platformPropertyService.isFirebaseIsOn()) {
            return new BkmBaseRestResponse(ReturnCode.FIREBASE_IS_OFFLINE);
        }

        if (platformPropertyService.isTestEnvironment() && !allowedTokenDao.findByToken(request.getPushToken()).isPresent()) {
            return new BkmBaseRestResponse(ReturnCode.ALLOWED_TOKEN_NOT_FOUND);
        }

        Optional<String> token = Optional.of(request.getPushToken());
        Optional<PushAppUser> appUser = platformPropertyService.getAppUserProperty(request.getApplicationId());
        Optional<String> optionalPushUrl = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_PUSH_URL);
        String pushUrl = optionalPushUrl.get();
        int appId = appUser.get().getId();
        String apiKey = appUser.get().getAppKey();
        String bundleName = appUser.get().getAppBundle();

        MobileDeviceType deviceType = MobileDeviceType.get(request.getDeviceType());

        if (request.getApplicationId().equals(BkmConstants.BKM_EXPRESS_APPLICATION_USER_ID)) {
            return callNonGenericFirebasePushService(request, appId, apiKey, token.get(), pushUrl, request.getPushToken(),deviceType, bundleName);
        }
        return callGenericFirebasePushService(request, appId, apiKey, token.get(), pushUrl, request.getPushToken());
    }

    @Override
    public BkmBaseRestResponse receivePushRead(ReceivePushReadRequest request) {
        int affectedRowCount = pushLogDao.updateShowInfoById(Long.parseLong(request.getPushLogId()));
        if (affectedRowCount == 0) {
            return new BkmBaseRestResponse(ReturnCode.SHOW_INFO_NOT_UPDATED);
        }
        return new BkmBaseRestResponse();
    }

    private Optional<String> connectToFirebaseServerForGetIOSToken(SendPushNotificationRequest request, String apiKey, String bundleName) {
        try {
            logger.info("connect to firebase server for get ios token");

            if(request.getPushToken().length()>64){ // kýsa uzunluktaki tokenlar eski tokanler eski servis çaðýrýlýr.
                logger.info("token length > 64");
                return Optional.ofNullable(request.getPushToken());
            }

            //properties
            Optional<String> optionalSandboxMode = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_SANDBOX_MODE);
            Boolean sandboxMode = optionalSandboxMode.isPresent() ? Boolean.parseBoolean(optionalSandboxMode.get()) : false;
            Optional<String> pushUrlForIos = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_GET_IOS_TOKEN);
            String pushUrl = pushUrlForIos.isPresent() ? pushUrlForIos.get() : "";

            // Init Push Request
            IOSTokenRequest iosTokenRequest = new IOSTokenRequest();
            iosTokenRequest.setApplication(bundleName);
            iosTokenRequest.setSandbox(sandboxMode);

            List<String> apns_tokens = new ArrayList<String>();
            apns_tokens.add(request.getPushToken());
            iosTokenRequest.setApns_tokens(apns_tokens);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> httpEntity = BkmUtil.getEntityForRestTemplate(iosTokenRequest, apiKey);

            logger.info("send request to firebase server for get ios token");
            IOSTokenResponse iosTokenResponse = restTemplate.postForObject(pushUrl, httpEntity, IOSTokenResponse.class);
            if (iosTokenResponse != null && iosTokenResponse.getResults() != null && iosTokenResponse.getResults().size() > 0) {
                IOSTokenResult iosTokenResult = iosTokenResponse.getResults().get(0);
                if (iosTokenResult.getStatus() != null && iosTokenResult.getStatus().equals("OK")) {
                    logger.info("firebase push service get ios token service successfully working");
                    return Optional.ofNullable(iosTokenResult.getRegistration_token());
                } else {
                    logger.error("firebase push service get ios token service failed : " + iosTokenResult.getStatus());
                    return Optional.empty();
                }
            } else {
                logger.error("firebase push service get ios token service response not valid");
                return Optional.empty();
            }
        } catch (Exception ex) {
            logger.error("firebase get ios token push service exception : " + ExceptionUtils.getStackTrace(ex));
        }
        return Optional.empty();
    }

    private BkmBaseRestResponse callNonGenericFirebasePushService(SendPushNotificationRequest request, int appId, String apiKey, String selectedToken, String pushUrl, String incomingToken, MobileDeviceType deviceType, String bundleName) {
        try {
            logger.info("call Firebase Push Service method.");
            if (CollectionUtils.isEmpty(request.getData()))
                request.setData(new HashMap<>());

            // Call Rest Service
            Date dt1 = new Date();
            long id = 0;
            Object requestObject;

            // Init Push Request
            if (deviceType.equals(MobileDeviceType.IPHONE)) {
                IOSRequest iosRequest = new IOSRequest();
                Optional<String> token = connectToFirebaseServerForGetIOSToken(request, apiKey, bundleName);
                if (token.isPresent()) {
                    iosRequest.setTo(token.get());
                } else {
                    logger.info("ios token not found");
                    return new BkmBaseRestResponse(ReturnCode.IOS_TOKEN_NOT_FOUND);
                }
                iosRequest.setPriority(request.getPriority());
                if (!request.isSilent()) {
                    iosRequest.setNotification(request.getPushNotificationInfo());
                }

                String pushRecordId = "";
                if (!CollectionUtils.isEmpty(request.getData()) && request.getData().containsKey("pushRecordId")) {
                    pushRecordId = request.getData().get("pushRecordId");
                    request.getData().remove("pushRecordId");
                }
                id = pushLogDao.insert(appId, incomingToken, BkmUtil.getJsonString(iosRequest), request.getPushType(), request.getPushSubType(), request.getDeviceType(), pushRecordId,0);
                request.getData().put("pushId", Long.toString(id));

                if (!CollectionUtils.isEmpty(request.getData())) {
                    BaseIOSPushData baseIOSPushData = new BaseIOSPushData();
                    baseIOSPushData.setPushId(id);
                    baseIOSPushData.setData(request.getData());
                    baseIOSPushData.getData().put("pushId", Long.toString(id));
                    if (request.getData().containsKey("userId")) {
                        baseIOSPushData.setUserId(request.getData().get("userId"));
                    }
                    iosRequest.setData(baseIOSPushData);
                }

                if (!request.isSilent()) {
                    String decodedMessage = new String(Base64.getDecoder().decode(iosRequest.getNotification().getBody()), StandardCharsets.UTF_8);
                    iosRequest.getNotification().setBody(decodedMessage);
                }

                requestObject = iosRequest;
                logger.info("ios request is ready");
            } else {
                PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
                pushNotificationRequest.setTo(selectedToken);
                pushNotificationRequest.setPriority(request.getPriority());


                String pushRecordId = "";
                if (!CollectionUtils.isEmpty(request.getData()) && request.getData().containsKey("pushRecordId")) {
                    pushRecordId = request.getData().get("pushRecordId");
                    request.getData().remove("pushRecordId");
                }
                id = pushLogDao.insert(appId, incomingToken, BkmUtil.getJsonString(pushNotificationRequest), request.getPushType(), request.getPushSubType(), request.getDeviceType(), pushRecordId,0);
                request.getData().put("pushId", Long.toString(id));

                if (!CollectionUtils.isEmpty(request.getData())) {
                    pushNotificationRequest.setData(request.getData());
                }

                requestObject = pushNotificationRequest;
                logger.info("android request is ready");
            }

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity1 = BkmUtil.getEntityForRestTemplate(requestObject, apiKey);

            logger.info("service call to firebase cloud messaging...");
            PushNotificationResponse pushNotificationResponse = restTemplate.postForObject(pushUrl, entity1, PushNotificationResponse.class);
            logger.info("service call to firebase cloud messaging... Time Duration: " + (new Date().getTime() - dt1.getTime()));
            if (pushNotificationResponse != null && pushNotificationResponse.getSuccess() != null && pushNotificationResponse.getSuccess().equals("1")) {
                logger.info("firebase push service successfully sent : " + pushNotificationResponse.getSuccess());
                pushLogDao.updateSendInfoById(id);

            } else {
                logger.error("firebase cloud messaging service fail");
                String failedReason = "firebase pushService failed";
                if (pushNotificationResponse != null && pushNotificationResponse.getSuccess() != null && pushNotificationResponse.getSuccess().equals("0")) {
                    List<PushError> errors = pushNotificationResponse.getResults();
                    if (errors != null && errors.size() > 0) {
                        for (PushError error : errors) {
                            failedReason = failedReason + "," + error.getError() + " * ";
                        }
                    }
                }
                logger.error("firebase cloud messaging service fail : " + failedReason);
            }
        } catch (Exception ex) {
            logger.error("firebase push service exception : " + ExceptionUtils.getStackTrace(ex));
            return new BkmBaseRestResponse(ReturnCode.PUSH_SERVICE_SENDING_EXCEPTION, ExceptionUtils.getStackTrace(ex));
        }
        logger.info("return firebase push service");
        return new BkmBaseRestResponse();
    }

    private BkmBaseRestResponse callGenericFirebasePushService(SendPushNotificationRequest request, int appId, String apiKey, String selectedToken, String pushUrl,String incomingToken) {
        try {
            // Init Push Request
            PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
            pushNotificationRequest.setTo(selectedToken);
            pushNotificationRequest.setPriority(request.getPriority());
            pushNotificationRequest.setNotification(request.getPushNotificationInfo());

            long id = pushLogDao.insert(appId, incomingToken, BkmUtil.getJsonString(pushNotificationRequest), request.getPushType(), request.getPushSubType(), request.getDeviceType(),"",0);

            if (!CollectionUtils.isEmpty(request.getData())) {
                pushNotificationRequest.setData(request.getData());
            }

            // Call Rest Service
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity1 = BkmUtil.getEntityForRestTemplate(pushNotificationRequest, apiKey);

            PushNotificationResponse pushNotificationResponse = restTemplate.postForObject(pushUrl, entity1, PushNotificationResponse.class);

            if (pushNotificationResponse != null && pushNotificationResponse.getSuccess() != null && pushNotificationResponse.getSuccess().equals("1")) {
                logger.info("firebase push service successfully sent : " + pushNotificationResponse.getSuccess());
                pushLogDao.updateSendInfoById(id);

            } else {
                String failedReason = "firebase pushService failed";
                if (pushNotificationResponse != null && pushNotificationResponse.getSuccess() != null && pushNotificationResponse.getSuccess().equals("0")) {
                    List<PushError> errors = pushNotificationResponse.getResults();
                    if (errors != null && errors.size() > 0) {
                        failedReason = failedReason + "," + errors.get(0).getError();
                    }
                }
                logger.error(failedReason);
                return new BkmBaseRestResponse(ReturnCode.PUSH_SERVICE_SENDING_EXCEPTION);
            }
        } catch (Exception ex) {
            logger.error("firebase push service exception : " + ExceptionUtils.getStackTrace(ex));
            return new BkmBaseRestResponse(ReturnCode.PUSH_SERVICE_SENDING_EXCEPTION);
        }
        return new BkmBaseRestResponse();
    }

}
