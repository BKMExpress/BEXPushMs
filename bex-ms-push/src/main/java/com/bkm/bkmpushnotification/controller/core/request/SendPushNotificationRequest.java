package com.bkm.bkmpushnotification.controller.core.request;

import com.bkm.bkmpushnotification.core.domain.PushNotificationInfo;
import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * Created by Akif Hatipoglu on 19.02.2018.
 */
public class SendPushNotificationRequest {

    @Expose
    private String applicationId;
    @Expose
    private String pushToken;
    @Expose
    private int deviceType;
    @Expose
    private String priority;
    @Expose
    private int pushType;
    @Expose
    private int pushSubType;
    @Expose
    private PushNotificationInfo pushNotificationInfo;
    @Expose(serialize = false)
    private HashMap<String,String> data;
    @Expose
    private boolean silent;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public int getPushSubType() {
        return pushSubType;
    }

    public void setPushSubType(int pushSubType) {
        this.pushSubType = pushSubType;
    }

    public PushNotificationInfo getPushNotificationInfo() {
        return pushNotificationInfo;
    }

    public void setPushNotificationInfo(PushNotificationInfo pushNotificationInfo) {
        this.pushNotificationInfo = pushNotificationInfo;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }
}
