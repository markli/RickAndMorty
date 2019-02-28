package com.demo.rickandmorty.model.impl;

import android.os.Message;

import com.demo.rickandmorty.entity.ActionDefinition;
import com.demo.rickandmorty.entity.character.Character;
import com.demo.rickandmorty.entity.episode.Episode;
import com.demo.rickandmorty.entity.location.Location;
import com.demo.rickandmorty.model.IMainModel;
import com.demo.rickandmorty.utils.RetrofitFactory;

import java.util.List;

import common.utils.observer.SimpleSubjecter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainModel extends SimpleSubjecter implements IMainModel {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private List<Character> mCharacters;
    private List<Location> mLocations;
    private List<Episode> mEpisodes;
    private int mPageNum[] = {0, 0, 0};
    private int mPagePos[] = {0, 0, 0};


    @Override
    public void requestCharacterData(int page) {
        Message msg = Message.obtain();
        msg.what = ActionDefinition.ACTION_DATA_CHANGED;
        msg.arg1 = ActionDefinition.SOURCE_CHARACTER;
        msg.arg2 = ActionDefinition.STATE_LOADING;
        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
        int queryPage = page;
        if (page <= 0) {
            queryPage = this.getPageNum(ActionDefinition.SOURCE_CHARACTER);
        } else if (page > this.getPageNum(ActionDefinition.SOURCE_CHARACTER)) {
            queryPage = 1;
        }
        mCompositeDisposable.add(RetrofitFactory.getServiceInstance().getCharacters(queryPage)
                .subscribeOn(Schedulers.io())
                .doOnNext(characterEntity -> {
                    if (characterEntity.getCharacters().size() != 0) {
                        mCharacters = characterEntity.getCharacters();
                        mPageNum[ActionDefinition.SOURCE_CHARACTER] = characterEntity.getInfo().getPages();
                        if (page <= 0) {
                            mPagePos[ActionDefinition.SOURCE_CHARACTER] = this.getPageNum(ActionDefinition.SOURCE_CHARACTER);
                        } else if (page > this.getPageNum(ActionDefinition.SOURCE_CHARACTER)) {
                            mPagePos[ActionDefinition.SOURCE_CHARACTER] = 1;
                        } else {
                            mPagePos[ActionDefinition.SOURCE_CHARACTER] = page;
                        }
                        msg.arg1 = ActionDefinition.SOURCE_CHARACTER;
                        msg.arg2 = ActionDefinition.STATE_FINISHED;
                        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(characterEntity -> {
                }, throwable -> {
                    msg.arg1 = ActionDefinition.SOURCE_CHARACTER;
                    msg.arg2 = ActionDefinition.STATE_ERROR;
                    MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                }));

    }

    @Override
    public void requestLocationData(int page) {
        Message msg = Message.obtain();
        msg.what = ActionDefinition.ACTION_DATA_CHANGED;
        msg.arg1 = ActionDefinition.SOURCE_LOCATION;
        msg.arg2 = ActionDefinition.STATE_LOADING;
        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
        int queryPage = page;
        if (page <= 0) {
            queryPage = this.getPageNum(ActionDefinition.SOURCE_LOCATION);
        } else if (page > this.getPageNum(ActionDefinition.SOURCE_LOCATION)) {
            queryPage = 1;
        }
        mCompositeDisposable.add(RetrofitFactory.getServiceInstance().getLocations(queryPage)
                .subscribeOn(Schedulers.io())
                .doOnNext(locationEntity -> {
                    if (locationEntity.getLocations().size() != 0) {
                        mLocations = locationEntity.getLocations();
                        mPageNum[ActionDefinition.SOURCE_LOCATION] = locationEntity.getInfo().getPages();
                        if (page <= 0) {
                            mPagePos[ActionDefinition.SOURCE_LOCATION] = this.getPageNum(ActionDefinition.SOURCE_LOCATION);
                        } else if (page > this.getPageNum(ActionDefinition.SOURCE_LOCATION)) {
                            mPagePos[ActionDefinition.SOURCE_LOCATION] = 1;
                        } else {
                            mPagePos[ActionDefinition.SOURCE_LOCATION] = page;
                        }
                        msg.arg1 = ActionDefinition.SOURCE_LOCATION;
                        msg.arg2 = ActionDefinition.STATE_FINISHED;
                        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locationEntity -> {
                }, throwable -> {
                    msg.arg1 = ActionDefinition.SOURCE_LOCATION;
                    msg.arg2 = ActionDefinition.STATE_ERROR;
                    MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                }));
    }

    @Override
    public void requestEpisodeData(int page) {
        Message msg = Message.obtain();
        msg.what = ActionDefinition.ACTION_DATA_CHANGED;
        msg.arg1 = ActionDefinition.SOURCE_EPISODE;
        msg.arg2 = ActionDefinition.STATE_LOADING;
        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
        int queryPage = page;
        if (page <= 0) {
            queryPage = this.getPageNum(ActionDefinition.SOURCE_EPISODE);
        } else if (page > this.getPageNum(ActionDefinition.SOURCE_EPISODE)) {
            queryPage = 1;
        }
        mCompositeDisposable.add(RetrofitFactory.getServiceInstance().getEpisodes(queryPage)
                .subscribeOn(Schedulers.io())
                .doOnNext(episodeEntity -> {
                    if (episodeEntity.getEpisodes().size() != 0) {
                        mEpisodes = episodeEntity.getEpisodes();
                        mPageNum[ActionDefinition.SOURCE_EPISODE] = episodeEntity.getInfo().getPages();
                        if (page <= 0) {
                            mPagePos[ActionDefinition.SOURCE_EPISODE] = this.getPageNum(ActionDefinition.SOURCE_EPISODE);
                        } else if (page > this.getPageNum(ActionDefinition.SOURCE_EPISODE)) {
                            mPagePos[ActionDefinition.SOURCE_EPISODE] = 1;
                        } else {
                            mPagePos[ActionDefinition.SOURCE_EPISODE] = page;
                        }
                        msg.arg1 = ActionDefinition.SOURCE_EPISODE;
                        msg.arg2 = ActionDefinition.STATE_FINISHED;
                        MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(episodeEntity -> {
                }, throwable -> {
                    msg.arg1 = ActionDefinition.SOURCE_EPISODE;
                    msg.arg2 = ActionDefinition.STATE_ERROR;
                    MainModel.this.notify(msg, FLAG_RUN_MAIN_THREAD);
                }));
    }

    @Override
    public List<Character> getCharacterData() {
        return mCharacters;

    }

    @Override
    public List<Location> getLocationData() {
        return mLocations;
    }

    @Override
    public List<Episode> getEpisodeData() {
        return mEpisodes;
    }

    @Override
    public void releaseModel() {
        mCompositeDisposable.clear();
        mCharacters.clear();
        mLocations.clear();
        mEpisodes.clear();
    }

    @Override
    public int getPageNum(int type) {
        return mPageNum[type];
    }

    @Override
    public int getPagePos(int type) {
        return mPagePos[type];
    }

}
