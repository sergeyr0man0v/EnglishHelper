package com.example.englishhelper1.activities;

import android.content.Intent;
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
import com.example.englishhelper1.models.Module;
import com.example.englishhelper1.models.Section;
import com.example.englishhelper1.localDb.OpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class ModuleActivity extends AppCompatActivity {

    Random rnd = new Random();
    private Module currentModule;
    private TextView moduleNameTv;
    private ImageButton goToSettingsBtn;
    private ListView listView;

    private static SectionAdapter adapter;

    private OpenHelper openHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);

        openHelper = new OpenHelper(this);

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

        ArrayList<Section> data = openHelper.getSectionsByModuleId(currentModule.getId());

        //SectionAdapter adapter = new SectionAdapter(this, data);
        adapter = new SectionAdapter(this, data);
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

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    public static void updateAdapter(){
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }
}
