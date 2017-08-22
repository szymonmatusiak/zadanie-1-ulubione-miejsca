package com.example.szymon.ulubionemiejsca.main;

import com.example.szymon.ulubionemiejsca.Place;

/**
 * Created by Szymon on 14.08.2017.
 */

public interface MainPresenter {
    void onStart(final MainView mainView);

    void onStop();

    void savePlace(Place place);
}
