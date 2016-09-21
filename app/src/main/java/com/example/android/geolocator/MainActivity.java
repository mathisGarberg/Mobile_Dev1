package com.example.android.geolocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void location_btn (View v) {

        // Start the new activity through an intent
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

}