package com.example.englishhelper1.domain;

public class Section {

    private String name;
    private int progress = 0;
    //Word[] words;

    public Section(String name) {
        this.name = name;
    }

    /*public Section(String name, Word[] words) {
        this.name = name;
        this.words = words;
    }*/

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
