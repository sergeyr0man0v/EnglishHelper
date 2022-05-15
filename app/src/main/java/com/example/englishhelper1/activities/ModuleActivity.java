package com.example.englishhelper1.activities;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.SectionAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Module;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.localDb.OpenHelper;
import com.example.englishhelper1.rest.ExternalData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ModuleActivity extends AppCompatActivity {

    final Random rnd = new Random();
    Module currentModule;
    TextView moduleNameTv;
    ImageButton goToSettingsBtn;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);

        currentModule = (Module) getIntent().getParcelableExtra(MyPreferences.SELECTED_MODULE);

        moduleNameTv = findViewById(R.id.module_activity__module_name_tv);
        moduleNameTv.setText(currentModule.getName());

        goToSettingsBtn = (ImageButton) findViewById(R.id.module_activity__settings_btn);
        goToSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModuleActivity.this, SettingsActivity.class);
                //intent.putExtra(currentAct, ModuleActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Section> data = StartActivity.openHelper.getSectionsByModuleId(currentModule.getId());

        //SectionAdapter adapter = new SectionAdapter(this, data);
        SectionAdapter adapter = new SectionAdapter(this, data);
        listView = (ListView) findViewById(R.id.section_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ModuleActivity.this, SectionActivity.class);
                //intent.putExtra(MyPreferences.SELECTED_SECTION, (Parcelable) data[i]);
                intent.putExtra(MyPreferences.SELECTED_SECTION, (Parcelable) data.get(i));
                startActivity(intent);
            }
        });


    }
}
