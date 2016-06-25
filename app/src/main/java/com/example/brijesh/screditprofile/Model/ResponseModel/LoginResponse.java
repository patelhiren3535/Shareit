package com.example.brijesh.screditprofile.Model.ResponseModel;

/**
 * Created by admin-4 on 21/6/16.
 */
public class LoginResponse {
    boolean Status;
    String Message;
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


}
