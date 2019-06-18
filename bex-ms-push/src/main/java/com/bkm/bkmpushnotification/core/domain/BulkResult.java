package com.bkm.bkmpushnotification.core.domain;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;

/**
 * (C) Copyright
 * Created by Akif Hatipoglu on 11.03.2019.
 */
public class BulkResult {

    private String token;
    private String pushRecordId;
    private boolean success;

    public BulkResult(String token, String pushRecordId, boolean success) {
        this.token = token;
        this.pushRecordId = pushRecordId;
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPushRecordId() {
        return pushRecordId;
    }

    public void setPushRecordId(String pushRecordId) {
        this.pushRecordId = pushRecordId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
