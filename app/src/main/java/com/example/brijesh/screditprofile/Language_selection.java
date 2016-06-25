package com.example.brijesh.screditprofile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brijesh.screditprofile.Model.RequestModel.LanguageRequest;
import com.example.brijesh.screditprofile.Model.ResponseModel.LanguageResponse;
import com.example.brijesh.screditprofile.Network.HTTPWebRequest;
import com.example.brijesh.screditprofile.Utility.AppConstants;
import com.example.brijesh.screditprofile.listner.ApiResponse;
import com.google.gson.Gson;

import java.util.ArrayList;


public class Language_selection extends Activity implements ApiResponse,AdapterView.OnItemSelectedListener {
    Spinner spinnerOsversions;
    Button b;
    ArrayAdapter<String> adapter_state;
    SharedPreferences sharedPreferences;
    private String[] state = {"English"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        b=(Button)findViewById(R.id.Language_select);
        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        HTTPWebRequest.Language(Language_selection.this, AppConstants.APICode.Language,Language_selection.this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              SharedPreferences.Editor editor = sharedPreferences.edit();
//
//
//                editor.putString("password",Language_selection.getText().toString());
//
//               editor.commit();
                Intent i= new Intent(getApplicationContext(),NavigationDrawer.class);
                startActivity(i);
                finish();


            }
        });
        System.out.println(state.length);
        spinnerOsversions = (Spinner) findViewById(R.id.osversions);
        adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);
        spinnerOsversions.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        SharedPreferences.Editor editor = sharedPreferences.edit();


//        editor.putString("Language_Selection",Language_selection.getText().toString());

        editor.commit();


    }




    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        spinnerOsversions.setSelection(position);
        String selState = (String) spinnerOsversions.getSelectedItem();
        Toast.makeText(getApplicationContext(),"you select\t"+selState,Toast.LENGTH_LONG).show();
        //selVersion.setText("Selected Android OS:" + selState);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        Gson gson=new Gson();
        if(response!=null || !response.equalsIgnoreCase(""))
        {
            LanguageResponse languageResponse=gson.fromJson(response,LanguageResponse.class);
            if(languageResponse.isStatus())
            {
                ArrayList<String> Language_name=new ArrayList<>();
                for(int i=0;i<languageResponse.getLanguage().size();i++)
                {
                    Log.d("data",languageResponse.getLanguage().get(i).getLanguagename()+"");
                    Language_name.add(i,languageResponse.getLanguage().get(i).getLanguagename()+"");
                }
                adapter_state = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, Language_name);
                spinnerOsversions.setAdapter(adapter_state);
                adapter_state.notifyDataSetChanged();
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


