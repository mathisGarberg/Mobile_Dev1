package com.example.android.geolocator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE__NAME = "geolocator.db";
    public static final String TABLE_GEOLOCATOR = "geolocator";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_LONG = "longitude";
    public static final String COLUMN_ALT = "altitude";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_TEMP = "temperature";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE__NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_GEOLOCATOR + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_LAT + " FLOAT," +
                COLUMN_LONG + " FLOAT," +
                COLUMN_ALT + " FLOAT," +
                COLUMN_PLACE + " TEXT," +
                COLUMN_TEMP + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_GEOLOCATOR);
        onCreate(db);
    }

    //Add new row to the database
    public void addLocatorData(LocatorData locatorData) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_LAT, locatorData.get_latitude());
        values.put(COLUMN_LONG, locatorData.get_longitude());
        values.put(COLUMN_ALT, locatorData.get_altitude());
        values.put(COLUMN_PLACE, locatorData.get_place());
        values.put(COLUMN_TEMP, locatorData.get_temperature());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_GEOLOCATOR, null, values);
        db.close();
    }

    // Delete product from database
    /** public void deleteProduct (String productName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GEOLOCATOR + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    } */

    // Get last locator data
    public LocatorData getLocatorData() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        // String query = "SELECT * FROM " + TABLE_GEOLOCATOR + " WHERE 1";

        // Create Locator Data
        LocatorData locLocatorData = new LocatorData();

        // Cursor point to last location in your results
        Cursor c = db.query(TABLE_GEOLOCATOR, new String[]{COLUMN_ID, COLUMN_LAT, COLUMN_LONG, COLUMN_ALT, COLUMN_PLACE, COLUMN_TEMP}, null,null,null, null, null);
        // Move to the first row in the results

        if(c.isLast()) {
            locLocatorData.set_latitude(70);
            locLocatorData.set_longitude(15);
            locLocatorData.set_altitude(40.1);
            locLocatorData.set_place("");
            locLocatorData.set_temperature("");
        } else {
            c.moveToFirst();
            locLocatorData.set_latitude(c.getFloat(c.getColumnIndex("latitude")));
            locLocatorData.set_longitude(c.getFloat(c.getColumnIndex("longitude")));
            locLocatorData.set_altitude(c.getFloat(c.getColumnIndex("altitude")));
            if(c.getString(c.getColumnIndex("place")) != null) {
                locLocatorData.set_place(c.getString(c.getColumnIndex("place")));
            }
            if(c.getString(c.getColumnIndex("temperature")) != null) {
                locLocatorData.set_temperature(c.getString(c.getColumnIndex("temperature")));
            }
        }

        //   if(!c.isAfterLast()) {
            //    }
        //  else {
      //  }

        db.close();
        c.close();
        return locLocatorData;
    }

    // Print out the database as a string
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_GEOLOCATOR + " WHERE 1";

        // Cursor point to a locaiton in your results
        Cursor c = db.rawQuery(query, null);
        // Move to the first row in the results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("productName")) != null) {
                dbString += c.getString(c.getColumnIndex("productName"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }



}
