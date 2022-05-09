package com.example.englishhelper1.domain;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.io.Serializable;

public class Word implements Parcelable {

    private String engValue;
    private String ruValue;
    boolean isLearned = false;

    public Word(String engValue, String ruValue) {
        this.engValue = engValue;
        this.ruValue = ruValue;
    }

    public Word(String engValue, String ruValue, boolean isLearned) {
        this.engValue = engValue;
        this.ruValue = ruValue;
        this.isLearned = isLearned;
    }

    protected Word(Parcel in) {
        engValue = in.readString();
        ruValue = in.readString();
        isLearned = in.readByte() != 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public String getEngValue() {
        return engValue;
    }

    public String getRuValue() {
        return ruValue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(engValue);
        parcel.writeString(ruValue);
        parcel.writeBoolean(isLearned);
    }
}
