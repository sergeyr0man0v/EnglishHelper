package com.example.englishhelper1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.englishhelper1.R;
import com.example.englishhelper1.models.Section;

import java.util.ArrayList;

public class SectionAdapter extends ArrayAdapter<Section> {


    public SectionAdapter(Context context, ArrayList<Section> sections) {
        super(context, R.layout.adapter_section_item, sections);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Section section = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_section_item, null);
        }

        //Заполнение адатера
        ((TextView) convertView.findViewById(R.id.section_item_name)).setText(section.getName());
        ((TextView) convertView.findViewById(R.id.section_item__description__tv)).setText(section.getDescription());

        ((ProgressBar) convertView.findViewById(R.id.section_item__progressBar)).setProgress(section.getProgress());
        ((TextView) convertView.findViewById(R.id.section_item__progress_tv)).setText(String.valueOf(section.getProgress()) + "%");
        /*progressBar = ((ProgressBar) convertView.findViewById((R.id.section_item_progressBar)));
        progressBar.setProgress(section.getProgress());
        ((ProgressBar) convertView.findViewById(R.id.progressBar1)).setProgress(section.getProgress());
        ((TextView) convertView.findViewById(R.id.tv_progress_circle)).setText(String.valueOf(section.getProgress()) + "%");*/

        return convertView;

    }
}
