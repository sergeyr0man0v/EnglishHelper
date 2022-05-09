package com.example.englishhelper1.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.WordListAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Section;

public class WordListActivity extends AppCompatActivity {

    TextView sectionNameTv;
    ListView wordList;
    Section currentSection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list_activity);

        currentSection = (Section) getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

        sectionNameTv = findViewById(R.id.word_list__section_name__tv);
        sectionNameTv.setText(currentSection.getName());

        WordListAdapter wordListAdapter = new WordListAdapter(this, currentSection.getWords());
        wordList = findViewById(R.id.word_list);
        wordList.setAdapter(wordListAdapter);
    }
}
