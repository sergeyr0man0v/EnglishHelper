package com.example.englishhelper1.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Section implements Parcelable {

    private String name;
    private int progress = 0;

    Word[] words;

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, int progress) {
        this.name = name;
        this.progress = progress;
    }

    public Section(String name, Word[] words) {
        this.name = name;
        this.words = words;
    }

    protected Section(Parcel in) {
        name = in.readString();
        progress = in.readInt();
        words = in.createTypedArray(Word.CREATOR);
    }

    public Section(String name, int progress, Word[] words) {
        this.name = name;
        this.progress = progress;
        this.words = words;
    }

    public static final Creator<Section> CREATOR = new Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

    public Word[] getWords() {
        return words;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(progress);
        parcel.writeTypedArray(words, i);
    }
}
