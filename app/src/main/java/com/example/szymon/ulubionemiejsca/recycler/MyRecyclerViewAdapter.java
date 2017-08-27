package com.example.szymon.ulubionemiejsca.recycler;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.R;
import com.example.szymon.ulubionemiejsca.recycler.helper.ItemTouchHelperAdapter;
import com.example.szymon.ulubionemiejsca.recycler.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Szymon on 23.08.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
    private List<Place> places;
    private MyRecyclerViewForAdapter myRecyclerViewForAdapter;

    public MyRecyclerViewAdapter(MyRecyclerViewForAdapter myRecyclerViewForAdapter) {
        places = new ArrayList<Place>();
        this.myRecyclerViewForAdapter = myRecyclerViewForAdapter;
    }

    public void setData(final List<Place> places) {
        this.places.clear();
        this.places.addAll(places);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Place place = places.get(position);
        holder.setData(place);
    }

    @Override
    public int getItemCount() {
        return places != null ? places.size() : 0;
    }

    @Override
    public boolean onItemMove(final int fromPosition, final int toPosition) {
        notifyItemMoved(fromPosition, toPosition);
        myRecyclerViewForAdapter.changePositionOfItemsInRange(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final int position) {
        myRecyclerViewForAdapter.removeFromDatabase(places.get(position).getPosition());
        places.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.location)
        TextView location;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final Place place) {
            this.note.setText(place.getNote());
            this.location.setText(place.getLocationCoordinates());
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            //TODO R.color.colorRowBackground not working
            itemView.setBackgroundColor(Color.parseColor("#DFDFDF"));
        }
    }

}
