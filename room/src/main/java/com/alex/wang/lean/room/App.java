package com.alex.wang.lean.room;

import android.app.Application;

import com.alex.wang.lean.room.room.DataBase;

public class App extends Application {
    private static App sApp;


    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;

        DataBase.getInstance();
    }

    public static App getInstance() {
        return sApp;
    }
}
