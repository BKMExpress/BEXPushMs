package com.bkm.bkmpushnotification.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
public class PushRecord implements Serializable {

    private long id;
    private int pushMasterId;
    private int lineNo;
    private String line;
    private int validated;
    private int processed;
    private String errorDesc;
    private int JobSeq;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;
    private int processTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPushMasterId() {
        return pushMasterId;
    }

    public void setPushMasterId(int pushMasterId) {
        this.pushMasterId = pushMasterId;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public int getJobSeq() {
        return JobSeq;
    }

    public void setJobSeq(int jobSeq) {
        JobSeq = jobSeq;
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

    public int getProcessTime() {
        return processTime;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }
}