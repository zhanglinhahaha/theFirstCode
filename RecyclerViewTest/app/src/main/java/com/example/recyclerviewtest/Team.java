package com.example.recyclerviewtest;

/**
 * Created by zl on 19-10-15.
 */
class Team {
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
