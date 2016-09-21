package com.example.android.geolocator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    MyDBHandler mDbHandler;
    LocatorData mLocatorData;
    double mLatitude;
    double mLongitude;
    double mAltitude;
    String mPlace;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        // Creating an API Client for LocationServices.API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        // Create SQLite database
        mDbHandler = new MyDBHandler(this, null, null, 1);

        // Get last locator data from database
        mLocatorData = mDbHandler.getLocatorData();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /* Add location from LocationServices
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location != null) {
            mLocatorData.set_latitude(location.getLongitude());
            mLocatorData.set_longitude(location.getLatitude());
            mLocatorData.set_altitude(location.getAltitude());
        };

        //Log.i("TAG", String.valueOf(mLocatorData.get_latitude()));
        LatLng myLocation = new LatLng(mLocatorData.get_latitude(), mLocatorData.get_longitude());

        // Add a marker and move the camera to location
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Marker in my location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

        // Add information from Geocoder
        android.location.Address address;

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(mLocatorData.get_latitude(), mLocatorData.get_longitude(), 1);

            if (addresses.size() > 0) {
                address = addresses.get(0);
                mLocatorData.set_place(address.getLocality());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Add information from Google PlacePicker
        /** PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        final LatLng latLng = new LatLng(myLatitude, myLongitude);
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(latLng)
                .build();
        builder.setLatLngBounds(bounds);

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } */

    }

    /** @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                String strPlace = String.format("Place: %s", place.getName());
                // Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                Log.i(TAG, strPlace);
            }
        }
    } */

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        mGoogleApiClient.disconnect();

        // Set last locator data to database
        mDbHandler.addLocatorData(mLocatorData);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Location services connected.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Location services failed. Please reconnect.");
    }

    // Summary button
    public void display_summary (View view) {
        Intent i = new Intent(this, SummaryActivity.class);
        i.putExtra("ACT2_LONGITUDE", mLocatorData.get_longitude());
        i.putExtra("ACT2_LATITUDE", mLocatorData.get_latitude());
        i.putExtra("ACT2_ALTITUDE", mLocatorData.get_altitude());
        i.putExtra("ACT2_PLACE", mLocatorData.get_place());
        startActivity(i);
    }

}
