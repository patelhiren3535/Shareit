package com.example.brijesh.screditprofile.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Utility {
    static int pos = 0;
    Context _context;
    static Dialog dialog;

    public Utility(Context c) {
        this._context = c;
    }


    public static String getResponseString(String requestURL) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(35000);
            conn.setConnectTimeout(35000);
            /*conn.setReadTimeout(100);
			conn.setConnectTimeout(100);*/
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    public static String getResponseStringUsingPostMethod(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(35000);
            conn.setConnectTimeout(35000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        Log.d("post string", "post string==" + result.toString());
        return result.toString();
    }

    public static final ProgressDialog getProgressDialog(Context context, String dialogMessage) {

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(dialogMessage);
        return progressDialog;
    }

    public static void RedirectToActivity(Activity yourActivity, Class SecondActivity,
                                          boolean isfinish, Bundle b) {
        Intent intent = new Intent(yourActivity, SecondActivity);

        if (b != null)
            intent.putExtras(b);

        yourActivity.startActivity(intent);

        yourActivity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        if (isfinish) {
            yourActivity.finish();
        }

    }

    public static void showToast(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public static int getPxFromDp(Context context, float dpUnits) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpUnits, context.getResources().getDisplayMetrics());
    }
}
