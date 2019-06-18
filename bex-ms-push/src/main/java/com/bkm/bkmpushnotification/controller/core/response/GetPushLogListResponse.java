package com.bkm.bkmpushnotification.controller.core.response;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.core.domain.PushLog;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
public class GetPushLogListResponse extends BkmBaseRestResponse{

    @Expose
    private List<PushLog> pushLogList;

    public List<PushLog> getPushLogList() {
        return pushLogList;
    }

    public void setPushLogList(List<PushLog> pushLogList) {
        this.pushLogList = pushLogList;
    }
}
