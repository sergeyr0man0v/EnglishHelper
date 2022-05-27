package com.example.englishhelper1.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.example.englishhelper1.fragments.ResultDialog;
import com.example.englishhelper1.localDb.OpenHelper;
import com.example.englishhelper1.rest.ExternalData;

import java.util.ArrayList;
import java.util.Random;

public class LearningActivity extends AppCompatActivity implements ResultDialog.ResultDialogListener {

    private Section currentSection;
    private ArrayList<Word> words;
    private ArrayList<Word> notLearned;
    private TextView numberOfWords;
    private TextView sectionName;
    private ImageButton toSettingsBtn;
    private TextView enValue;
    private TextView ruValue;
    private Button volumeBtn;
    private Button nextBtn;
    private Word currentWord;

    private OpenHelper openHelper;

    Random random = new Random();
    boolean autoVolume = MyPreferences.mySettings.getBoolean(
            MyPreferences.APP_PREFERENCES_AUTO_VOLUME,true);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_activity);

        currentSection = getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        openHelper = new OpenHelper(this);

        words = openHelper.getWordsBySectionId(currentSection.getId());

        //////////////////////////////////////////////////////////////////
        notLearned = new ArrayList<>();

        for (Word word : words) {
            if (!word.isLearned())
                notLearned.add(word);
        }
        //////////////////////////////////////////////////////////////////
        if (notLearned.size() == 0){
            openHelper.updateWordLearnedStatusBySectionId(currentSection.getId(),false);
            notLearned = words;
        }else if (words.size() != notLearned.size()){
            ResultDialog resultDialog = new ResultDialog(1);
            resultDialog.show(getSupportFragmentManager(), "result_dialog1");
        }

        sectionName = findViewById(R.id.learning_activity__section_name_tv);
        sectionName.setText(currentSection.getName());

        toSettingsBtn = findViewById(R.id.learning_activity__settings_btn);
        toSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearningActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        currentWord = notLearned.get(random.nextInt(notLearned.size()));
        notLearned.remove(currentWord);

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

                openHelper.updateWordLearnedStatus(
                        currentWord.getId(),
                        true
                );

                if (!notLearned.isEmpty()){
                    currentWord = notLearned.get(random.nextInt(notLearned.size()));
                    notLearned.remove(currentWord);

                    fill(currentWord);
                } else {
                    ResultDialog resultDialog = new ResultDialog(2);
                    resultDialog.show(getSupportFragmentManager(), "result_dialog2");

                }
            }
        });

    }

    private void fill(Word currentWord){

        if (autoVolume){
            volume();
        }
        fillNumberTv();
        fillEnTv(currentWord.getEngValue());
        fillRuTv(currentWord.getRuValue());
    }

    private void fillNumberTv(){
        numberOfWords = findViewById(R.id.learning_activity__number_of_words__tv);
        numberOfWords.setText(words.size() - notLearned.size() + "/" + words.size());
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
        StartActivity.speaker.volume(currentWord.getEngValue());
    }

    @Override
    public void getResult(int value) {
        switch (value){
            case 0:
                openHelper.updateWordLearnedStatusBySectionId(currentSection.getId(), false);
                notLearned = words;
                notLearned.remove(currentWord);
                break;
            case 1:
                break;
            case 2:
                finish();
                break;
            case 3:
                Intent intent = new Intent(LearningActivity.this, TestActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra(MyPreferences.SELECTED_SECTION, currentSection);
                startActivity(intent);
        }
    }
}
