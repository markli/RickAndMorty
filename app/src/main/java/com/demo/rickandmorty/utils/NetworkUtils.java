package com.demo.rickandmorty.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.demo.rickandmorty.RickAndMorty;

public class NetworkUtils {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) RickAndMorty.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
