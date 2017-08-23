package com.example.szymon.ulubionemiejsca;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.note)
    TextView note;
    @BindView(R.id.location)
    TextView location;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(Place place) {
        this.note.setText(place.getNote());
        this.location.setText(place.getLocationCoordinates());
    }
}