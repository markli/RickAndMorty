package com.demo.rickandmorty.entity.baseapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseAPIEntity {

    @SerializedName("characters")
    @Expose
    private String characters;
    @SerializedName("locations")
    @Expose
    private String locations;
    @SerializedName("episodes")
    @Expose
    private String episodes;

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

}
