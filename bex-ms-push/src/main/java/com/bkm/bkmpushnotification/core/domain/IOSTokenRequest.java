package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 23.02.2018.
 */
public class IOSTokenRequest {
    @Expose
    private String application;
    @Expose
    private Boolean sandbox;
    @Expose
    private List<String> apns_tokens = new ArrayList<String>();

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Boolean getSandbox() {
        return sandbox;
    }

    public void setSandbox(Boolean sandbox) {
        this.sandbox = sandbox;
    }

    public List<String> getApns_tokens() {
        return apns_tokens;
    }

    public void setApns_tokens(List<String> apns_tokens) {
        this.apns_tokens = apns_tokens;
    }
}
