package com.example.englishhelper1.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.domain.Word;
import com.example.englishhelper1.rest.ExternalData;

import java.util.ArrayList;
import java.util.Random;

public class LearningActivity extends AppCompatActivity {

    private ArrayList<Word> words;
    TextView sectionName;
    ImageButton toSettingsBtn;
    TextView enValue;
    TextView ruValue;
    Button volumeBtn;
    Button nextBtn;
    Word currentWord;


    //int currentWord = 0;

    Random random = new Random();
    boolean autoVolume = MyPreferences.mySettings.getBoolean(
            MyPreferences.APP_PREFERENCES_AUTO_VOLUME,true);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_activity);

        Section section = getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        words = StartActivity.openHelper.getWordsBySectionId(section.getId());

        //Log.d("SECTION", String.valueOf(words.size()));
        sectionName = findViewById(R.id.learning_activity__section_name_tv);
        sectionName.setText(section.getName());

        toSettingsBtn = findViewById(R.id.learning_activity__settings_btn);
        toSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearningActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        currentWord = words.get(random.nextInt(words.size()));
        words.remove(currentWord);

        ///// заполнение полей для текущего слова
        fill(currentWord);

        volumeBtn = findViewById(R.id.learning_activity__volume_btn);
        volumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume();
            }
        });

        nextBtn = findViewById(R.id.learning_activity__next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!words.isEmpty()){
                    currentWord = words.get(random.nextInt(words.size()));
                    words.remove(currentWord);

                    fill(currentWord);
                } else {
                    Intent intent = new Intent(LearningActivity.this, ResultFragment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);

                }
            }
        });

    }

    private void fill(Word currentWord){

        if (autoVolume){
            volume();
        }

        fillEnTv(currentWord.getEngValue());
        fillRuTv(currentWord.getRuValue());

        StartActivity.openHelper.updateWordLearnedStatus(
                currentWord.getId(),
                true
        );
    }

    private void fillEnTv(String value){
        enValue = findViewById(R.id.learning_activity__enValue_tv);
        enValue.setText(value);
    }

    private void fillRuTv(String value){
        ruValue = findViewById(R.id.learning_activity__ruValue_tv);
        ruValue.setText(value);
    }

    private void volume(){
        SectionActivity.volume(currentWord.getEngValue());
    }

}
