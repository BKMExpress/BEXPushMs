package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.request.GetPushLogListRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushMasterInfoRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushRecordsRequest;
import com.bkm.bkmpushnotification.controller.core.response.GetPushLogListResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushMasterInfoResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushRecordsResponse;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
public interface MonitoringService {

    GetPushLogListResponse getPushLogList(GetPushLogListRequest request);

    GetPushMasterInfoResponse getPushMasterInfo(GetPushMasterInfoRequest request);

    GetPushRecordsResponse getPushRecords(GetPushRecordsRequest request);

    void healthCheck();
}
