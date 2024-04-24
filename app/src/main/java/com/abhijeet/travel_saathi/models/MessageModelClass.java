package com.abhijeet.travel_saathi.models;

public class MessageModelClass {

    private int image;
    private String name;


    public MessageModelClass(int image, String name) {
        this.image = image;
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
