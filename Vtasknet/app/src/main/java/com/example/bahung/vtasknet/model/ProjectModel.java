package com.example.bahung.vtasknet.model;

/**
 * Created by bahung on 18/06/2018.
 **/

public class ProjectModel {
    private String title;
    private int img;

    public ProjectModel(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }
}
