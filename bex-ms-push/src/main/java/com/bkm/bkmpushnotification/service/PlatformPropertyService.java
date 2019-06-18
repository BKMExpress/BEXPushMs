package com.bkm.bkmpushnotification.service;

import com.bkm.bkmpushnotification.core.domain.PushAppUser;

import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 21.02.2018.
 */
public interface PlatformPropertyService {

    Optional<String> getPlatformProperty(String key);

    Optional<String> getPushTypeProperty(int key);

    Optional<String> getPushSubTypeProperty(int key);

    Optional<PushAppUser> getAppUserProperty(String key);

    boolean isTestEnvironment();

    boolean isFirebaseIsOn();
}
