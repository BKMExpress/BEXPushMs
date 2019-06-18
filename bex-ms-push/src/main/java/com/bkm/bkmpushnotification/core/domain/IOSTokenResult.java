package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

/**
 * Created by Akif Hatipoglu on 23.02.2018.
 */
public class IOSTokenResult {
    @Expose
    private String registration_token;
    @Expose
    private String apns_token;
    @Expose
    private String status;

    public String getApns_token() {
        return apns_token;
    }

    public void setApns_token(String apns_token) {
        this.apns_token = apns_token;
    }

    public String getRegistration_token() {
        return registration_token;
    }

    public void setRegistration_token(String registration_token) {
        this.registration_token = registration_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
