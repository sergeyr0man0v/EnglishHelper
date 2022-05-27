package com.example.englishhelper1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.localDb.OpenHelper;

public class StartActivity extends AppCompatActivity {

    private OpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.deleteDatabase("data.db");

        openHelper = new OpenHelper(this);


        MyPreferences myPreferences = new MyPreferences(this);

        MyPreferences.settingEditor.clear();
        MyPreferences.settingEditor.apply();

        //////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        MyPreferences.settingEditor.putInt(MyPreferences.APP_PREFERENCES_GRADE, 11);
        /////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        if (openHelper.getModules().isEmpty()) {
            MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, true);
            Intent intent = new Intent(this, HelloActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else{
            MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, false);
            Intent intent = new Intent(this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}