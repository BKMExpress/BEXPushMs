package com.bkm.bkmpushnotification.controller.core.response;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Akif Hatipoglu on 20.04.2018.
 */
public class GetPushRecordsResponse extends BkmBaseRestResponse {
    @Expose
    List<String> pushRecordLineList;

    public List<String> getPushRecordLineList() {
        return pushRecordLineList;
    }

    public void setPushRecordLineList(List<String> pushRecordLineList) {
        this.pushRecordLineList = pushRecordLineList;
    }
}
