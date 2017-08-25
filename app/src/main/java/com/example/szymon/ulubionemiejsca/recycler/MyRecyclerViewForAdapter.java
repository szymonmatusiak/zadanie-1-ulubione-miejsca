package com.example.szymon.ulubionemiejsca.recycler;

/**
 * Created by Szymon on 24.08.2017.
 */

public interface MyRecyclerViewForAdapter {
    void removeFromDatabase(int position);

    void changePositionOfItemsInRange(int fromPosition, int toPosition);
}
