package com.example.szymon.ulubionemiejsca;

import android.location.Location;

import io.realm.RealmObject;

/**
 * Created by Szymon on 22.08.2017.
 */

public class Place extends RealmObject {
    private double longitude;
    private double latitude;
    private String note;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
