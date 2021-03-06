package com.example.szymon.ulubionemiejsca.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.note)
    TextView note;
    @BindView(R.id.location)
    TextView location;

    public MyViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void setData(final Place place) {
        this.note.setText(place.getNote());
        this.location.setText(place.getLocationCoordinates() + "      " + place.getPosition());
    }

    @Override
    public void onClick(final View v) {
        getAdapterPosition();
    }
}
