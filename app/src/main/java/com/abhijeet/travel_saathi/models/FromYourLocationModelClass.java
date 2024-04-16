package com.abhijeet.travel_saathi.models;

import android.graphics.drawable.Drawable;

public class FromYourLocationModelClass {

    private int image;
    private String name;

    public FromYourLocationModelClass(int image, String name) {
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
