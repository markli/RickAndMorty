package com.demo.rickandmorty.model;

import com.demo.rickandmorty.entity.character.Character;
import com.demo.rickandmorty.entity.episode.Episode;
import com.demo.rickandmorty.entity.location.Location;

import java.util.List;

public interface IMainModel {
    public void requestCharacterData(int page);
    public void requestLocationData(int page);
    public void requestEpisodeData(int page);
    public List<Character> getCharacterData();
    public List<Location> getLocationData();
    public List<Episode> getEpisodeData();
    public void releaseModel();
    public int getPageNum(int type);
    public int getPagePos(int type);
}
