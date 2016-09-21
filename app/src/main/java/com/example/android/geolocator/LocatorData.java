package com.example.android.geolocator;

public class LocatorData {

    private int _id;
    private double _longitude;
    private double _latitude;
    private double _altitude;
    private String _place;
    private String _temperature;

    // Setter methods
    public void set_id (int _id) {
        this._id = _id;
    }

    public void set_longitude (double _longitude) {
        this._longitude = _longitude;
    }

    public void set_latitude (double _latitude) {
        this._latitude = _latitude;
    }

    public void set_altitude (double _altitude) {
        this._altitude = _altitude;
    }

    public void set_place (String _place) {
        this._place = _place;
    }

    public void set_temperature (String _temperature) {
        this._temperature = _temperature;
    }

    // Getter methods
    public int get_id () {
        return _id;
    }

    public double get_longitude () {
        return _longitude;
    }

    public double get_latitude () {
        return _latitude;
    }

    public double get_altitude () {
        return _altitude;
    }

    public String get_place () {
        return _place;
    }

    public String get_temperature () {
        return _temperature;
    }

}
