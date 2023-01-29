package com.example.myapp.workoutmeasurement;

/**
 * This class shall be used to contain a reading of
 * body weight readings, as well as the corresponding timestamp
 * when the measurement was taken
 *
 */
public class WorkoutMeasurementReading {

    private int id;
    private int weightLifted;
    private long timeStamp;

    public int getId(){return id;}
    public void setBodyWeight(int sys) {
        this.weightLifted = sys;
    }
    public int getBodyWeight() {
        return weightLifted;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public WorkoutMeasurementReading(int bodyWeight){
        this.weightLifted = bodyWeight;
        this.timeStamp = System.currentTimeMillis();
    }

    public WorkoutMeasurementReading(){}
}
