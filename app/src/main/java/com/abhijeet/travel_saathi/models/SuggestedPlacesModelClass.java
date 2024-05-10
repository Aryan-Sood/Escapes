package com.abhijeet.travel_saathi.models;

public class SuggestedPlacesModelClass {

    private int sceneImage;
    private String sceneName;

    public SuggestedPlacesModelClass(String sceneName,int sceneImage) {
        this.sceneImage = sceneImage;
        this.sceneName = sceneName;
    }

    public void setSceneImage(int sceneImage) {
        this.sceneImage = sceneImage;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public int getSceneImage() {
        return sceneImage;
    }
}
