package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.localDb.OpenHelper;

import java.util.Locale;

public class SectionActivity extends AppCompatActivity {

    private Section currentSection;

    public static TextToSpeech textToSpeech;

    private OpenHelper openHelper;

    private TextView sectionNameTv;
    private ImageButton toSettingsBtn;
    private TextView numberOfWordsTv;
    private Button wordListBtn;
    private Button toLearningBtn;
    private TextView numberOfLearnedTv;
    private Button toTestBtn;

    @Override
    protected void onRestart() {
        //recreate();
        super.onRestart();
        fillTv();
    }

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

        openHelper = new OpenHelper(this);

        currentSection = (Section) getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        sectionNameTv = findViewById(R.id.section_activity__section_name__tv);
        sectionNameTv.setText(currentSection.getName());

        toSettingsBtn = findViewById(R.id.section_activity__settings_btn);
        toSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        numberOfWordsTv = findViewById(R.id.section_activity__number_of_words__tv);

        numberOfWordsTv.setText(String.valueOf(openHelper.getNumberOfWords(currentSection.getId())));

        wordListBtn = findViewById(R.id.section_activity__word_list__btn);
        wordListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this, WordListActivity.class);
                intent.putExtra(MyPreferences.SELECTED_SECTION, (Parcelable) currentSection);
                //intent.putExtra("words", data);
                //startActivityForResult(intent, 10);
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
                //intent.putExtra("words", data);
                startActivity(intent);
            }
        });

        numberOfLearnedTv = findViewById(R.id.section_activity__number_of_learned__tv);
        fillTv();

        toTestBtn = findViewById(R.id.section_activity__to_test__btn);
        toTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SectionActivity.this, TestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra(MyPreferences.SELECTED_SECTION, currentSection);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        textToSpeech.shutdown();
        super.onDestroy();
    }

    private void fillTv(){
        numberOfLearnedTv.setText(String.valueOf(
                openHelper.getNumberOfLearnedWords(currentSection.getId()) +
                        "/" + numberOfWordsTv.getText()));
    }

    public static void volume(String value){
        String utteranceId = String.valueOf(value.hashCode());
        SectionActivity.textToSpeech.speak(value, TextToSpeech.QUEUE_FLUSH,null, utteranceId);
    }
}
