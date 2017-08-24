package com.example.szymon.ulubionemiejsca;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.location)
        TextView location;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setData(Place place) {
            this.note.setText(place.getNote());
            this.location.setText(place.getLocationCoordinates() + "      " + place.getPosition());
        }

        @Override
        public void onClick(View v) {
            realm.moveElementUp(getAdapterPosition());
            notifyDataSetChanged();

        }

        @Override
        public boolean onLongClick(View v) {
            realm.remove(getAdapterPosition());
            realm.sort();
            notifyDataSetChanged();
            return true;
        }
    }

}
