package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Section;

import java.io.Serializable;
import java.util.Locale;

public class SectionActivity extends AppCompatActivity {

    Section currentSection;

    public static TextToSpeech textToSpeech;
    TextView sectionNameTv;
    TextView numberOfWordsTv;
    Button wordListBtn;
    Button toLearningBtn;
    TextView numberOfLearnedTv;
    Button toTestBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_activity);

        //////////////////////////////
        ///объявление и инициализация TextToSpeech
        //////////////////////////////

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override public void onInit(int initStatus) {
                if (initStatus == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                    textToSpeech.setPitch(1.3f);
                    textToSpeech.setSpeechRate(0.7f);
                }
            }
        });;

        currentSection = (Section) getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        sectionNameTv = findViewById(R.id.section_activity__section_name__tv);
        sectionNameTv.setText(currentSection.getName());

        numberOfWordsTv = findViewById(R.id.section_activity__number_of_words__tv);
        numberOfWordsTv.setText(String.valueOf(currentSection.getWords().length));

        wordListBtn = findViewById(R.id.section_activity__word_list__btn);
        wordListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this, WordListActivity.class);
                intent.putExtra(MyPreferences.SELECTED_SECTION, (Parcelable) currentSection);
                startActivity(intent);
            }
        });

        toLearningBtn = findViewById(R.id.section_activity__to_learning__btn);
        toLearningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this, LearningActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra(MyPreferences.SELECTED_SECTION, currentSection);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textToSpeech.shutdown();
    }
}
