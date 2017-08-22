package com.example.szymon.ulubionemiejsca.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.example.szymon.ulubionemiejsca.base.BasePresenter;

/**
 * Created by Szymon on 14.08.2017.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {
    @Override
    public void onStart(MainView mainView) {
        attachView(mainView);
        if (ActivityCompat.checkSelfPermission((Context) mainView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Context) mainView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @Override
    public void onStop() {
        detachView();
    }


}
