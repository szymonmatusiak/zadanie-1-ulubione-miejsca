package com.example.szymon.ulubionemiejsca.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.szymon.ulubionemiejsca.MyRealm;
import com.example.szymon.ulubionemiejsca.R;
import com.example.szymon.ulubionemiejsca.recycler.helper.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity {
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;
    private ItemTouchHelper mItemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        recyclerViewAdapter = new MyRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerViewAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        textView.setText(String.valueOf(recyclerViewAdapter.getRealm().getItemCount() + " " + MyRealm.lastPosision));
        recyclerViewAdapter.notifyDataSetChanged();
        if (recyclerViewAdapter.getItemCount() > 0) {
            toast(recyclerViewAdapter.getRealm().get(0).getLocationCoordinates());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
