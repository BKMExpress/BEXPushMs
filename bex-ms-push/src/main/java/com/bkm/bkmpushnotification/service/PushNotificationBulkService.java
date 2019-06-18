package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.controller.core.request.ReceivePushReadRequest;
import com.bkm.bkmpushnotification.controller.core.request.SendBulkPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.request.SendPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.response.SendBulkPushNotificationResponse;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
public interface PushNotificationBulkService {

    SendBulkPushNotificationResponse sendBulkPushNotification(SendBulkPushNotificationRequest request);
}
