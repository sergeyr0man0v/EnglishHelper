package com.example.englishhelper1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.englishhelper1.R;
import com.example.englishhelper1.activities.StartActivity;
import com.example.englishhelper1.models.Word;

import java.util.ArrayList;

public class WordListAdapter extends ArrayAdapter<Word> {
    public WordListAdapter(Context context, ArrayList<Word> words) {
        super(context, R.layout.adapter_word_list_item, words);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Word word = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_word_list_item,
                    null);
        }

        //Заполнение адатера
        ((TextView) convertView.findViewById(R.id.word_list_item__en__tv)).setText(word.getEngValue());
        ((TextView) convertView.findViewById(R.id.word_list_item__ru__tv)).setText(word.getRuValue());



        ((Button) convertView.findViewById(R.id.word_list_item__volume__btn)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartActivity.speaker.volume(word.getEngValue());
                    }
                });



        return convertView;
    }
}
