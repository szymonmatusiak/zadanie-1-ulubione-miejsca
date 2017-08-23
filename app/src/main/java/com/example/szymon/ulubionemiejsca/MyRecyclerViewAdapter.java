package com.example.szymon.ulubionemiejsca;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private MyRealm realm;

    public MyRecyclerViewAdapter() {
        realm = new MyRealm();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    @OnClick
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Place place = realm.get(position);
        if (holder != null) {
            holder.setData(place);
        }
    }

    @Override
    public int getItemCount() {
        return realm.getItemCount();
    }

    public MyRealm getRealm() {
        return realm;
    }
}
