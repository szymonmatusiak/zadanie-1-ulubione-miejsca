package com.example.szymon.ulubionemiejsca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends AppCompatActivity {
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        recyclerViewAdapter = new MyRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();

        textView.setText(String.valueOf(recyclerViewAdapter.getRealm().getItemCount()));
        recyclerViewAdapter.notifyDataSetChanged();
        if (recyclerViewAdapter.getItemCount() > 0) {
            toast(recyclerViewAdapter.getRealm().get(0).getLocationCoordinates());
        }
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
