package com.demo.rickandmorty.utils;

import com.demo.rickandmorty.entity.character.CharacterEntity;
import com.demo.rickandmorty.entity.episode.EpisodeEntity;
import com.demo.rickandmorty.entity.location.LocationEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServiceAPI {

    @GET("character")
    Observable<CharacterEntity> getCharacters(@Query("page") int page);

    @GET("location")
    Observable<LocationEntity> getLocations(@Query("page") int page);

    @GET("episode")
    Observable<EpisodeEntity> getEpisodes(@Query("page") int page);
}
