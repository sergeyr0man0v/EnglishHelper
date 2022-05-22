package com.example.englishhelper1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.englishhelper1.R;


public class TestFragmentEt extends Fragment implements FragmentData{

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_et, container, false);

        editText = view.findViewById(R.id.test_fragment_et);

        return view;
    }

    @Override
    public String getData() {
        String value = editText.getText().toString().trim();

        return value;
    }
}