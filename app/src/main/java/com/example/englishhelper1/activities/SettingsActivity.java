package com.example.englishhelper1.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;

import java.util.HashMap;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton okBtn;
    private TextView tv1;
    private ImageButton volumeBtn;

    private MyPreferences myPreferences;

    private boolean autoVolume = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        myPreferences = new MyPreferences(getApplicationContext());

        tv1 = (TextView) findViewById(R.id.settings_activity__grade_tv);
        tv1.setText(String.valueOf(MyPreferences.mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 0)));

        volumeBtn = (ImageButton) findViewById(R.id.settings_activity__volume_btn);
        if (!MyPreferences.mySettings.getBoolean(MyPreferences.APP_PREFERENCES_AUTO_VOLUME, true)) {
            volumeBtn.setImageResource(R.drawable.ic_volume_off_icon);
            autoVolume = false;
        }
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoVolume) {
                    volumeBtn.setImageResource(R.drawable.ic_volume_off_icon);
                } else {
                    volumeBtn.setImageResource(R.drawable.ic_volume_on_icon);
                }
                autoVolume = !autoVolume;
                MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_AUTO_VOLUME, autoVolume);
                MyPreferences.settingEditor.apply();
            }
        });


        HashMap<String, ImageButton> langBtns = new HashMap<>();

        langBtns.put("en", (ImageButton) findViewById(R.id.settings_activity__langUK_btn));
        langBtns.put("ru", (ImageButton) findViewById(R.id.settings_activity__langRU_btn));

        class LangChooseListener implements View.OnClickListener {
            private String lang;

            public LangChooseListener(String lang) {
                this.lang = lang;
            }

            @Override
            public void onClick(View view) {
                if (lang.equals("ru")) {
                    Toast.makeText(getApplicationContext(), "Выбор сделан!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Choice is made!", Toast.LENGTH_SHORT).show();
                }
                MyPreferences.settingEditor.putString(MyPreferences.APP_PREFERENCES_LANGUAGE, lang);
                MyPreferences.settingEditor.apply();

                Locale locale = new Locale(lang);
                Locale.setDefault(locale);
                Configuration configuration = new Configuration();
                configuration.locale = locale;
                getBaseContext().getResources().updateConfiguration(configuration, null);

            }
        }

        for (HashMap.Entry<String, ImageButton> entry : langBtns.entrySet()) {
            entry.getValue().setOnClickListener(new LangChooseListener(entry.getKey()));
        }

        okBtn = (ImageButton) findViewById(R.id.settings_activity__ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyPreferences.mySettings.getBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, true)) {
                    MyPreferences.settingEditor.putBoolean(
                            MyPreferences.APP_PREFERENCES_IS_NEW_USER, false
                    );
                    MyPreferences.settingEditor.apply();
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    SettingsActivity.super.onBackPressed();
                }
            }
        });
        /*Button clearBtn = findViewById(R.id.settings_activity__clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferences.settingEditor.clear();
                MyPreferences.settingEditor.apply();
            }
        });*/

    }
}
