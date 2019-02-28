package com.demo.rickandmorty.entity;

public final class ActionDefinition {
    static public final int ACTION_APP_LAUNCHED = 1;
    static public final int ACTION_SHOW_CHARACTER = 2;
    static public final int ACTION_SHOW_LOCATION = 3;
    static public final int ACTION_SHOW_EPISODE = 4;
    static public final int ACTION_DATA_CHANGED = 5;
    static public final int ACTION_APP_EXIT = 99;


    static public final int SOURCE_CHARACTER = 0;
    static public final int SOURCE_LOCATION = 1;
    static public final int SOURCE_EPISODE = 2;

    static public final int STATE_LOADING = 1;
    static public final int STATE_FINISHED = 2;
    static public final int STATE_ERROR = 3;
}
