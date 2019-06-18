package com.bkm.bkmpushnotification.controller.core.response;

import com.bkm.bkmpushnotification.controller.core.BkmBaseRestResponse;
import com.bkm.bkmpushnotification.core.domain.BulkResult;

import java.util.List;

/**
 * (C) Copyright
 * Created by Akif Hatipoglu on 11.03.2019.
 */
public class SendBulkPushNotificationResponse extends BkmBaseRestResponse{

    List<BulkResult> bulkResultList;

    public List<BulkResult> getBulkResultList() {
        return bulkResultList;
    }

    public void setBulkResultList(List<BulkResult> bulkResultList) {
        this.bulkResultList = bulkResultList;
    }
}
