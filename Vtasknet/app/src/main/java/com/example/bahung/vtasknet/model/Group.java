package com.example.bahung.vtasknet.model;

/**
 * Created by bahung on 17/03/2018.
 **/

public class Group {
    private String image;
    private String name;
    private String gmail;

    public Group(String image, String name, String gmail) {
        this.image = image;
        this.name = name;
        this.gmail = gmail;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getGmail() {
        return gmail;
    }
}
