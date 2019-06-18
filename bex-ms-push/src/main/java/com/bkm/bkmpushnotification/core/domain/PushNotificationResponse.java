package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 26.02.2018.
 */
public class PushNotificationResponse {
    @Expose
    private String multicast_id;
    @Expose
    private String success;
    @Expose
    private String failure;
    @Expose
    private String canonical_ids;
    @Expose
    private List<PushError> results = new ArrayList<PushError>();

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(String canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<PushError> getResults() {
        return results;
    }

    public void setResults(List<PushError> results) {
        this.results = results;
    }
}
