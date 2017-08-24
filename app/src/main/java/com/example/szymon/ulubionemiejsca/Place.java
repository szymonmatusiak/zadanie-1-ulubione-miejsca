package com.example.szymon.ulubionemiejsca;

import io.realm.RealmObject;

/**
 * Created by Szymon on 22.08.2017.
 */

public class Place extends RealmObject {
    private double longitude;
    private double latitude;
    private double position;
    private String note;

    public Place() {
        latitude = 1;
        longitude = 1;
        note = "testy o 1 rano";
    }

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

    public String getLocationCoordinates() {
        return String.valueOf(latitude + " " + longitude);
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
