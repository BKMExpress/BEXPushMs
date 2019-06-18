package com.bkm.bkmpushnotification.utility;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
public class BkmConstants {

    public static final int BKM_PUSH_SERVICE_LOG_MAX_SIZE = 2000;
    public static final int HALF_YEAR_OF_MONTH = 6;

    public static final String BKM_EXPRESS_APPLICATION_USER_ID = "123"; // must be a guid

    public static final String APP_NAME = "BKM PUSH NOTIFICATION";

    public static final String PUSH_ID = "pushId";

    public static final String PROD_ENVIRONMENT = "PROD";
    public static final String TEST_ENVIRONMENT = "TEST";

    public static final String ENVIRONMENT = "environment";
    public static final String PROPERTY_UPDATE_INTERVAL = "PROPERTY_UPDATE_INTERVAL";
    public static final String PROPERTY_UPDATE_INTERVAL_TIME = "3600000";

    public static final String FIREBASE_GET_IOS_TOKEN = "firebase_get_ios_token_url";
    public static final String FIREBASE_SANDBOX_MODE = "firebase_sandbox_mode";
    public static final String FIREBASE_IS_ON = "firebase_is_on";
    public static final String FIREBASE_PUSH_URL = "firebase_push_url";

    public static final String SQL_QUERY_FOR_AND = " AND ";
    public static final String SQL_QUERY_FOR_OR = " OR ";
    public static final String SQL_QUERY_FOR_IN = " IN (";
    public static final String SQL_QUERY_FOR_EQUAL_MARK = " = ? ";
    public static final String SQL_QUERY_FOR_BETWEEN = " BETWEEN ";

    public static final String PUSH_TYPE_COLUMN_NAME = "PUSH_TYPE_ID";
    public static final String PUSHSUB_TYPE_COLUMN_NAME = "PUSH_SUBTYPE_ID";
    public static final String DEVICE_TYPE_COLUMN_NAME = "DEVICETYPE";
    public static final String PUSH_TOKEN = "TOKEN";
    public static final String CREATED_DATE= "CREATED_DATE";
    public static final String SQL_QUERY_FOR_ORDER = "ORDER BY ID DESC";
}
