package com.bkm.bkmpushnotification.controller;

import com.bkm.bkmpushnotification.controller.core.request.GetPushLogListRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushMasterInfoRequest;
import com.bkm.bkmpushnotification.controller.core.request.GetPushRecordsRequest;
import com.bkm.bkmpushnotification.controller.core.response.GetPushLogListResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushMasterInfoResponse;
import com.bkm.bkmpushnotification.controller.core.response.GetPushRecordsResponse;
import com.bkm.bkmpushnotification.service.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Akif Hatipoglu on 01.03.2018.
 */
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringController.class);

    @Autowired
    MonitoringService monitoringService;

    @RequestMapping(value = {"/getPushLogList.do"}, method = RequestMethod.POST)
    public @ResponseBody GetPushLogListResponse getPushLogList(@RequestBody GetPushLogListRequest request) {
        logger.info("post request for getPushLogList.do");
        return monitoringService.getPushLogList(request);
    }

    @RequestMapping(value = {"/getPushMasterInfo.do"},method = RequestMethod.POST)
    public @ResponseBody
    GetPushMasterInfoResponse getPushMasterInfo(@RequestBody GetPushMasterInfoRequest request){
        logger.info("post request for getPushMasterInfo.do");
        return monitoringService.getPushMasterInfo(request);
    }

    @RequestMapping(value = {"/getPushRecords.do"},method = RequestMethod.POST)
    public @ResponseBody
    GetPushRecordsResponse getPushRecords(@RequestBody GetPushRecordsRequest request){
        logger.info("post request for getPushRecords.do");
        return monitoringService.getPushRecords(request);
    }
}
