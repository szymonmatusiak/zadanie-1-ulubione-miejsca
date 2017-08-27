package com.example.szymon.ulubionemiejsca.recycler;

import com.example.szymon.ulubionemiejsca.MyRealm;
import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymon on 24.08.2017.
 */

public class RecyclerPresenterImpl extends BasePresenter<MyRecyclerView> implements RecyclerPresenter {
    private MyRealm realm;

    @Override
    public void onStart(final MyRecyclerView myRecyclerView) {
        attachView(myRecyclerView);
        realm = new MyRealm();
        setDataInRecycle();
    }

    @Override
    public void onStop() {
        detachView();
    }

    @Override
    public void setDataInRecycle() {
        realm.findAll();
        List<Place> places = new ArrayList<Place>();
        places = realm.getRealm().copyFromRealm(realm.getPlaces());
        getView().setPlacesList(places);
    }

    @Override
    public String getLocationCoordinates(final int position) {
        if (realm.getPlaces().size() > position) {
            return realm.getPlaces().get(position).getLocationCoordinates();
        } else {
            return "position is larger than size";
        }
    }

    @Override
    public void removePlaceFromDatabase(final int position) {
        realm.remove(position);
        MyRealm.setLastPosition(MyRealm.getLastPosition() - 1);
    }

    @Override
    public void changePositionOfItemsInRange(final int fromPosition, final int toPosition) {
        realm.changePositionOfItemsInRange(fromPosition, toPosition);
    }

    @Override
    public String getFirstNote() {
        return realm.get(0).getNote();
    }

}
