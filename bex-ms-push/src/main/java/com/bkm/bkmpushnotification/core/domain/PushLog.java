package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
public class PushLog implements Serializable {

    @Expose
    private long id;

    private int appId;
    private String token;
    private String message;
    private boolean isShow;
    private int pushTypeId;
    private int pushSubTypeId;
    private boolean isSend;
    private int deviceType;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public int getPushTypeId() {
        return pushTypeId;
    }

    public void setPushTypeId(int pushTypeId) {
        this.pushTypeId = pushTypeId;
    }

    public int getPushSubTypeId() {
        return pushSubTypeId;
    }

    public void setPushSubTypeId(int pushSubTypeId) {
        this.pushSubTypeId = pushSubTypeId;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend = isSend;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}