package com.example.englishhelper1.domain;

public class Module {

    //Section[] sections;

    private String name;

    public Module(String name) {
        this.name = name;
    }

    /*public Module(String name, Section[] sections) {
        this.name = name;
        this.sections = sections;
    }*/

    public String getName() {
        return name;
    }
}
