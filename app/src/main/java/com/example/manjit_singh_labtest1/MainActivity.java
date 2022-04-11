package com.example.manjit_singh_labtest1;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button register,clear,display,overwrite;
    EditText email,password,security;
    Switch swi;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button) findViewById(R.id.registerButton);
        clear = (Button) findViewById(R.id.clearButton);
        display = (Button) findViewById(R.id.displayButton);
        overwrite = (Button) findViewById(R.id.overwriteButton);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        security = (EditText) findViewById(R.id.editTextSecurity);
        preferences = getSharedPreferences("user_data",MODE_PRIVATE);
        swi = findViewById(R.id.switch1);

        register.setOnClickListener(view -> {
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();
            String strSecurity = security.getText().toString();

            if(strEmail.equals("") || strPassword.equals("") || strSecurity.equals("")){
                Toast.makeText(getApplicationContext(),"Required Fields!!",Toast.LENGTH_SHORT).show();
            }
            else{
                if(swi.isChecked()){
                    saveDataIntoSqlite(strEmail,strPassword,strSecurity);
                }
                else{
                    saveDataIntoSharedPref(strEmail,strPassword,strSecurity);
                }
            }
        });
        clear.setOnClickListener(view -> {
            email.setText("");
            password.setText("");
            security.setText("");
        });
        display.setOnClickListener(view -> {
            if(swi.isChecked()){
                //saveDataIntoSqlite(strEmail,strPassword,strSecurity);
            }
            else{
                getDataFromSharedPref();
            }

        });
        overwrite.setOnClickListener(view -> {
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();
            String strSecurity = security.getText().toString();

            if(strEmail.equals("") || strPassword.equals("") || strSecurity.equals("")){
                Toast.makeText(getApplicationContext(),"Required Fields!!",Toast.LENGTH_SHORT).show();
            }
            if(swi.isChecked()){

            }
            else{
                saveDataIntoSharedPref(strEmail,strPassword,strSecurity);
            }
        });

        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    swi.setText("Sql database");


                } else {
                    swi.setText("Shared preference");

                }

            }
        });


    }

    private void getDataFromSharedPref() {
        email.setText(preferences.getString("email",""));
        password.setText(preferences.getString("password",""));
        security.setText(preferences.getString("security",""));
    }

    private void saveDataIntoSqlite(String strEmail, String strPassword, String strSecurity) {
    }

    private void saveDataIntoSharedPref(String strEmail, String strPassword, String strSecurity) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email",strEmail);
        editor.putString("password",strPassword);
        editor.putString("security",strSecurity);
        editor.apply();
        Toast.makeText(getApplicationContext(),"User is registered !!",Toast.LENGTH_SHORT).show();
        clear.performClick();
    }
}