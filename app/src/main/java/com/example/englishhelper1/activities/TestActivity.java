package com.example.englishhelper1.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Mistake;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.domain.Word;
import com.example.englishhelper1.fragments.FragmentData;
import com.example.englishhelper1.fragments.TestFragmentEt;
import com.example.englishhelper1.fragments.TestFragmentRg;
import com.example.englishhelper1.localDb.OpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    private TextView sectionNameTv;
    private TextView currentWordTv;
    private Fragment fragment;
    private ImageButton applyBtn;

    private Section currentSection;
    private ArrayList<Word> allWords;

    private ArrayList<Word> notAnswered;

    private Word currentWord;

    private int correct;

    private FragmentData fragmentData;

    private OpenHelper openHelper;

    private Random random = new Random();

    private ArrayList<Mistake> mistakes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        currentSection = getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);
        sectionNameTv = findViewById(R.id.test_activity__section_name_tv);
        sectionNameTv.setText(currentSection.getName());

        openHelper = new OpenHelper(this);

        allWords = openHelper.getWordsBySectionId(currentSection.getId());

        notAnswered = new ArrayList<>(allWords);
        mistakes = new ArrayList<>();

        currentWord = notAnswered.get(random.nextInt(notAnswered.size()));
        notAnswered.remove(currentWord);
        fillViews(currentWord);


        applyBtn = findViewById(R.id.test_activity__apply_btn);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentData = (FragmentData) getSupportFragmentManager()
                        .findFragmentById(R.id.test_activity__fl);
                String selected = fragmentData.getData();
                if (currentWord.getEngValue().equals(selected)){
                    correct++;
                    Log.d("TEST", String.valueOf(correct));
                } else {
                    mistakes.add(new Mistake(
                            currentWord.getRuValue(),
                            currentWord.getEngValue(),
                            selected
                    ));
                }
                if (!notAnswered.isEmpty()){
                    currentWord = notAnswered.get(random.nextInt(notAnswered.size()));
                    notAnswered.remove(currentWord);
                    fillViews(currentWord);
                } else {
                    int result = Math.round(100 * correct/allWords.size());
                    if (result > currentSection.getProgress()){
                        openHelper.updateSectionProgress(
                                currentSection.getId(),
                                result);
                    }

                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    intent.putExtra(MyPreferences.RESULT_VALUE, result);
                    intent.putExtra(MyPreferences.WRONG_ANSWERS, mistakes);
                    startActivity(intent);
                }
            }
        });

    }

    private void fillViews(Word current){
        fillTv(current.getRuValue());

        if (notAnswered.size() % 3 == 0){
            fillFragmentEt();
        } else {
            HashSet<String> fragmentWords = new HashSet<>();
            fragmentWords.add(current.getEngValue());
            while (fragmentWords.size() != 4) {
                fragmentWords.add(allWords.get(random.nextInt(allWords.size())).getEngValue());
            }

            ArrayList<String> words = new ArrayList<>(fragmentWords);

            fillFragmentRg(words);
        }
    }

    private void fillTv(String value){
        currentWordTv = findViewById(R.id.test_activity__current_word_tv);
        currentWordTv.setText(value);
    }
    private void fillFragmentRg(ArrayList<String> words){

        deleteFragment();

        fragment = TestFragmentRg.newInstance(words);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.test_activity__fl, fragment)
                .commit();
    }

    private void fillFragmentEt(){
        deleteFragment();

        fragment = new TestFragmentEt();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.test_activity__fl, fragment)
                .commit();
    }

    private void deleteFragment(){
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        int size = fragmentList.size();
        if (size > 0){
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragmentList.get(size - 1))
                    .commit();
        }
    }
}
