package com.example.szymon.ulubionemiejsca;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.szymon.ulubionemiejsca.recycler.helper.OnStartDragListener;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyRealm implements OnStartDragListener {
    public static int lastPosision;
    RealmResults<Place> places;
    private Realm realm;
    private ItemTouchHelper mItemTouchHelper;


    public MyRealm() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
        }
    }

    private void setNextElementPosition() {
        lastPosision = lastPosision + 1;
    }

    private int getLastElementPosition() {
        places = findAll();
        if (places == null || places.size() == 0) {
            return 0;
        }
        return places.max("position").intValue();
    }

    public void savePlace(final Place place) {
        setNextElementPosition();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(place);
            }
        });
    }

    public RealmResults<Place> findAll() {
        return places = realm.where(Place.class).findAll();
    }

    public Place get(int i) {
        places = realm.where(Place.class).findAll();
        Place place = places.get(i);
        return place;
    }

    public int getItemCount() {
        places = findAll();
        if (places == null) {
            return 0;
        }
        return places.size();
    }

    public void remove(final int adapterPosition) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Place place = places.get(adapterPosition);
                place.deleteFromRealm();
            }
        });
    }

    public void moveElementUp(int adapterPosition) {
    }

    public void sort() {
        places.sort("position", Sort.ASCENDING);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void changePositionOfItemsInRange(int fromPosition, int toPosition) {
        places.get(fromPosition).setPosition(toPosition);
        for (Place place : places) {
            if (place.getPosition() > toPosition && place.getPosition() < fromPosition)
                place.setPosition(place.getPosition() + 1);
        }
    }
}
