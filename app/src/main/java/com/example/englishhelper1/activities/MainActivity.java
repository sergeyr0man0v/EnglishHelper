package com.example.englishhelper1.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.ModuleAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Module;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.domain.Word;

import java.io.Serializable;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences mySettings;

    TextView gradeTv;
    ListView listView;
    ImageButton goToSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mySettings = getSharedPreferences(MyPreferences.APP_PREFERENCES, Context.MODE_PRIVATE);


        if (!mySettings.contains(MyPreferences.APP_PREFERENCES_GRADE)) {
            Intent intent = new Intent(MainActivity.this, HelloActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        MyPreferences.settingEditor.putBoolean(MyPreferences.APP_PREFERENCES_IS_NEW_USER, false);

        gradeTv = (TextView) findViewById(R.id.main_activity__grade_tv);
        gradeTv.setText(String.valueOf(mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 0)) + " " + gradeTv.getText());

        goToSettingsBtn = (ImageButton) findViewById(R.id.main_activity__settings_btn);
        goToSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                //intent.putExtra(currentAct, MainActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////
        /////// заполнение данных для ListView
        ///////////////////////////////////////////////

        Module[] data = createTestData();

        ModuleAdapter adapter = new ModuleAdapter(this, data);
        listView = (ListView) findViewById(R.id.module_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                intent.putExtra(MyPreferences.SELECTED_MODULE, (Parcelable) data[i]);
                startActivity(intent);
            }
        });


    }

    static Module[] createTestData(){
        Module[] data = new Module[2];

        Section[] sections1 = new Section[2];

        sections1[0] = new Section("a", new Word[]{new Word("life", "жизнь"),
                new Word("beautiful", "красивый")});

        sections1[1] = new Section("b", new Word[]{new Word("cat", "кошка"),
                new Word("dog", "собака")});

        data[0] = new Module("Module 1", sections1);

        Section[] sections2 = new Section[3];

        sections2[0] = new Section("a", new Word[]{new Word("car", "машина"),
                new Word("airplane", "самолёт")});

        sections2[1] = new Section("b", new Word[]{new Word("house", "дом"),
                new Word("flat", "квартира")});

        sections2[2] = new Section("c", new Word[]{new Word("mother", "мама"),
                new Word("father", "папа")});

        data[1] = new Module("Module 2", sections2);

        return data;
    }
}
