package com.example.szymon.ulubionemiejsca;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Szymon on 22.08.2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
