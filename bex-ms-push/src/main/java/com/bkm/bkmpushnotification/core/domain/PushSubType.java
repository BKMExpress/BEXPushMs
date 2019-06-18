package com.bkm.bkmpushnotification.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
public class PushSubType implements Serializable {

    private int id;
    private int pushTypeId;
    private String name;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPushTypeId() {
        return pushTypeId;
    }

    public void setPushTypeId(int pushTypeId) {
        this.pushTypeId = pushTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
