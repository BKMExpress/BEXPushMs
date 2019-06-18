package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.controller.core.request.SendPushNotificationRequest;
import com.bkm.bkmpushnotification.controller.core.request.ReceivePushReadRequest;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
public interface PushNotificationService {

    BkmBaseRestResponse sendPushNotification(SendPushNotificationRequest request);

    BkmBaseRestResponse receivePushRead(ReceivePushReadRequest request);
}
