package com.abhijeet.travel_saathi.models;

import android.graphics.drawable.Drawable;

public class FromYourLocationModelClass {

    private int image;
    private String name;

    public FromYourLocationModelClass() {
    }

    public FromYourLocationModelClass(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
