package com.bkm.bkmpushnotification.controller.core.request;

import com.bkm.bkmpushnotification.core.domain.PushNotificationInfo;
import com.bkm.bkmpushnotification.core.domain.TokenDevice;
import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 19.02.2018.
 */
public class SendBulkPushNotificationRequest {

    @Expose
    private String applicationId;
    @Expose
    private List<TokenDevice> pushTokenDeviceList;
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

    public List<TokenDevice> getPushTokenDeviceList() {
        return pushTokenDeviceList;
    }

    public void setPushTokenDeviceList(List<TokenDevice> pushTokenDeviceList) {
        this.pushTokenDeviceList = pushTokenDeviceList;
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
