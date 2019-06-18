package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Created by Akif Hatipoglu on 22.02.2018.
 */
public class PushNotificationInfo {
    @Expose
    private String body;
    @Expose
    private String title;
    @Expose
    private String sound;
    @Expose
    private boolean vibrate;
    @Expose
    private boolean alert;
    @Expose
    private String click_action;
    @Expose
    private String android_channel_id;

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }

    public String getAndroid_channel_id() {
        return android_channel_id;
    }

    public void setAndroid_channel_id(String android_channel_id) {
        this.android_channel_id = android_channel_id;
    }

    public String getObjectToJson(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
