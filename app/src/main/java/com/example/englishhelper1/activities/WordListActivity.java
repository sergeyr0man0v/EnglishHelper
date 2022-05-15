package com.example.englishhelper1.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.WordListAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Section;
import com.example.englishhelper1.domain.Word;
import com.example.englishhelper1.rest.ExternalData;

import java.util.ArrayList;

public class WordListActivity extends AppCompatActivity {

    TextView sectionNameTv;
    ListView wordList;
    Section currentSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_activity);

        currentSection = (Section) getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        /*ArrayList<Word> words = new ArrayList<>();

        for (Word word : ExternalData.words) {
            if(word.getSectionId() == currentSection.getId())
                words.add(word);

        }

        Word[] data = new Word[words.size()];
        for (int i = 0; i < words.size(); i++) {
            data[i] = words.get(i);
        }*/

        ArrayList<Word> data = StartActivity.openHelper.getWordsBySectionId(currentSection.getId());


        sectionNameTv = findViewById(R.id.word_list__section_name__tv);
        sectionNameTv.setText(currentSection.getName());

        WordListAdapter wordListAdapter = new WordListAdapter(this, data);
        wordList = findViewById(R.id.word_list);
        wordList.setAdapter(wordListAdapter);
    }
}
