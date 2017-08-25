package com.example.szymon.ulubionemiejsca.recycler;

import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.base.IBaseView;

import java.util.List;

/**
 * Created by Szymon on 24.08.2017.
 */

public interface MyRecyclerView extends IBaseView {
    void toast(String text);

    void setPlacesList(final List<Place> places);
}
