package com.example.brijesh.screditprofile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brijesh.screditprofile.Model.RequestModel.LoginRequest;
import com.example.brijesh.screditprofile.Model.ResponseModel.LoginResponse;
import com.example.brijesh.screditprofile.Network.HTTPWebRequest;
import com.example.brijesh.screditprofile.Utility.AppConstants;
import com.example.brijesh.screditprofile.Utility.Utility;
import com.example.brijesh.screditprofile.listner.ApiResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends BaseClassActivity implements ApiResponse {
    TextView textView;
    EditText email,password;
    LinearLayout login;
    Intent in;

    public static final String MyPREFERENCES = "MyData" ;
    public static final String Email = "username";
    public static final String Password = "userpassword";
    SharedPreferences s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView=(TextView)findViewById(R.id.registration);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,Registration.class);
                startActivity(i);
            }
        });
        email=(EditText)findViewById(R.id.acc_id);
        password=(EditText)findViewById(R.id.acc_id2);
        login=(LinearLayout) findViewById(R.id.signin);
      //  s = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginRequest req=new LoginRequest();
                req.setEmail(email.getText().toString());
                req.setPassword(password.getText().toString());
                HTTPWebRequest.Login(LoginActivity.this,req, AppConstants.APICode.Login,LoginActivity.this);


                String mail  = email.getText().toString();
                String pass  = password.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("UserLoggedIn", true);
                editor.putString(Email, mail);
                editor.putString(Password, pass);
                editor.commit();


             //   s= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Boolean login  = BaseClassActivity.sharedpreferences.getBoolean("UserLoggedIn",true);

                if (login == false) {

                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                   // startActivity(new Intent(LoginActivity.this, Language_selection.class));
                   // finish();
                }


                Toast.makeText(LoginActivity.this, "Thank", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        Gson gson=new Gson();
        if(response!=null || !response.equalsIgnoreCase(""))
        {
            LoginResponse loginResponse=gson.fromJson(response,LoginResponse.class);
            if(loginResponse.getStatus())
            {
                Utility.showToast(login,loginResponse.getMessage());
                Utility.RedirectToActivity(LoginActivity.this,Language_selection.class,true,null);
            }
            else
            {
                Utility.showToast(login,loginResponse.getMessage());
            }
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(String responseError, int apiCode) {

    }

    @Override
    public void responseError(String responseError, ArrayList<Integer> successedUrlList, int apiCode) {

    }
}
