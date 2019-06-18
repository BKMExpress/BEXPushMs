package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * (C) Copyright
 * Created by Akif Hatipoglu on 11.03.2019.
 */
public class TokenDevice {

    @Expose
    private String pushToken;
    @Expose
    private int deviceType;
    @Expose
    private String pushRecordId;

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getPushRecordId() {
        return pushRecordId;
    }

    public void setPushRecordId(String pushRecordId) {
        this.pushRecordId = pushRecordId;
    }
}
