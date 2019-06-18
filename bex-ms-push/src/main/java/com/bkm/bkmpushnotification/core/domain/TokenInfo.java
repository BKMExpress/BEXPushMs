package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 03.05.2018.
 */
public class TokenInfo implements Serializable {

    @Expose
    public String pushToken;
    @Expose
    public Date startDate;
    @Expose
    public Date endDate;

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
