package com.example.materialtest;

/**
 * Created by zl on 19-10-25.
 */
public class Fruit {

    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
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

/*
 public class Record {

    //Consumption of type, which is one of eat, close, home, or others.
    private int type;

    //Consumption of content
    private String content;

    //Consumption of price
    private int price;

    //Consumption of date
    private String date;

    //Consumption of name
    private String name;
}
*/