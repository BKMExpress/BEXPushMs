package com.bkm.bkmpushnotification.aspect;

import com.bkm.bkmpushnotification.core.dao.PushServiceLogDao;
import com.bkm.bkmpushnotification.core.domain.PushServiceLog;
import com.bkm.bkmpushnotification.utility.BkmConstants;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Akif Hatipoglu on 05.02.2018.
 */
@Component
@Aspect
public class BkmRestAspect {

    private static final Logger logger = LoggerFactory.getLogger(BkmRestAspect.class);

    @Autowired
    PushServiceLogDao pushServiceLogDao;

    //@Pointcut("execution(* *(..))")
    //public void methodPointcut() {}

    @Around("within(@org.springframework.web.bind.annotation.RestController *) && args(request)")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint, Object request) {

        String requestJson = "";
        String responseJson = "";
        String methodName = "";
        Object response = null;
        Date currentTime = new Date();
        try {
            requestJson = BkmUtil.getJsonString(request);

            response = proceedingJoinPoint.proceed();

            responseJson = BkmUtil.getJsonString(response);
            methodName = proceedingJoinPoint.getSignature().getName();
            return response;
        } catch (Throwable e) {
            logger.error("around Controller exception : " + ExceptionUtils.getStackTrace(e));
            responseJson = ExceptionUtils.getStackTrace(e);
        } finally {
            if(!"sendBulkPushNotification".equals(methodName)) {
                PushServiceLog pushServiceLog = new PushServiceLog();
                pushServiceLog.setRequest(requestJson);
                pushServiceLog.setResponse(responseJson);
                pushServiceLog.setServiceName(methodName);
                pushServiceLog.setServer(BkmUtil.getHostIp());
                pushServiceLog.setCreatedDate(currentTime);
                pushServiceLog.setCreatedBy(BkmConstants.APP_NAME);
                pushServiceLog.setUpdatedDate(new Date());
                pushServiceLog.setUpdatedBy(BkmConstants.APP_NAME);
                pushServiceLogDao.insert(pushServiceLog);
            }
        }
        return response;
    }
}
