package com.example.englishhelper1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.englishhelper1.models.Module;
import com.example.englishhelper1.R;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<Module> {
    public ModuleAdapter(Context context, ArrayList<Module> modules) {
        super(context, R.layout.adapter_module_item, modules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Module module = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_module_item, null);
        }

        //Заполнение адатера
        ((TextView) convertView.findViewById(R.id.module_item_name)).setText(module.getName());
        ((TextView) convertView.findViewById(R.id.module_item__description__tv)).setText(module.getDescription());



        return convertView;

    }

}
