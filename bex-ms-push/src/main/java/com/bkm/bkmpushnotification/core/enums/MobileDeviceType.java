package com.bkm.bkmpushnotification.core.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Akif Hatipoglu on 21.02.2018.
 */
public enum MobileDeviceType implements Serializable {

    IPHONE(1),
    IPAD(2),
    ANDROID_PHONE(3),
    ANDROID_TABLET(4),
    WINDOWS_PHONE(5),
    WINDOWS_TABLET(6),
    OTHER(7);

    private int typeCode;
    private static List<Integer> codes = Arrays.asList(
            IPHONE.typeCode, IPAD.typeCode, ANDROID_PHONE.typeCode, ANDROID_TABLET.typeCode, WINDOWS_PHONE.typeCode,
            WINDOWS_TABLET.typeCode, OTHER.typeCode);

    private MobileDeviceType(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public static MobileDeviceType get(int typeCode) {
        return Arrays.stream(values()).filter(x -> x.getTypeCode() == typeCode).findAny().orElse(MobileDeviceType.OTHER);
    }

    public static MobileDeviceType getForBulk(int typeCode){
        if(typeCode == 1)
            return MobileDeviceType.IPHONE;
        else
            return MobileDeviceType.ANDROID_PHONE;
    }

    public static boolean isExist(int type) {
        return codes.contains(type);
    }

    public static List<Integer> getCodes() {
        return codes;
    }
}
