package com.bkm.bkmpushnotification.controller.core.request;

/**
 * Created by Akif Hatipoglu on 20.04.2018.
 */
public class GetPushRecordsRequest {

    private String pushMasterId;

    public String getPushMasterId() {
        return pushMasterId;
    }

    public void setPushMasterId(String pushMasterId) {
        this.pushMasterId = pushMasterId;
    }
}
