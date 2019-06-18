package com.bkm.bkmpushnotification.core.enums;

import javax.xml.bind.annotation.XmlEnum;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Akif Hatipoglu on 24.01.2018.
 */
@XmlEnum
public enum ReturnCode implements Serializable {

    SUCCESS(0, "Success"),
    FAILED(999, "Failed"),
    PUSH_TOKEN_NOT_FOUND(1, "Push Token Info Not Found"),
    DEVICE_TYPE_NOT_FOUND(2, "Device Type Info Not Found"),
    PUSH_TYPE_NOT_FOUND(3, "Push Type Info Not Found"),
    PUSH_SUBTYPE_NOT_FOUND(4, "Push Subtype Info Not Found"),
    PUSH_APPLICATION_ID_NOT_FOUND(5, "Push Application Id Not Found"),
    PUSH_NOTIFICATION_INFO_OBJECT_NOT_FOUND(6, "Push Notification Info Object Not Found"),
    ALLOWED_TOKEN_NOT_FOUND(7, "Allowed Token Not Found For This Environment"),
    FIREBASE_IS_OFFLINE(8, "Firebase services is offline"),
    IOS_TOKEN_NOT_FOUND(9, "IOS Token Not Found"),
    SHOW_INFO_NOT_UPDATED(10, "Show info not updated for log id"),
    LOG_ID_NOT_FOUND(11, "Log id info not found"),
    LOG_INFO_NOT_FOUND(12, "Log info not found"),
    PUSH_SERVICE_SENDING_EXCEPTION(13, "FCM service fail"),
    PUSH_MASTER_INFO_NOT_FOUND(14, "Push master info not found");

    private int code;
    private String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ReturnCode get(int code) {
        return Arrays.stream(values()).filter(x -> x.getCode() == code).findAny().orElse(ReturnCode.FAILED);
    }
}
