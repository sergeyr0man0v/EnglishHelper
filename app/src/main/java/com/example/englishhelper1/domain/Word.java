package com.example.englishhelper1.domain;

public class Word {

    private String engValue;
    private String ruValue;
    boolean isLearned = false;

    public Word(String engValue, String ruValue) {
        this.engValue = engValue;
        this.ruValue = ruValue;
    }

    public String getEngValue() {
        return engValue;
    }

    public String getRuValue() {
        return ruValue;
    }
}
