package com.example.englishhelper1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.englishhelper1.Adapters.MistakesAdapter;
import com.example.englishhelper1.MyPreferences;
import com.example.englishhelper1.R;
import com.example.englishhelper1.models.Mistake;
import com.example.englishhelper1.models.Section;

import java.util.ArrayList;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {


    private TextView resultDescriptionTv;
    private TextView resultTv;
    private ImageView img;

    private Button viewMistakesBtn;
    private Button tryAgainBtn;
    private RecyclerView recyclerView;
    private MistakesAdapter adapter;

    private int result;
    private Section section;

    private boolean opened = false;

    private ArrayList<Mistake> mistakes;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = getIntent().getIntExtra(MyPreferences.RESULT_VALUE, 3);

        mistakes = getIntent().getParcelableArrayListExtra(MyPreferences.WRONG_ANSWERS);

        //section = getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);

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
                int weight;
                if (opened){
                    weight = 0;
                } else {
                    weight = 100;
                }
                opened = !opened;
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        weight
                );
                recyclerView.setLayoutParams(param);
            }
        });

        tryAgainBtn = findViewById(R.id.result_activity__try_again__btn);
        if (result == 100){
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0
            );
            tryAgainBtn.setLayoutParams(param);
        }
        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, TestActivity.class);
                section = getIntent().getParcelableExtra(MyPreferences.SELECTED_SECTION);
                intent.putExtra(MyPreferences.SELECTED_SECTION, section);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                /*getParent().recreate();
                finish();*/

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
