package com.example.englishhelper1.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.Speaker;
import com.example.englishhelper1.localDb.OpenHelper;

import java.util.Locale;

public class StartActivity extends AppCompatActivity {

    private OpenHelper openHelper;

    public static Speaker speaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        this.deleteDatabase("data.db");

        speaker = new Speaker(getApplicationContext());

        openHelper = new OpenHelper(getApplicationContext());


        MyPreferences myPreferences = new MyPreferences(getApplicationContext());

        MyPreferences.settingEditor.clear();
        MyPreferences.settingEditor.apply();
        String lang = MyPreferences.mySettings.getString(
                MyPreferences.APP_PREFERENCES_LANGUAGE, "ru"
        );

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);



        if (openHelper.getModules().isEmpty()) {
            MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, true);
            Intent intent = new Intent(this, HelloActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else{
            MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, false);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
