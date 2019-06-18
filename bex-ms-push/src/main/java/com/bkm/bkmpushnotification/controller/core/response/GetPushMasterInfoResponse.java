package com.bkm.bkmpushnotification.controller.core.response;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.core.domain.PushMaster;
import com.bkm.bkmpushnotification.core.domain.PushMasterInfo;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Akif Hatipoglu on 18.04.2018.
 */
public class GetPushMasterInfoResponse extends BkmBaseRestResponse{

    @Expose
    private List<PushMaster> pushMasterList;

    public List<PushMaster> getPushMasterList() {
        return pushMasterList;
    }

    public void setPushMasterList(List<PushMaster> pushMasterList) {
        this.pushMasterList = pushMasterList;
    }
}
