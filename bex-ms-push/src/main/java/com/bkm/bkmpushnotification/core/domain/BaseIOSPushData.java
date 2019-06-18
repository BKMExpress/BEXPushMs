package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * Created by Akif Hatipoglu on 05.04.2018.
 */
public class BaseIOSPushData {

    @Expose
    private String userId;

    @Expose
    private Long pushId;

    @Expose
    private HashMap<String,String> Data; //typo degil, kasıtlı yapılmıstır

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPushId() {
        return pushId;
    }

    public void setPushId(long pushId) {
        this.pushId = pushId;
    }

    public HashMap<String, String> getData() {
        return Data;
    }

    public void setData(HashMap<String, String> data) {
        Data = data;
    }
}
