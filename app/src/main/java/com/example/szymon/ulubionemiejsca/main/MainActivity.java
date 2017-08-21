package com.example.szymon.ulubionemiejsca.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.szymon.ulubionemiejsca.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 2017;
    @BindView(R.id.text)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart(this);
        mainPresenter.setupLocationManager(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }


    @OnClick(R.id.button)
    public void onButtonClicked() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        else
            textView.setText(Integer.toString(permissionCheck));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    textView.setText("not granted");
                }
                return;
            }
        }

    }

    @Override
    public void updateTextView(String text) {
        textView.setText(text);
    }
}
