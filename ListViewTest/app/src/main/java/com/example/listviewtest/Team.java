package com.example.listviewtest;

/**
 * Created by zl on 19-10-15.
 */
public class Team {
    private String name;
    private int imageId;

    public Team(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
