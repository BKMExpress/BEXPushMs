package com.bkm.bkmpushnotification.controller;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.controller.core.request.SendBulkPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.request.SendPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.request.ReceivePushReadRequest;
import com.bkm.bkmpushnotification.controller.core.response.SendBulkPushNotificationResponse;
import com.bkm.bkmpushnotification.core.enums.MobileDeviceType;
import com.bkm.bkmpushnotification.core.enums.ReturnCode;
import com.bkm.bkmpushnotification.service.PlatformPropertyService;
import com.bkm.bkmpushnotification.service.PushNotificationBulkService;
import com.bkm.bkmpushnotification.service.PushNotificationService;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Akif Hatipoglu on 24.01.2018.
 */
@RestController
@RequestMapping("/rest")
public class PushNotificationController {

    private static final Logger logger = LoggerFactory.getLogger(PushNotificationController.class);

    @Autowired
    PushNotificationService pushNotificationService;

    @Autowired
    PushNotificationBulkService pushNotificationBulkService;

    @Autowired
    PlatformPropertyService platformPropertyService;

    @RequestMapping(value = {"/sendPushNotification.do"},method = RequestMethod.POST)
    public @ResponseBody BkmBaseRestResponse sendPushNotification(@RequestBody SendPushNotificationRequest request) {
        logger.info("post request for sendPushNotification.do");

        BkmBaseRestResponse validateResponse = validateRequest(request);
        if (!validateResponse.isStatus()) {
            return validateResponse;
        }

        return pushNotificationService.sendPushNotification(request);
    }

    @RequestMapping(value = {"/sendBulkPushNotification.do"},method = RequestMethod.POST)
    public @ResponseBody
    SendBulkPushNotificationResponse sendBulkPushNotification(@RequestBody SendBulkPushNotificationRequest request) {
        logger.info("post request for sendBulkPushNotification.do");
        return pushNotificationBulkService.sendBulkPushNotification(request);
    }

    @RequestMapping(value = {"/receivePushRead.do"},method = RequestMethod.POST)
    public @ResponseBody BkmBaseRestResponse receivePushRead(@RequestBody ReceivePushReadRequest request){
        logger.info("post request for receivePushRead.do");

        BkmBaseRestResponse validateResponse = validateRequest(request);
        if (!validateResponse.isStatus()) {
            return validateResponse;
        }

        return pushNotificationService.receivePushRead(request);
    }

    private BkmBaseRestResponse validateRequest(SendPushNotificationRequest request) {
        if (BkmUtil.isNullOrEmptyString(request.getPushToken())) {
            return new BkmBaseRestResponse(ReturnCode.PUSH_TOKEN_NOT_FOUND);
        } else if (!MobileDeviceType.isExist(request.getDeviceType())) {
            return new BkmBaseRestResponse(ReturnCode.DEVICE_TYPE_NOT_FOUND);
        } else if (!platformPropertyService.getPushTypeProperty(request.getPushType()).isPresent()) {
            return new BkmBaseRestResponse(ReturnCode.PUSH_TYPE_NOT_FOUND);
        } else if (!platformPropertyService.getPushSubTypeProperty(request.getPushSubType()).isPresent()) {
            return new BkmBaseRestResponse(ReturnCode.PUSH_SUBTYPE_NOT_FOUND);
        } else if (!platformPropertyService.getAppUserProperty(request.getApplicationId()).isPresent()) {
            return new BkmBaseRestResponse(ReturnCode.PUSH_APPLICATION_ID_NOT_FOUND);
        }
        return new BkmBaseRestResponse();
    }

    private BkmBaseRestResponse validateRequest(ReceivePushReadRequest request){
        if(BkmUtil.isNullOrEmptyString(request.getPushLogId())){
            return new BkmBaseRestResponse(ReturnCode.LOG_ID_NOT_FOUND);
        }
        return new BkmBaseRestResponse();
    }
}
