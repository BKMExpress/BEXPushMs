package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.core.dao.PushAppParamDao;
import com.bkm.bkmpushnotification.core.dao.PushAppUserDao;
import com.bkm.bkmpushnotification.core.dao.PushSubTypeDao;
import com.bkm.bkmpushnotification.core.dao.PushTypeDao;
import com.bkm.bkmpushnotification.core.domain.PushAppParam;
import com.bkm.bkmpushnotification.core.domain.PushAppUser;
import com.bkm.bkmpushnotification.core.domain.PushSubType;
import com.bkm.bkmpushnotification.core.domain.PushType;
import com.bkm.bkmpushnotification.utility.BkmConstants;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Akif Hatipoglu on 21.02.2018.
 */
@Service("PlatformPropertyService")
public class PlatformPropertyServiceImpl implements PlatformPropertyService {

    private static Properties platformProperties;
    private static Properties pushTypeProperties;
    private static Properties pushSubTypeProperties;
    private static HashMap<String, PushAppUser> applicationUserProperties;

    private static Date lastPlatformPropertiesUpdateDate = new Date(0);
    private static Date lastPushTypePropertiesUpdateDate = new Date(0);
    private static Date lastPushSubTypePropertiesUpdateDate = new Date(0);
    private static Date lastApplicationUserProperties = new Date(0);

    private final AtomicBoolean platformPropertiesUpdating = new AtomicBoolean(false);
    private final AtomicBoolean pushTypePropertiesUpdating = new AtomicBoolean(false);
    private final AtomicBoolean pushSubTypePropertiesUpdating = new AtomicBoolean(false);
    private final AtomicBoolean applicationUserPropertiesUpdating = new AtomicBoolean(false);

    private static final Logger logger = LoggerFactory.getLogger(PlatformPropertyServiceImpl.class);

    @Autowired
    PushAppParamDao pushAppParamDao;

    @Autowired
    PushTypeDao pushTypeDao;

    @Autowired
    PushSubTypeDao pushSubTypeDao;

    @Autowired
    PushAppUserDao pushAppUserDao;

