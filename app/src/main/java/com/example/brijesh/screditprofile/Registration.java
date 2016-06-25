package com.example.brijesh.screditprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.brijesh.screditprofile.Model.RequestModel.LoginRequest;
import com.example.brijesh.screditprofile.Model.RequestModel.RegistrationRequest;
import com.example.brijesh.screditprofile.Model.ResponseModel.LanguageResponse;
import com.example.brijesh.screditprofile.Model.ResponseModel.LoginResponse;
import com.example.brijesh.screditprofile.Model.ResponseModel.RegistrationResponse;
import com.example.brijesh.screditprofile.Network.HTTPWebRequest;
import com.example.brijesh.screditprofile.Utility.AppConstants;
import com.example.brijesh.screditprofile.Utility.Utility;
import com.example.brijesh.screditprofile.listner.ApiResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Registration extends AppCompatActivity implements ApiResponse{

    TextView t;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        t=(TextView)findViewById(R.id.submit);
        e1=(EditText)findViewById(R.id.fname);
        e2=(EditText)findViewById(R.id.lname);
        e3=(EditText)findViewById(R.id.number);
        e4=(EditText)findViewById(R.id.mail);
        e5=(EditText)findViewById(R.id.dob);
        e6=(EditText)findViewById(R.id.latitude);
        e7=(EditText)findViewById(R.id.longitude);
        e8=(EditText)findViewById(R.id.pass);
        e9=(EditText)findViewById(R.id.devicetype);
        e10=(EditText)findViewById(R.id.image);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegistrationRequest req=new RegistrationRequest();
                req.setFname(e1.getText().toString());
                req.setLname(e2.getText().toString());
                req.setNumber(e3.getText().toString());
                req.setMail(e4.getText().toString());
                req.setDob(e5.getText().toString());
                req.setLattitude(e6.getText().toString());
                req.setLongitud(e7.getText().toString());
                req.setPass(e8.getText().toString());
                req.setDevicetype(e9.getText().toString());
                req.setImage(e10.getText().toString());
                HTTPWebRequest.Registration(Registration.this,req,AppConstants.APICode.REGISTER,Registration.this);
            }
        });
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        Gson gson=new Gson();
        if(response!=null || !response.equalsIgnoreCase(""))
        {
            RegistrationResponse rs=gson.fromJson(response,RegistrationResponse.class);
            if(rs.isStatus())
            {
               Utility.showToast(t,RegistrationResponse.getMessage());
                Utility.RedirectToActivity(Registration.this, Language_selection.class,true,null);
            }
            else
            {
                Utility.showToast(t,RegistrationResponse.getMessage());
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newfeed, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_notification) {
            Intent i=new Intent(Registration.this,Language_selection.class);
            startActivity(i);
            return true;
        }
//        else if (id == R.id.menu_post) {
//            Intent i=new Intent(NavigationDrawer.this,LoginActivity.class);
//            startActivity(i);
//        }
        return super.onOptionsItemSelected(item);
    }
}
