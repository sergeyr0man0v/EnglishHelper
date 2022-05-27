package com.example.englishhelper1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Module implements Parcelable {

    private int id;
    private String name;
    private String description;
    private int progress;

    //Section[] sections;

    public Module(String name) {
        this.name = name;
    }

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Module(int id, String name, String description, int progress) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.progress = progress;
    }

    /*public Module(String name, Section[] sections) {
        this.name = name;
        this.sections = sections;
    }*/

    /*protected Module(Parcel in) {
        name = in.readString();
        sections = in.createTypedArray(Section.CREATOR);
    }*/

    protected Module(Parcel in) {
        name = in.readString();
        description = in.readString();
        id = in.readInt();
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

    /*public Section[] getSections() {
        return sections;
    }*/

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(id);
    }

    /*@Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedArray(sections, i);
    }*/
}
