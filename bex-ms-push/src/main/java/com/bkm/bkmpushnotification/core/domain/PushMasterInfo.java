package com.bkm.bkmpushnotification.core.domain;

import java.util.List;

/**
 * Created by Akif Hatipoglu on 18.04.2018.
 */
public class PushMasterInfo extends PushMaster{

    private List<String> pushTokenLineList;

    public List<String> getPushTokenLineList() {
        return pushTokenLineList;
    }

    public void setPushTokenLineList(List<String> pushTokenLineList) {
        this.pushTokenLineList = pushTokenLineList;
    }
}
