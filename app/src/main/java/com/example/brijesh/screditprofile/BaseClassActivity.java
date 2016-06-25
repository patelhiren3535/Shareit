package com.example.brijesh.screditprofile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cognisun on 6/23/2016.
 */
public class BaseClassActivity extends AppCompatActivity {
    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyData" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
    }
}
