package com.example.szymon.ulubionemiejsca.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.example.szymon.ulubionemiejsca.MyRealm;
import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.base.BasePresenter;

/**
 * Created by Szymon on 14.08.2017.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {
    private MyRealm realm;

    @Override
    public void onStart(MainView mainView) {
        attachView(mainView);
        realm = new MyRealm();
        if (ActivityCompat.checkSelfPermission((Context) mainView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Context) mainView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @Override
    public void onStop() {
        detachView();
    }

    @Override
    public void savePlace(final Place place) {
        realm.savePlace(place);
    }

    @Override
    public void openRecyclerActivity() {
        getView().openRecycler();
    }
}
