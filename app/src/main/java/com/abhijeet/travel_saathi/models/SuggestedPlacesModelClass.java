package com.abhijeet.travel_saathi.models;

public class SuggestedPlacesModelClass {

    private int sceneImage;
    private String sceneName;

    public SuggestedPlacesModelClass(String sceneName,int sceneImage) {
        this.sceneImage = sceneImage;
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public int getSceneImage() {
        return sceneImage;
    }
}
