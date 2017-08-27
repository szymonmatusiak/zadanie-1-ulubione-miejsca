package com.example.szymon.ulubionemiejsca.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.R;
import com.example.szymon.ulubionemiejsca.recycler.helper.SimpleItemTouchHelperCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity implements MyRecyclerView, MyRecyclerViewForAdapter {
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerPresenter recyclerPresenter;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        recyclerPresenter = new RecyclerPresenterImpl();

        recyclerViewAdapter = new MyRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerViewAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerPresenter.onStart(this);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        recyclerPresenter.onStop();
    }

    public void toast(final String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPlacesList(final List<Place> places) {
        recyclerViewAdapter.setData(places);
    }

    @Override
    public void removeFromDatabase(final int position) {
        recyclerPresenter.removePlaceFromDatabase(position);
    }

    @Override
    public void changePositionOfItemsInRange(final int fromPosition, final int toPosition) {
        recyclerPresenter.changePositionOfItemsInRange(fromPosition, toPosition);
    }
}
