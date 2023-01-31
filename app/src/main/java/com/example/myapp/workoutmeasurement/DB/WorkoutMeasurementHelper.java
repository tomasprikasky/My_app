package com.example.myapp.workoutmeasurement.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WorkoutMeasurementHelper extends SQLiteOpenHelper {

    public final String COL_TIME_STAMP = "timeStamp";
    public final String TABLE_NAME = "workoutMeasurement";
    public final String COL_WEIGHT_LIFTING_VALUE = "weights";

    /* queries */

    private final String CREATE_WEIGHT_LIFTING = "CREATE TABLE " + TABLE_NAME +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_WEIGHT_LIFTING_VALUE + " INTEGER, " +
            COL_TIME_STAMP + " TEXT);";


    private final String DROP_WEIGHT_LIFTING = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public WorkoutMeasurementHelper(@Nullable Context context) {
        super(context, "workoutmeasurement.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WEIGHT_LIFTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_WEIGHT_LIFTING);
        onCreate(db);
    }
}
