package com.bkm.bkmpushnotification.controller.core.request;

import com.google.gson.annotations.Expose;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
public class ReceivePushReadRequest {

    @Expose
    private String pushLogId;

    public String getPushLogId() {
        return pushLogId;
    }

    public void setPushLogId(String pushLogId) {
        this.pushLogId = pushLogId;
    }
}
