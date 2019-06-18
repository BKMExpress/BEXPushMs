package com.bkm.bkmpushnotification.core.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
public class ListPushLog implements Serializable {

    @Expose
    private List<TokenInfo> tokenList;
    @Expose
    private int pushType;
    @Expose
    private int pushSubType;
    @Expose
    private int deviceType;

    public List<TokenInfo> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<TokenInfo> tokenList) {
        this.tokenList = tokenList;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    public int getPushSubType() {
        return pushSubType;
    }

    public void setPushSubType(int pushSubType) {
        this.pushSubType = pushSubType;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }
}
