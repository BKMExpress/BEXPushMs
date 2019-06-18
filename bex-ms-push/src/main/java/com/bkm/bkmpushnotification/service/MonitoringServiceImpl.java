package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.controller.core.request.GetPushLogListRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushMasterInfoRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushRecordsRequest;
import com.bkm.bkmpushnotification.controller.core.response.GetPushLogListResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushMasterInfoResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushRecordsResponse;
import com.bkm.bkmpushnotification.core.dao.PushLogDao;
import com.bkm.bkmpushnotification.core.dao.PushMasterDao;
import com.bkm.bkmpushnotification.core.dao.PushRecordDao;
import com.bkm.bkmpushnotification.core.domain.PushLog;
import com.bkm.bkmpushnotification.core.domain.PushMaster;
import com.bkm.bkmpushnotification.core.domain.PushMasterInfo;
import com.bkm.bkmpushnotification.core.domain.PushRecord;
import com.bkm.bkmpushnotification.core.enums.ReturnCode;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
@Service("MonitoringService")
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    PushLogDao pushLogDao;

    @Autowired
    PushMasterDao pushMasterDao;

    @Autowired
    PushRecordDao pushRecordDao;

    @Override
    public GetPushLogListResponse getPushLogList(GetPushLogListRequest request) {
        GetPushLogListResponse response = new GetPushLogListResponse();

        if(request == null || request.getListPushLog() == null) {
            response.setReturnCode(ReturnCode.FAILED);
            return response;
        }

        Optional<List<PushLog>> pushLogList = pushLogDao.findByDynamicParameters(request.getListPushLog());
        if(!pushLogList.isPresent()){
            response.setReturnCode(ReturnCode.LOG_INFO_NOT_FOUND);
            return response;
        }

        pushLogList.get().forEach(pushLog -> {
            Optional<String> message = BkmUtil.getMessageFromJson(pushLog.getMessage());
            if(message.isPresent()){
                String decodedMessage = new String(Base64.getDecoder().decode(message.get()), StandardCharsets.UTF_8);
                pushLog.setMessage(decodedMessage);
            } else {
                pushLog.setMessage("");
            }
        });

        response.setPushLogList(pushLogList.get());
        response.setReturnCode(ReturnCode.SUCCESS);
        return response;
    }

    @Override
    public GetPushMasterInfoResponse getPushMasterInfo(GetPushMasterInfoRequest request) {
        GetPushMasterInfoResponse response = new GetPushMasterInfoResponse();

        Date lastMonth = BkmUtil.addToNow(Calendar.MONTH, -1);

        Optional<List<PushMaster>> pushMasterList = pushMasterDao.findByCreatedDate(lastMonth);
        if (!pushMasterList.isPresent()) {
            response.setReturnCode(ReturnCode.PUSH_MASTER_INFO_NOT_FOUND);
            return response;
        }
        pushMasterList.get().forEach(pushMaster -> {
            String decodedMessage = new String(Base64.getDecoder().decode(pushMaster.getMessage()), StandardCharsets.UTF_8);
            pushMaster.setMessage(decodedMessage);
        });
        response.setPushMasterList(pushMasterList.get());
        response.setReturnCode(ReturnCode.SUCCESS);
        return response;
    }

    @Override
    public GetPushRecordsResponse getPushRecords(GetPushRecordsRequest request) {
        GetPushRecordsResponse response = new GetPushRecordsResponse();
        List<String> pushRecordLineList = new ArrayList<>();

        if(!BkmUtil.isNullOrEmptyString(request.getPushMasterId())){
            Optional<List<PushRecord>> pushRecordList = pushRecordDao.getLineListByProcessed(request.getPushMasterId());
            if (pushRecordList.isPresent()) {
                pushRecordList.get().forEach(pushRecord -> {
                    pushRecordLineList.add(pushRecord.getLine());
                });
            }
        }

        response.setPushRecordLineList(pushRecordLineList);
        return  response;
    }

    @Override
    public void healthCheck() {
        pushMasterDao.validationQuery();
    }
}
