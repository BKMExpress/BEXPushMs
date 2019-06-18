package com.bkm.bkmpushnotification.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
public class AllowedToken implements Serializable {

    private String token;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
