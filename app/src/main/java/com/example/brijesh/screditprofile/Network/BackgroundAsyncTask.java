package com.example.brijesh.screditprofile.Network;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;


import com.example.brijesh.screditprofile.Utility.Utility;
import com.example.brijesh.screditprofile.listner.ApiResponse;

import java.util.HashMap;

public class BackgroundAsyncTask extends AsyncTask<Object, Void, String> {

    public static Context context;
    private String url;
    private String data;
    private ApiResponse apiResponse;
    private int code;
    private String dialogMessage;
    private boolean isGET;
    private HashMap<String, String> postDataParams = new HashMap<String, String>();
    private ProgressDialog dialog = null;

    public BackgroundAsyncTask(Context context, String url, String dialogMessage, int code, boolean isGET) {
        this.context = context;
        this.url = url;
        this.dialogMessage = dialogMessage;
        this.code = code;
        this.isGET = isGET;
    }

    public BackgroundAsyncTask(Context context, String url, String dialogMessage, int code, HashMap<String, String> postDataParams, boolean isGET) {

        this.context = context;
        this.url = url;
        this.dialogMessage = dialogMessage;
        this.code = code;
        this.isGET = isGET;
        this.postDataParams = postDataParams;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Request URL :" + url);
        if (!dialogMessage.equals("")) {
            dialog = Utility.getProgressDialog(context, dialogMessage);
            if (dialog != null) {
                if (!dialog.isShowing()) {
                    dialog.show();
                } else {
                    dialog.dismiss();
                }
            }

        }
        /*if (KissKiss.hasActiveInternetConnection(context)) {
            if (!dialogMessage.equals("")) {
				dialog = Utility.getProgressDialog(context, dialogMessage);
				if (dialog != null) {
					if (!dialog.isShowing()) {
						dialog.show();
					} else {
						dialog.dismiss();
					}
				}

			}
		} else {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setTitle("Connectivity");
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setMessage("Pleace check internet conection");
			alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					Activity activity = (Activity) context;
					activity.finish();

				}
			});


			alertDialogBuilder.setPositiveButton("Try", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					new BackgroundAsyncTask(context, url, dialogMessage, code, postDataParams, false).execute(context);
				}
			});

			alertDialogBuilder.show();


			this.cancel(true);
			return;

		}*/

    }

    @Override
    protected String doInBackground(Object... arg0) {
        // TODO Auto-generated method stub

        if (this.isCancelled())
            return null;

        apiResponse = (ApiResponse) arg0[0];
        String response = null;
        if (isGET) {
            response = Utility.getResponseString(url);//get method
        } else {
            response = Utility.getResponseStringUsingPostMethod(url, postDataParams);//post method
        }
        return response;
    }

    public BackgroundAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(String response) {

        System.out.println("Response in ASYNC :" + response);
        try {
            if ((this.dialog != null) && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.dialog = null;
        }

        if (response == null || response.isEmpty()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            alertDialogBuilder.setTitle("Request TimeOut");
            alertDialogBuilder.setMessage("Please try again");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Activity activity = (Activity) context;
                    activity.finish();

                }
            });


            alertDialogBuilder.setPositiveButton("Try", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    new BackgroundAsyncTask(context, url, dialogMessage, code, postDataParams, false).execute(context);

                }
            });

            alertDialogBuilder.show();


        } else {
            apiResponse.apiResponsePostProcessing(response, code);
        }


        this.cancel(true);
        return;
    }


}
