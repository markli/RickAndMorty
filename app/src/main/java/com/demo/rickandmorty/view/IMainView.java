package com.demo.rickandmorty.view;

import android.os.Message;

import com.demo.rickandmorty.entity.character.Character;
import com.demo.rickandmorty.entity.episode.Episode;
import com.demo.rickandmorty.entity.location.Location;

import java.util.List;

public interface IMainView {
    public void showCharacters(List<Character> characters);
    public void showLocations(List<Location> locations);
    public void showEpisodes(List<Episode> episodes);
    public void updateInfo(int pagePos, int pageNum);
    public void releaseView();
}
