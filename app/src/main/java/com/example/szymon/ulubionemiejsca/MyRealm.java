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
    public static int lastPosition;
    private RealmResults<Place> places;
    private Realm realm;
    private ItemTouchHelper mItemTouchHelper;

    public MyRealm() {
        if (realm == null) {
            realm = Realm.getDefaultInstance();
            lastPosition = getItemCount();
        }
    }

    public static int getLastPosition() {
        return lastPosition;
    }

    public static void setLastPosition(final int lastPosition) {
        MyRealm.lastPosition = lastPosition;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(final Realm realm) {
        this.realm = realm;
    }

    public RealmResults<Place> getPlaces() {
        return places;
    }

    public void setPlaces(final RealmResults<Place> places) {
        this.places = places;
    }

    private void setNextElementPosition() {
        lastPosition = lastPosition + 1;
    }

    private int getLastElementPosition() {
        findAll();
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

    public void findAll() {
        places = realm.where(Place.class).findAll().sort("position");
    }

    public Place get(final int i) {
        Place place = places.get(i);
        return place;
    }

    public int getItemCount() {
        findAll();
        if (places == null) {
            return 0;
        }
        return places.size();
    }

    public void remove(final int adapterPosition) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Place placeToRemove = realm.where(Place.class).equalTo("position", adapterPosition).findFirst();
                if (placeToRemove != null)
                    placeToRemove.deleteFromRealm();
                for (Place place : places) {
                    if (place.getPosition() > adapterPosition) {
                        int newValue = place.getPosition() - 1;
                        place.setPosition(newValue);
                    }
                }
            }
        });
    }

    //TODO remove this
    public void sort() {
        places.sort("position", Sort.ASCENDING);
    }

    @Override
    public void onStartDrag(final RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void changePositionOfItemsInRange(final int fromPosition, final int toPosition) {

        final Place one = realm.where(Place.class).equalTo("position", fromPosition).findFirst();
        final Place two = realm.where(Place.class).equalTo("position", toPosition).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int tempPositionHolder;
                tempPositionHolder = one.getPosition();
                one.setPosition(toPosition);
                two.setPosition(tempPositionHolder);
            }
        });
    }
}
