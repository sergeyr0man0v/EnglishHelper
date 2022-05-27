package com.example.englishhelper1.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

public class Word implements Parcelable {

    private int id;
    private String engValue;
    private String ruValue;
    boolean isLearned = false;
    private int sectionId;

    public Word(String engValue, String ruValue) {
        this.engValue = engValue;
        this.ruValue = ruValue;
    }

    public Word(String engValue, String ruValue, int sectionId) {
        this.engValue = engValue;
        this.ruValue = ruValue;
        this.sectionId = sectionId;
    }

    public Word(int id, String engValue, String ruValue, boolean isLearned) {
        this.id = id;
        this.engValue = engValue;
        this.ruValue = ruValue;
        this.isLearned = isLearned;
    }

    protected Word(Parcel in) {
        id = in.readInt();
        sectionId = in.readInt();
        engValue = in.readString();
        ruValue = in.readString();
        isLearned = in.readBoolean();
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

    public int getId() {
        return id;
    }

    public String getEngValue() {
        return engValue;
    }

    public String getRuValue() {
        return ruValue;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public int getSectionId() {
        return sectionId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(sectionId);
        parcel.writeString(engValue);
        parcel.writeString(ruValue);
        parcel.writeBoolean(isLearned);
    }
}
