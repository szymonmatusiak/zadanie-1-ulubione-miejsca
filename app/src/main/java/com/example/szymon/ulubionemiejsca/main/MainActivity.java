package com.example.szymon.ulubionemiejsca.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.szymon.ulubionemiejsca.MyRealm;
import com.example.szymon.ulubionemiejsca.Place;
import com.example.szymon.ulubionemiejsca.R;
import com.example.szymon.ulubionemiejsca.recycler.RecyclerActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 2017;
    @BindView(R.id.text)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.recycler_activity)
    Button recycler_activity;

    MainPresenter mainPresenter;
    private GoogleApiClient googleApiClient;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart(this);
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        googleApiClient.disconnect();
    }


    @OnClick(R.id.button)
    public void onButtonClicked() {
        final int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        } else {
            getLastLocation();
            Place place = getPlace();
            mainPresenter.savePlace(place);
        }
    }

    @NonNull
    private Place getPlace() {
        Place place = new Place();
        if (location != null) {
            place.setLatitude(location.getLatitude());
            place.setLongitude(location.getLongitude());
        }
        if (textView != null && textView.getTextSize() != 0) {
            place.setNote(String.valueOf(note.getText()));
        }
        place.setPosition(MyRealm.lastPosition);
        return place;
    }

    @OnClick(R.id.recycler_activity)
    public void openRecyclerActivity() {
        mainPresenter.openRecyclerActivity();
    }

    @Override
    public void openRecycler() {
        MainActivity.this.startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    textView.setText("not granted");
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @NonNull
    private String getLatitudeAndLongitude() {
        if (location != null) {
            return location.getLatitude() + " " + location.getLongitude() + " " + location.getAccuracy();
        } else
            return "check gps";
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTextView(String text) {
        textView.setText(text);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        toast(getLatitudeAndLongitude() + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        toast(provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        toast(provider);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        toast("onConnected");
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        toast(i + " ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        toast(connectionResult.toString());
    }
}
