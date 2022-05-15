package com.example.englishhelper1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.englishhelper1.R;

public class TestActivity extends AppCompatActivity {

    TextView sectionName;
    TextView currentWord;
    RadioGroup radioGroup;
    ImageButton applyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);



    }
}
