package com.example.englishhelper1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.englishhelper1.domain.Module;
import com.example.englishhelper1.R;

public class ModuleAdapter extends ArrayAdapter<Module> {
    public ModuleAdapter(Context context, Module[] modules) {
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



        return convertView;

    }

}
