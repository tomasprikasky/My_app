package com.example.myapp.workoutmeasurement.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.myapp.workoutmeasurement.WorkoutMeasurementReading;

import java.util.LinkedList;

public class WorkoutMeasurementReadingDAO extends WorkoutMeasurementHelper {

    /* constructors */
    public WorkoutMeasurementReadingDAO(Context context) {
        super(context);
    }


    /* DAO methods */

    //TODO: insert
    public long addWeightRecord(WorkoutMeasurementReading weight) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_WEIGHT_LIFTING_VALUE, weight.getBodyWeight());
        values.put(COL_TIME_STAMP, weight.getTimeStamp());
        //return insert
        return  writableDatabase.insert(TABLE_NAME, null, values);
    }

    //TODO: selects
    public LinkedList<WorkoutMeasurementReading> getAllWeightRecords() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        LinkedList<WorkoutMeasurementReading> weightRecords = new LinkedList<>();
        Cursor query = readableDatabase.query(
                TABLE_NAME,
                new String[]{COL_WEIGHT_LIFTING_VALUE },
                null,
                null,
                null,
                null,
                null);
        while (query.moveToNext()) {
            int i1 = query.getColumnIndex(COL_WEIGHT_LIFTING_VALUE );
            int weightValue = query.getInt(i1);
            WorkoutMeasurementReading weightRecord = new WorkoutMeasurementReading(weightValue);
            weightRecords.add(weightRecord);
        }

        return weightRecords;
    }

}
