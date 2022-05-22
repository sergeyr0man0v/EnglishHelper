package com.example.englishhelper1.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Section implements Parcelable {

    private int id;
    private String name;
    private int progress = 0;
    private int moduleId;
    private String description;

    private ArrayList<Word> words;

    public Section(String name) {
        this.name = name;
    }

    public Section(int id, String name, String description, int progress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.progress = progress;
    }

    public Section(int id, String name, int moduleId, String description) {
        this.id = id;
        this.name = name;
        this.moduleId = moduleId;
        this.description = description;
    }

    public Section(String name, int progress) {
        this.name = name;
        this.progress = progress;
    }

    /*public Section(String name, Word[] words) {
        this.name = name;
        this.words = words;
    }*/

    protected Section(Parcel in) {
        id = in.readInt();
        name = in.readString();
        progress = in.readInt();
        moduleId = in.readInt();
        description = in.readString();
        //words = in.createTypedArrayList(Word.CREATOR);
    }

    /*public Section(String name, int progress, Word[] words) {
        this.name = name;
        this.progress = progress;
        this.words = words;
    }*/

    /*public Word[] getWords() {
        return words;
    }*/

    public int getId() {
        return id;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public int getModuleId() {
        return moduleId;
    }

    public String getDescription() {
        return description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    @Override
    public int describeContents() {
        return 0;
    }

    /*@Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(progress);
        parcel.writeInt(moduleId);
    }*/

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(progress);
        parcel.writeInt(moduleId);
        parcel.writeString(description);
        /*Word[] words1 = words.toArray(new Word[0]);
        parcel.writeTypedArray(words1, i);*/
    }
}