    @Override
    public Optional<String> getPlatformProperty(String key) {

        String updateInterval = System.getProperty(BkmConstants.PROPERTY_UPDATE_INTERVAL);
        int updateIntervalTime = Integer.parseInt(updateInterval == null ? BkmConstants.PROPERTY_UPDATE_INTERVAL_TIME : updateInterval);
        if (platformProperties == null || new Date().getTime() - lastPlatformPropertiesUpdateDate.getTime() > updateIntervalTime && platformPropertiesUpdating.compareAndSet(false, true)) {

            Optional<List<PushAppParam>> appParams = pushAppParamDao.findAll();

            Properties properties = new Properties();
            appParams.ifPresent(x -> x.forEach(appParam -> {
                if (!BkmUtil.isNullOrEmptyString(appParam.getName()) && !BkmUtil.isNullOrEmptyString(appParam.getValue())) {
                    properties.put(appParam.getName(), appParam.getValue());
                } else {
                    logger.info("this application parameter is null");
                }
            }));

            platformProperties = properties;
            lastPlatformPropertiesUpdateDate = new Date();

            platformPropertiesUpdating.set(false);

            logger.info("application parameters are taken from db");

        }
        if (!BkmUtil.isNullOrEmptyString(key) && platformProperties.containsKey(key)) {
            return Optional.ofNullable(platformProperties.getProperty(key));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getPushTypeProperty(int key) {

        String updateInterval = System.getProperty(BkmConstants.PROPERTY_UPDATE_INTERVAL);
        int updateIntervalTime = Integer.parseInt(updateInterval == null ? BkmConstants.PROPERTY_UPDATE_INTERVAL_TIME : updateInterval);
        if (pushTypeProperties == null || new Date().getTime() - lastPushTypePropertiesUpdateDate.getTime() > updateIntervalTime && pushTypePropertiesUpdating.compareAndSet(false, true)) {

            Optional<List<PushType>> pushTypes = pushTypeDao.findAll();

            Properties properties = new Properties();
            pushTypes.ifPresent(x -> x.forEach(pushType -> {
                if (!BkmUtil.isNullOrEmptyString(pushType.getName())) {
                    properties.put(pushType.getId(), pushType.getName());
                } else {
                    logger.info("Id: " + pushType.getId() + " push type parameter is null");
                }
            }));

            pushTypeProperties = properties;
            lastPushTypePropertiesUpdateDate = new Date();

            pushTypePropertiesUpdating.set(false);

            logger.info("push type parameters are taken from db");

        }
        if (pushTypeProperties.containsKey(key)) {
            return Optional.ofNullable(pushTypeProperties.get(key).toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getPushSubTypeProperty(int key) {

        String updateInterval = System.getProperty(BkmConstants.PROPERTY_UPDATE_INTERVAL);
        int updateIntervalTime = Integer.parseInt(updateInterval == null ? BkmConstants.PROPERTY_UPDATE_INTERVAL_TIME : updateInterval);
        if (pushSubTypeProperties == null || new Date().getTime() - lastPushSubTypePropertiesUpdateDate.getTime() > updateIntervalTime && pushSubTypePropertiesUpdating.compareAndSet(false, true)) {

            Optional<List<PushSubType>> pushSubTypes = pushSubTypeDao.findAll();

            Properties properties = new Properties();
            pushSubTypes.ifPresent(x -> x.forEach(pushSubType -> {
                if (!BkmUtil.isNullOrEmptyString(pushSubType.getName())) {
                    properties.put(pushSubType.getId(), pushSubType.getName());
                } else {
                    logger.info("Id: " + pushSubType.getId() + " push sub type parameter is null");
                }
            }));

            pushSubTypeProperties = properties;
            lastPushSubTypePropertiesUpdateDate = new Date();

            pushSubTypePropertiesUpdating.set(false);

            logger.info("push sub type parameters are taken from db");

        }
        if (pushSubTypeProperties.containsKey(key)) {
            return Optional.ofNullable(pushSubTypeProperties.get(key).toString());
        }
        return Optional.empty();
    }

    @Override
    public Optional<PushAppUser> getAppUserProperty(String key) {

        String updateInterval = System.getProperty(BkmConstants.PROPERTY_UPDATE_INTERVAL);
        int updateIntervalTime = Integer.parseInt(updateInterval == null ? BkmConstants.PROPERTY_UPDATE_INTERVAL_TIME : updateInterval);
        if (applicationUserProperties == null || new Date().getTime() - lastApplicationUserProperties.getTime() > updateIntervalTime && applicationUserPropertiesUpdating.compareAndSet(false, true)) {

            Optional<List<PushAppUser>> pushAppUsers = pushAppUserDao.findAll();

            HashMap<String, PushAppUser> properties = new HashMap<>();
            pushAppUsers.ifPresent(x -> x.forEach(pushAppUser -> {
                if (!BkmUtil.isNullOrEmptyString(pushAppUser.getIndicator())) {
                    properties.put(pushAppUser.getIndicator(), pushAppUser);
                } else {
                    logger.info("Id: " + pushAppUser.getId() + " app user parameter is null");
                }
            }));

            applicationUserProperties = properties;
            lastApplicationUserProperties = new Date();

            applicationUserPropertiesUpdating.set(false);

            logger.info("application user parameters are taken from db");

        }
        if (!BkmUtil.isNullOrEmptyString(key) && applicationUserProperties.containsKey(key)) {
            return Optional.ofNullable(applicationUserProperties.get(key));
        }
        return Optional.empty();
    }

    @Override
    public boolean isTestEnvironment() {

        Optional<String> environment = getPlatformProperty(BkmConstants.ENVIRONMENT);
        if (environment.isPresent()) {
            return environment.get().equals(BkmConstants.TEST_ENVIRONMENT) ? true : false;
        }
        return true;
    }

    @Override
    public boolean isFirebaseIsOn() {

        Optional<String> firebaseIsOn = getPlatformProperty(BkmConstants.FIREBASE_IS_ON);
        if (firebaseIsOn.isPresent()) {
            return Boolean.parseBoolean(firebaseIsOn.get());
        }
        return true;
    }
}