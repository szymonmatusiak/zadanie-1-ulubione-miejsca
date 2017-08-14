package com.example.szymon.ulubionemiejsca.base;

/**
 * Created by Szymon on 14.08.2017.
 */

interface IBasePresenter<V> {
    void attachView(V view);

    void detachView();
}
