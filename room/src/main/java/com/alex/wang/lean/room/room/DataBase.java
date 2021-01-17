package com.alex.wang.lean.room.room;

import androidx.room.Room;

import com.alex.wang.lean.room.App;

public class DataBase {
    private static DataBase sDataBase;

    private UserDataBase mUserDataBase;

    private DataBase() {
        mUserDataBase = Room.databaseBuilder(App.getInstance().getApplicationContext(), UserDataBase.class, "user.db").build();
    }

    public static synchronized DataBase getInstance() {
        if (sDataBase == null) {
            sDataBase = new DataBase();
        }

        return sDataBase;
    }

    public UserDataBase getUserDataBase() {
        return mUserDataBase;
    }
}
