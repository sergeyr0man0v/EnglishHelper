package com.example.englishhelper1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.englishhelper1.R;

import java.util.ArrayList;


public class TestFragmentRg extends Fragment implements FragmentData {

    private static final String ARG_WORD_1 = "word1";
    private static final String ARG_WORD_2 = "word2";
    private static final String ARG_WORD_3 = "word3";
    private static final String ARG_WORD_4 = "word4";


    /*private static final String ARG_FAKE_WORD_1 = "fakeWord1";
    private static final String ARG_FAKE_WORD_2 = "fakeWord2";
    private static final String ARG_FAKE_WORD_3 = "fakeWord3";*/

    private RadioGroup radioGroup;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private String word1;
    private String word2;
    private String word3;
    private String word4;

    public TestFragmentRg() {
        // Required empty public constructor
    }

    public static TestFragmentRg newInstance(ArrayList<String> words) {
        TestFragmentRg fragment = new TestFragmentRg();
        Bundle args = new Bundle();
        args.putString(ARG_WORD_1, words.get(0));
        args.putString(ARG_WORD_2, words.get(1));
        args.putString(ARG_WORD_3, words.get(2));
        args.putString(ARG_WORD_4, words.get(3));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word1 = getArguments().getString(ARG_WORD_1);
            word2 = getArguments().getString(ARG_WORD_2);
            word3 = getArguments().getString(ARG_WORD_3);
            word4 = getArguments().getString(ARG_WORD_4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_rg, container, false);

        radioGroup = view.findViewById(R.id.fragment_test1__rg);

        radioButton1 = radioGroup.findViewById(R.id.fragment_test1__rb1);
        radioButton2 = radioGroup.findViewById(R.id.fragment_test1__rb2);
        radioButton3 = radioGroup.findViewById(R.id.fragment_test1__rb3);
        radioButton4 = radioGroup.findViewById(R.id.fragment_test1__rb4);

        radioButton1.setText(word1);
        radioButton2.setText(word2);
        radioButton3.setText(word3);
        radioButton4.setText(word4);


        return view;
    }

    @Override
    public String getData() {
        String selected;
        int id = radioGroup.getCheckedRadioButtonId();
        if (id != -1) {
            RadioButton chosen = radioGroup.findViewById(id);
            selected = chosen.getText().toString();
        } else {
            selected = "";
        }
        return selected;
    }
}