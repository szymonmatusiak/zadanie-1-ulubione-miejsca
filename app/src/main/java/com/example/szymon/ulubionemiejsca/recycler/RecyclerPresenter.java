package com.example.szymon.ulubionemiejsca.recycler;

/**
 * Created by Szymon on 24.08.2017.
 */

public interface RecyclerPresenter {
    void onStart(final MyRecyclerView myRecyclerView);

    void onStop();

    void setDataInRecycle();

    void removePlaceFromDatabase(int position);

    void changePositionOfItemsInRange(int fromPosition, int toPosition);

    String getLocationCoordinates(int position);

    String getFirstNote();
}
