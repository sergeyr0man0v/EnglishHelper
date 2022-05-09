package com.example.englishhelper1.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Module implements Parcelable {

    private String name;

    Section[] sections;

    public Module(String name) {
        this.name = name;
    }

    public Module(String name, Section[] sections) {
        this.name = name;
        this.sections = sections;
    }

    protected Module(Parcel in) {
        name = in.readString();
        sections = in.createTypedArray(Section.CREATOR);
    }

    public static final Creator<Module> CREATOR = new Creator<Module>() {
        @Override
        public Module createFromParcel(Parcel in) {
            return new Module(in);
        }

        @Override
        public Module[] newArray(int size) {
            return new Module[size];
        }
    };

    public Section[] getSections() {
        return sections;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedArray(sections, i);
    }
}
