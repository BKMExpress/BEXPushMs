package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * Created by Akif Hatipoglu on 26.02.2018.
 */
public class PushNotificationRequest {
    @Expose
    private String to;

    @Expose
    private String priority ;

    @Expose
    private PushNotificationInfo notification;

    @Expose
    private HashMap<String,String> data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public PushNotificationInfo getNotification() {
        return notification;
    }

    public void setNotification(PushNotificationInfo notification) {
        this.notification = notification;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
