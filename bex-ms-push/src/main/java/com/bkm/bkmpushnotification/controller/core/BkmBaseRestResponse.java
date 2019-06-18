package com.bkm.bkmpushnotification.controller.core;

import com.bkm.bkmpushnotification.core.enums.ReturnCode;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Akif Hatipoglu on 25.01.2018.
 */

public class BkmBaseRestResponse implements Serializable{

    @Expose
    private boolean status;
    @Expose
    private int code;
    @Expose
    private String desc;

    public BkmBaseRestResponse() {
        setReturnCode(ReturnCode.SUCCESS,"");
    }

    public BkmBaseRestResponse(ReturnCode returnCode) {
        setReturnCode(returnCode, "");
    }

    public BkmBaseRestResponse(ReturnCode returnCode, String extraMessage) {
        setReturnCode(returnCode, extraMessage);
    }

    public void setReturnCode(@NotNull ReturnCode returnCode) {
        setReturnCode(returnCode,"");
    }

    public void setReturnCode(@NotNull ReturnCode returnCode,String extraMessage) {
        setCode(returnCode.getCode());
        setDesc(returnCode.getMessage());
        if(!BkmUtil.isNullOrEmptyString(extraMessage)) {
            setDesc(getDesc() + " ,Extra: " + extraMessage);
        }
        if (!returnCode.equals(ReturnCode.SUCCESS)) {
            setStatus(Boolean.valueOf(false));
        } else {
            setStatus(Boolean.valueOf(true));
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
