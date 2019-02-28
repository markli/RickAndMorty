package com.demo.rickandmorty;

import android.app.Application;

public class RickAndMorty extends Application {
    private static RickAndMorty application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static RickAndMorty getApplication() {
        return application;
    }
}
