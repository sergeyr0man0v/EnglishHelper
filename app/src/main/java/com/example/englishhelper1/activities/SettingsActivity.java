package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishhelper1.R;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    ImageButton okBtn;

    public static boolean autoVolume = true;
    public static String language = "ru";

    public static final String currentAct = "PREV_ACTIVITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);



        TextView tv1 = (TextView) findViewById(R.id.settings_activity__grade_tv);
        tv1.setText(String.valueOf(FirstEntranceActivity.selectedGrade));


        ImageButton volumeBtn = (ImageButton) findViewById(R.id.settings_activity__volume_btn);
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoVolume){
                    autoVolume = false;
                    volumeBtn.setImageResource(R.drawable.ic_volume_off_icon);
                } else {
                    autoVolume = true;
                    volumeBtn.setImageResource(R.drawable.ic_volume_on_icon);
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
                if (lang.equals("ru"))
                    Toast.makeText(getApplicationContext(), "Выбор сделан!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Choice is made!", Toast.LENGTH_SHORT).show();
            }
        }

        for(HashMap.Entry<String, ImageButton> entry : langBtns.entrySet()){
            entry.getValue().setOnClickListener(new LangChooseListener(entry.getKey()));
        }


        okBtn = (ImageButton) findViewById(R.id.settings_activity__ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, (Class<?>) getIntent().getSerializableExtra(SettingsActivity.currentAct));
                startActivity(intent);
            }
        });

    }
}
