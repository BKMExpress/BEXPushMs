package com.bkm.bkmpushnotification.controller.core.request;

import com.bkm.bkmpushnotification.core.domain.ListPushLog;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
public class GetPushLogListRequest implements Serializable {

    @Expose
    private ListPushLog listPushLog;

    public ListPushLog getListPushLog() {
        return listPushLog;
    }

    public void setListPushLog(ListPushLog listPushLog) {
        this.listPushLog = listPushLog;
    }
}
