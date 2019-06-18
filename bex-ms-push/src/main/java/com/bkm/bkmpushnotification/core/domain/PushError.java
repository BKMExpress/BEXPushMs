package com.bkm.bkmpushnotification.core.domain;

/**
 * Created by Akif Hatipoglu on 26.02.2018.
 */
public class PushError {
    private String error;

    private String message_id;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
