package com.example.englishhelper1.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;

import java.util.HashMap;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    ImageButton okBtn;

    public static boolean autoVolume = true;
    public static String language = "ru";

    //public static final String currentAct = "PREV_ACTIVITY"

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        TextView tv1 = (TextView) findViewById(R.id.settings_activity__grade_tv);
        tv1.setText(String.valueOf(MainActivity.mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 0)));


        ImageButton volumeBtn = (ImageButton) findViewById(R.id.settings_activity__volume_btn);
        if (!MainActivity.mySettings.getBoolean(MyPreferences.APP_PREFERENCES_AUTO_VOLUME, true)){
            volumeBtn.setImageResource(R.drawable.ic_volume_off_icon);
        }
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoVolume){
                    autoVolume = false;
                    volumeBtn.setImageResource(R.drawable.ic_volume_off_icon);
                    MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_AUTO_VOLUME, false);
                    MyPreferences.settingEditor.apply();
                } else {
                    autoVolume = true;
                    volumeBtn.setImageResource(R.drawable.ic_volume_on_icon);
                    MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_AUTO_VOLUME, true);
                    MyPreferences.settingEditor.apply();
                }
            }
        });



        HashMap<String, ImageButton> langBtns = new HashMap<>();

        langBtns.put("uk", (ImageButton) findViewById(R.id.settings_activity__langUK_btn));
        langBtns.put("ru", (ImageButton) findViewById(R.id.settings_activity__langRU_btn));

        class LangChooseListener implements View.OnClickListener {
            private String lang;

            public LangChooseListener(String lang) {
                this.lang = lang;
            }
            @Override
            public void onClick(View view) {
                language = lang;
                if (lang.equals("ru")) {
                    MyPreferences.settingEditor.putString(MyPreferences.APP_PREFERENCES_LANGUAGE, "ru");
                    MyPreferences.settingEditor.apply();
                    Toast.makeText(getApplicationContext(), "Выбор сделан!", Toast.LENGTH_SHORT).show();
                } else{
                    MyPreferences.settingEditor.putString(MyPreferences.APP_PREFERENCES_LANGUAGE, "en");
                    MyPreferences.settingEditor.apply();
                    Toast.makeText(getApplicationContext(), "Choice is made!", Toast.LENGTH_SHORT).show();
                }
                if (MainActivity.mySettings.getString(MyPreferences.APP_PREFERENCES_LANGUAGE, "ru").equals("ru")){
                    Locale locale = new Locale("ru");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getBaseContext().getResources().updateConfiguration(configuration, null);
                } else {
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getBaseContext().getResources().updateConfiguration(configuration, null);
                }
            }
        }

        for(HashMap.Entry<String, ImageButton> entry : langBtns.entrySet()){
            entry.getValue().setOnClickListener(new LangChooseListener(entry.getKey()));
        }

        okBtn = (ImageButton) findViewById(R.id.settings_activity__ok_btn);

        if (MainActivity.mySettings.getBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, true)) {
            okBtn.setVisibility(View.VISIBLE);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    //(Class<?>) getIntent().getSerializableExtra(SettingsActivity.currentAct));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                }
            });
        }
        Button clearBtn = findViewById(R.id.settings_activity__clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferences.settingEditor.clear();
                MyPreferences.settingEditor.apply();
            }
        });

    }
}
