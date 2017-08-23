package com.example.szymon.ulubionemiejsca;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyRealm {
    private Realm realm;

    public MyRealm() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
    }

    public void savePlace(final Place place) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(place);
            }
        });
    }

    public RealmResults<Place> findAll() {
        RealmResults<Place> places = realm.where(Place.class).findAll();
        return places;
    }

    public Place get(int i) {
        RealmResults<Place> places;
        places = realm.where(Place.class).findAll();
        Place place = places.get(i);
        return place;
    }

    public int getItemCount() {
        RealmResults<Place> places;
        places = realm.where(Place.class).findAll();
        places.size();
        return places.size();
    }

}
