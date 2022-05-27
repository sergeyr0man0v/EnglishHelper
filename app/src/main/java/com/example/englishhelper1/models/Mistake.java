package com.example.englishhelper1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Mistake implements Parcelable {

    private String word;
    private String correctAnswer;
    private String answer;

    public Mistake(String word, String correctAnswer, String answer) {
        this.word = word;
        this.correctAnswer = correctAnswer;
        this.answer = answer;
    }

    protected Mistake(Parcel in) {
        word = in.readString();
        correctAnswer = in.readString();
        answer = in.readString();
    }

    public static final Creator<Mistake> CREATOR = new Creator<Mistake>() {
        @Override
        public Mistake createFromParcel(Parcel in) {
            return new Mistake(in);
        }

        @Override
        public Mistake[] newArray(int size) {
            return new Mistake[size];
        }
    };

    public String getWord() {
        return word;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(word);
        parcel.writeString(correctAnswer);
        parcel.writeString(answer);
    }
}
