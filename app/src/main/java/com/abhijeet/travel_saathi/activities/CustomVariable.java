package com.abhijeet.travel_saathi.activities;

public class CustomVariable {
    String Name;
    int image;

    CustomVariable(String Name,int image){
        this.Name = Name;
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
