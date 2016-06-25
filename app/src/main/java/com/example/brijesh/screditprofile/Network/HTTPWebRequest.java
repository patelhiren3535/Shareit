package com.example.brijesh.screditprofile.Network;

import android.content.Context;
import android.util.Log;

import com.example.brijesh.screditprofile.Language_selection;
import com.example.brijesh.screditprofile.Model.RequestModel.LanguageRequest;
import com.example.brijesh.screditprofile.Model.RequestModel.LoginRequest;
import com.example.brijesh.screditprofile.Model.RequestModel.RegistrationRequest;
import com.example.brijesh.screditprofile.Utility.AppConstants;
import com.example.brijesh.screditprofile.listner.ApiResponse;
import com.google.gson.Gson;

import java.util.HashMap;

public class HTTPWebRequest {
    public static void Login(Context context, LoginRequest req, int apiCode, ApiResponse apiResponse) {

        HashMap<String, String> postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, req.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.PASSWORD, req.getPassword());
        new BackgroundAsyncTask(context, AppConstants.APIURL.URL_LOGIN, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, postDataParams, false).execute(apiResponse);
    }
    public static void Language(Context context, int apiCode, ApiResponse apiResponse) {

        HashMap<String, String> postDataParams = new HashMap<String, String>();
        //postDataParams.put(AppConstants.RequestDataKey.LANGUAGE, req.getLanguage());
        new BackgroundAsyncTask(context, AppConstants.APIURL.URL_LANGUAGE, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, postDataParams, false).execute(apiResponse);
    }
    public static void Registration(Context context, RegistrationRequest req, int apiCode, ApiResponse apiResponse) {

        HashMap<String, String> postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, req.getFname());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, req.getLname());
        postDataParams.put(AppConstants.RequestDataKey.MOBILE, req.getNumber());
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, req.getMail());
        postDataParams.put(AppConstants.RequestDataKey.BIRTHDATE, req.getDob());
        postDataParams.put(AppConstants.RequestDataKey.LATTITUDE, req.getLattitude());
        postDataParams.put(AppConstants.RequestDataKey.LONGITUDE, req.getLongitud());
        postDataParams.put(AppConstants.RequestDataKey.PASSWORD, req.getPass());
        postDataParams.put(AppConstants.RequestDataKey.DEVICETYPE, req.getDevicetype());
        postDataParams.put(AppConstants.RequestDataKey.IMAGE, req.getImage());
        new BackgroundAsyncTask(context, AppConstants.APIURL.URL_REGISTER, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, postDataParams, false).execute(apiResponse);
    }


   /* public static void Registration(Context context, RegistrationRequest req, int apiCode, ApiResponse apiResponse) {

        HashMap<String, String> postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, req.getFirstname());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, req.getLastname());
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, req.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.PASSWORD, req.getPassword());
        postDataParams.put(AppConstants.RequestDataKey.MOBILE, req.getMobile());
        postDataParams.put(AppConstants.RequestDataKey.GENDER, req.getGender());
        postDataParams.put(AppConstants.RequestDataKey.BIRTHDATE, req.getBirthDate());
        new BackgroundAsyncTask(context, AppConstants.APIURL.URL_REGISTER, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, postDataParams, false).execute(apiResponse);
    }*/
}
