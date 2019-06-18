package com.bkm.bkmpushnotification.utility;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
public class BkmQueries {

    public static final String VALIDATION_QUERY = "SELECT 1";

    //region Push Type
    public static final String GET_ALL_PUSH_TYPE = "SELECT * FROM PUSH_TYPE";
    public static final String GET_PUSH_TYPE_BY_ID = "SELECT * FROM PUSH_TYPE WHERE ID = ?";
    //endregion

    //region Push Sub Type
    public static final String GET_ALL_PUSH_SUB_TYPE = "SELECT * FROM PUSH_SUBTYPE";
    public static final String GET_PUSH_SUB_TYPE_BY_ID = "SELECT * FROM PUSH_SUBTYPE WHERE ID = ?";
    //endregion

    //region Push App Param
    public static final String GET_ALL_PUSH_APP_PARAM = "SELECT * FROM PUSH_APP_PARAM";
    //endregion


    //region Push App User
    public static final String GET_ALL_PUSH_APP_USER = "SELECT * FROM PUSH_APP_USER";
    //endregion

    //region Allowed Token
    public static final String GET_ALLOWED_TOKENS_BY_TOKEN = "SELECT * FROM ALLOWED_TOKENS WHERE TOKEN = ?";
    //endregion

    //region Push Log
    public static final String INSERT_PUSH_LOG = "INSERT INTO PUSH_LOG (APP_ID, TOKEN, MESSAGE, PUSH_TYPE_ID, PUSH_SUBTYPE_ID,DEVICETYPE,IS_SHOW,IS_SEND, PUSH_RECORD_ID) " +
                                                    "VALUES (?,?,?,?,?,?,0,?,?)";
    public static final String UPDATE_PUSH_LOG_SEND_INFO = "UPDATE PUSH_LOG SET IS_SEND = 1 WHERE ID=?";

    public static final String UPDATE_PUSH_LOG_SEND_FOR_BULK_INFO = "UPDATE PUSH_LOG SET IS_SEND = 0 WHERE ID=?";

    public static final String UPDATE_PUSH_LOG_SHOW_INFO = "UPDATE PUSH_LOG SET IS_SHOW = 1, UPDATED_DATE = SYSDATE() WHERE ID=?";

    public static final String SELECT_PUSH_LOG_FOR_DYNAMIC_QUERY = "SELECT * FROM PUSH_LOG WHERE ";

    //endregion

    //region Push Service Log
    public static final String INSERT_PUSH_SERVICE_LOG = "INSERT INTO PUSH_SERVICE_LOG (SERVICENAME, REQUEST, RESPONSE, SERVER, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY) \n" +
                                                            "VALUES (?,?,?,?,?,?,?,?)";
    //endregion

    //region
    public static final String GET_PUSH_MASTER_BY_CREATE_DATE = "SELECT * FROM PUSH_MASTER WHERE CREATED_DATE > ?";
    public static final String GET_PUSH_RECORD_COUNT_BY_PROCESSED = "SELECT COUNT(*) FROM PUSH_RECORD WHERE PROCESSED = ? AND PUSH_MASTER_ID = ?";
    public static final String GET_PUSH_RECORDS_BY_PROCESSED = "SELECT LINE FROM PUSH_RECORD WHERE PROCESSED NOT IN (-1,10) AND PUSH_MASTER_ID = ?";
    //endregion
}
