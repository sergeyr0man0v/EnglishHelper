package com.example.englishhelper1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.englishhelper1.R;
import com.example.englishhelper1.activities.StartActivity;
import com.example.englishhelper1.models.Mistake;

import java.util.List;

public class MistakesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Mistake> mistakes;
    private final LayoutInflater inflater;

    public MistakesAdapter(Context context, List<Mistake> mistakes){
        this.mistakes = mistakes;
        this.inflater = LayoutInflater.from(context);
    }


    private class MyHolder extends RecyclerView.ViewHolder{

        private TextView tvWord, tvAnswer, tvCorrectAnswer;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvWord = itemView.findViewById(R.id.mistake_item__word_tv);
            tvAnswer = itemView.findViewById(R.id.mistake_item__wrong_answer__tv);
            tvCorrectAnswer = itemView.findViewById(R.id.mistake_item__correct_answer__tv);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.adapter_mistake_item, viewGroup, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Mistake mistake = mistakes.get(i);

        String answer = mistake.getAnswer();
        if (answer.equals("")){
            answer = "-";
        }

        ((MyHolder) viewHolder).tvWord.setText(mistake.getWord());
        ((MyHolder) viewHolder).tvAnswer.setText(answer);
        ((MyHolder) viewHolder).tvCorrectAnswer.setText(mistake.getCorrectAnswer());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity.speaker.volume(mistake.getCorrectAnswer());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mistakes.size();
    }
}
