package com.example.brijesh.screditprofile.Model.ResponseModel;

/**
 * Created by admin-4 on 21/6/16.
 */
public class RegistrationResponse {
    boolean status;
    static String message;

    public static String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
