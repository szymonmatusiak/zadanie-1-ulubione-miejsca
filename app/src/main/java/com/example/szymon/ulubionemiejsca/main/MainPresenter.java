package com.example.szymon.ulubionemiejsca.main;

import android.content.Context;

/**
 * Created by Szymon on 14.08.2017.
 */

public interface MainPresenter {
    void onStart(final MainView mainView);

    void onStop();

    void setupLocationManager(final Context context);
}
