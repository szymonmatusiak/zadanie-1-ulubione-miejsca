package com.example.szymon.ulubionemiejsca.base;

import java.lang.ref.WeakReference;

/**
 * Created by Szymon on 14.08.2017.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    private WeakReference<V> viewReference;

    @Override
    public void attachView(V view) {
        viewReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    protected V getView() {
        return viewReference.get();
    }
}
