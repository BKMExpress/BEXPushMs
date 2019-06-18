package com.bkm.bkmpushnotification.controller;

import com.bkm.bkmpushnotification.service.MonitoringService;
import com.bkm.bkmpushnotification.service.MonitoringServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Akif Hatipoglu on 02.02.2018.
 */
@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    MonitoringService monitoringService;

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public
    @ResponseBody
    String healthCheck() {
        //monitoringService.healthCheck();
        return "UP =>  " + UUID.randomUUID() + " time:  " + new Date();
    }

}
