package com.example.android.geolocator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        super.onStart();

        displaySummary();
    }

    public void displaySummary() {

        double myLongitude = 0;
        double myLatitude = 0;
        double myAltitude = 0;
        String myPlace = "";
        String myTemperature = "48";

        // Create a summary
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            myLongitude = extras.getDouble("ACT2_LONGITUDE");
            myLatitude = extras.getDouble("ACT2_LATITUDE");
            myAltitude = extras.getDouble("ACT2_ALTITUDE");
            myPlace = extras.getString("ACT2_PLACE");
        }

        TextView textView = (TextView) findViewById(R.id.summaryTextView);
        textView.setText(
                    "Place: " + myPlace + "\n" +
                    "Temperature: " + myTemperature + " degrees" + "\n" +
                    "Meters above sea: " + myAltitude + "\n" +
                    "Longitude: " + myLongitude + "\n" +
                    "Latitude: " + myLatitude);

    }

}
