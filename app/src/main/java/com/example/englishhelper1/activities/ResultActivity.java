package com.example.englishhelper1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.MistakesAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.domain.Mistake;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {


    private TextView resultDescriptionTv;
    private TextView resultTv;
    private ImageView img;

    private Button viewMistakesBtn;
    private RecyclerView recyclerView;
    private MistakesAdapter adapter;

    private int result;

    private ArrayList<Mistake> mistakes;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = getIntent().getIntExtra(MyPreferences.RESULT_VALUE, 3);

        mistakes = getIntent().getParcelableArrayListExtra(MyPreferences.WRONG_ANSWERS);

        resultDescriptionTv = findViewById(R.id.result_activity__result_description__tv);
        img = findViewById(R.id.result_activity__img);

        resultTv = findViewById(R.id.result_activity__result__tv);
        resultTv.setText(resultTv.getText().toString() + result + "%");

        fillViews();

        recyclerView = findViewById(R.id.result_activity__rv);
        adapter = new MistakesAdapter(ResultActivity.this, mistakes);
        recyclerView.setAdapter(adapter);

        viewMistakesBtn = findViewById(R.id.result_activity__mistakes__btn);
        viewMistakesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        100
                );
                recyclerView.setLayoutParams(param);
            }
        });
    }

    private void fillViews(){
        int i = random.nextInt(3);
        if (result >= 85){
            resultDescriptionTv.setText(getResources().getString(
                    R.string.result_activity__perf_res_descripit));
            switch (i){
                case 0:
                    img.setImageResource(R.drawable.ic_result_activity__perf1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.ic_result_activity__perf2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.ic_result_activity__perf3);
            }

        }else if (result >= 65){
            resultDescriptionTv.setText(getResources().getString(
                    R.string.result_activity__good_res_descripit));
            switch (i){
                case 0:
                    img.setImageResource(R.drawable.ic_result_activity__good1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.ic_result_activity__good2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.ic_result_activity__good3);
            }
        }else if (result >= 45){
            resultDescriptionTv.setText(getResources().getString(
                    R.string.result_activity__normal_res_descripit));
            switch (i){
                case 0:
                    img.setImageResource(R.drawable.ic_result_activity__normal1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.ic_result_activity__normal2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.ic_result_activity__normal3);
            }
        }else{
            resultDescriptionTv.setText(getResources().getString(
                    R.string.result_activity__bad_res_descripit));
            switch (i){
                case 0:
                    img.setImageResource(R.drawable.ic_result_activity__bad1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.ic_result_activity__bad2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.ic_result_activity__bad3);
            }
        }
    }
}