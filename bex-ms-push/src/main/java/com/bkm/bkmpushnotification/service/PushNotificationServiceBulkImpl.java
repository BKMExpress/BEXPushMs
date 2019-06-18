package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.controller.core.request.SendBulkPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.response.SendBulkPushNotificationResponse;
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
@Service(value = "PushNotificationBulkService")
public class PushNotificationServiceBulkImpl implements PushNotificationBulkService {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationServiceBulkImpl.class);

    @Autowired
    PlatformPropertyService platformPropertyService;

    @Autowired
    AllowedTokenDao allowedTokenDao;

    @Autowired
    PushLogDao pushLogDao;

    @Override
    public SendBulkPushNotificationResponse sendBulkPushNotification(SendBulkPushNotificationRequest request) {
        SendBulkPushNotificationResponse response = new SendBulkPushNotificationResponse();

        if (!platformPropertyService.isFirebaseIsOn()) {
            response.setReturnCode(ReturnCode.FIREBASE_IS_OFFLINE);
            return response;
        }

        Optional<PushAppUser> appUser = platformPropertyService.getAppUserProperty(request.getApplicationId());
        Optional<String> optionalPushUrl = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_PUSH_URL);
        final String pushUrl = optionalPushUrl.get();
        final int appId = appUser.get().getId();
        final String apiKey = appUser.get().getAppKey();
        final String bundleName = appUser.get().getAppBundle();
        final boolean testEnvironment = platformPropertyService.isTestEnvironment();

        List<BulkResult> bulkResultList = new ArrayList<>();
        request.getPushTokenDeviceList().forEach(tokenDevice -> {
            logger.info("-------------------------------------------------- "+ tokenDevice.getDeviceType()+" ntoken:" + tokenDevice.getPushToken());
            if (testEnvironment && !allowedTokenDao.findByToken(tokenDevice.getPushToken()).isPresent()) {
                logger.info("Allowed Token Not Found For This Environment: " + tokenDevice.getPushToken());
                bulkResultList.add(new BulkResult(tokenDevice.getPushToken(), tokenDevice.getPushRecordId(), false));
                return; // only skips this iteration.
            }
            MobileDeviceType deviceType = MobileDeviceType.getForBulk(tokenDevice.getDeviceType());

            if (callFirebasePushService(request, appId, apiKey, pushUrl, tokenDevice, deviceType, bundleName).isStatus()) {
                bulkResultList.add(new BulkResult(tokenDevice.getPushToken(), tokenDevice.getPushRecordId(), true));
            } else {
                bulkResultList.add(new BulkResult(tokenDevice.getPushToken(), tokenDevice.getPushRecordId(), false));
            }
        });
        response.setReturnCode(ReturnCode.SUCCESS);
        response.setBulkResultList(bulkResultList);
        return response;
    }

    private Optional<String> connectToFirebaseServerForGetIOSToken(TokenDevice request, String apiKey, String bundleName) {
        try {
            logger.info("connect to firebase server for get ios token");

            if (request.getPushToken().length() > 64) { // kýsa uzunluktaki eski apple tokenlarý için apple servisleri çaðrýlýr.
                logger.info("token length > 64");
                return Optional.ofNullable(request.getPushToken());
            }

            //properties
            Optional<String> optionalSandboxMode = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_SANDBOX_MODE);
            final Boolean sandboxMode = optionalSandboxMode.isPresent() ? Boolean.parseBoolean(optionalSandboxMode.get()) : false;
            Optional<String> pushUrlForIos = platformPropertyService.getPlatformProperty(BkmConstants.FIREBASE_GET_IOS_TOKEN);
            final String pushUrl = pushUrlForIos.isPresent() ? pushUrlForIos.get() : "";

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

    private BkmBaseRestResponse callFirebasePushService(SendBulkPushNotificationRequest request, int appId, String apiKey, String pushUrl, TokenDevice tokenDevice, MobileDeviceType deviceType, String bundleName) {
        long id = 0;
        try {
            logger.info("call Firebase Push Service method.");
            if (CollectionUtils.isEmpty(request.getData()))
                request.setData(new HashMap<>());

            // Call Rest Service
            Date dt1 = new Date();
            Object requestObject;

            // Init Push Request
            if (deviceType.equals(MobileDeviceType.IPHONE)) {
                IOSRequest iosRequest = new IOSRequest();
                Optional<String> token = connectToFirebaseServerForGetIOSToken(tokenDevice, apiKey, bundleName);
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

                id = pushLogDao.insert(appId, tokenDevice.getPushToken(), BkmUtil.getJsonString(iosRequest), request.getPushType(), request.getPushSubType(), deviceType.getTypeCode(), tokenDevice.getPushRecordId(), 1);
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
                pushNotificationRequest.setTo(tokenDevice.getPushToken());
                pushNotificationRequest.setPriority(request.getPriority());

                id = pushLogDao.insert(appId, tokenDevice.getPushToken(), BkmUtil.getJsonString(pushNotificationRequest), request.getPushType(), request.getPushSubType(), tokenDevice.getDeviceType(), tokenDevice.getPushRecordId(), 1);
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
                return new BkmBaseRestResponse();
            } else {
                logger.error("firebase cloud messaging service fail");
                String failedReason = "firebase pushService failed";
                pushLogDao.updateSendInfoForBulkById(id);
                if (pushNotificationResponse != null && pushNotificationResponse.getSuccess() != null && pushNotificationResponse.getSuccess().equals("0")) {
                    List<PushError> errors = pushNotificationResponse.getResults();
                    if (errors != null && errors.size() > 0) {
                        for (PushError error : errors) {
                            failedReason = failedReason + "," + error.getError() + " * ";
                        }
                    }
                }
                logger.error("firebase cloud messaging service fail : " + failedReason);
                return new BkmBaseRestResponse(ReturnCode.PUSH_SERVICE_SENDING_EXCEPTION, failedReason);
            }
        } catch (Exception ex) {
            if (id != 0) {
                pushLogDao.updateSendInfoForBulkById(id);
            }
            logger.error("firebase push service exception : " + ExceptionUtils.getStackTrace(ex));
            return new BkmBaseRestResponse(ReturnCode.PUSH_SERVICE_SENDING_EXCEPTION, ExceptionUtils.getStackTrace(ex));
        }
    }
}
