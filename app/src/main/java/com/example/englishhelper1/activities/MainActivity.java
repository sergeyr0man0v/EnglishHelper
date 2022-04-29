package com.example.englishhelper1.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.ModuleAdapter;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Module;

public class MainActivity extends AppCompatActivity {



    public static final String currentAct = "PREV_ACTIVITY";
    public static final String selectedModule = "MODULE_NAME";

    TextView gradeTv;
    ListView listView;
    ImageButton goToSettingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirstEntranceActivity.selectedGrade == null) {
            Intent intent = new Intent(MainActivity.this, HelloActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        gradeTv = (TextView) findViewById(R.id.main_activity__grade_tv);
        gradeTv.setText(String.valueOf(FirstEntranceActivity.selectedGrade) + " класс");

        goToSettingsBtn = (ImageButton) findViewById(R.id.main_activity__settings_btn);
        goToSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.putExtra(currentAct, MainActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////////////////////////////
        /////// заполнение данных для ListView
        ///////////////////////////////////////////////
        Module[] data = new Module[7];

        for (int i = 0; i < data.length; i++){
            data[i] = new Module("Module" + ' ' + (i + 1));
        }


        ModuleAdapter adapter = new ModuleAdapter(this, data);
        listView = (ListView) findViewById(R.id.module_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                intent.putExtra(selectedModule, data[i].getName());
                startActivity(intent);
            }
        });


    }
}
