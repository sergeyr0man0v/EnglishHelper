package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.ModuleAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.localDb.OpenHelper;
import com.example.englishhelper1.models.Module;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView gradeTv;
    private ListView listView;
    private ImageButton goToSettingsBtn;

    private static ModuleAdapter moduleAdapter;

    private OpenHelper openHelper;

    //public static Speaker speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////
        ///объявление и инициализация TextToSpeech
        //////////////////////////////

        openHelper = new OpenHelper(this);

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

        ArrayList<Module> data = openHelper.getModules();

        //ModuleAdapter adapter = new ModuleAdapter(this, data);
        moduleAdapter = new ModuleAdapter(this, data);

        listView = (ListView) findViewById(R.id.module_list);
        listView.setAdapter(moduleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                //intent.putExtra(MyPreferences.SELECTED_MODULE, (Parcelable) data[i]);
                intent.putExtra(MyPreferences.SELECTED_MODULE, data.get(i));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    /*@Override
    protected void onDestroy() {
        speaker.shutdown();
        super.onDestroy();
    }*/

    public static void updateAdapter(){
        if (moduleAdapter != null)
            moduleAdapter.notifyDataSetChanged();
    }


}
