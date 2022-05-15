package com.example.englishhelper1.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.englishhelper1.localDb.OpenHelper;
import com.example.englishhelper1.rest.ExternalData;
import com.example.englishhelper1.rest.ExternalDatabaseService;
import com.example.englishhelper1.rest.ServerApiVolley;
import com.example.englishhelper1.rest.models.GradeDescription;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView gradeTv;
    ListView listView;
    ImageButton goToSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ExternalData.modules.isEmpty()) {
            ServerApiVolley serverApiVolley = new ServerApiVolley(this);
            serverApiVolley.fillGrade(11);
            this.recreate();
        }

        setContentView(R.layout.activity_main);

        gradeTv = (TextView) findViewById(R.id.main_activity__grade_tv);
        //gradeTv.setText(String.valueOf(StartActivity.mySettings.getInt(MyPreferences.APP_PREFERENCES_GRADE, 0)) + " " + gradeTv.getText());
        gradeTv.setText(String.valueOf("11 " + gradeTv.getText()));

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

        //Module[] data = createTestData();
        Module[] data = new Module[ExternalData.modules.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = ExternalData.modules.get(i);
        }

        //ModuleAdapter adapter = new ModuleAdapter(this, data);
        ModuleAdapter adapter = new ModuleAdapter(this, data);

        listView = (ListView) findViewById(R.id.module_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                //intent.putExtra(MyPreferences.SELECTED_MODULE, (Parcelable) data[i]);
                intent.putExtra(MyPreferences.SELECTED_MODULE, data[i]);
                startActivity(intent);
            }
        });

    }

    static class DataLoad extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            //ExternalData.getDataFromExternalDb();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            super.onPostExecute(unused);
        }
    }

    /*static Module[] createTestData(){
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
    }*/

}
