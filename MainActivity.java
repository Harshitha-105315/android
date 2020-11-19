package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static String  PREFS_NAME="mypre";
    public static String PREF_USERNAME="username";
    public static String PREF_PASSWORD="password";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);



        return true;

    }*/

    public void onStart(){
        super.onStart();
        //read username and password from SharedPreferences
        getUser();
    }

    public void doLogin(View view){
        EditText txtuser=(EditText)findViewById(R.id.txt_user);
        EditText txtpwd=(EditText)findViewById(R.id.txt_pwd);
        String username="myusername";
        String password="mypassword";
        if(txtuser.getText().toString().equals(username) && txtpwd.getText().toString().equals(password)){
            CheckBox ch=(CheckBox)findViewById(R.id.ch_rememberme);
            if(ch.isChecked())
                rememberMe(username,password); //save username and password
            //show logout activity
            showLogout(username);

        }
        else{
            Toast.makeText(this, "Invalid username or password",Toast.LENGTH_LONG).show();
        }


    }

    public void getUser(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (username != null || password != null) {
            //directly show logout form
            showLogout(username);
        }
    }

    public void rememberMe(String user, String password){
        //save username and password in SharedPreferences
        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME,user)
                .putString(PREF_PASSWORD,password)
                .commit();
    }

    public void showLogout(String username){
        //display log out activity
        Intent intent=new Intent(this, SecondActivity.class);
        intent.putExtra("user",username);
        startActivity(intent);
    }





}

