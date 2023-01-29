package com.example.myapp.workoutmeasurement;

import java.util.LinkedList;

/**
 * This interface shall be used to calculate the average body weight
 */
public interface IWorkoutMeasurementCalc {

    WorkoutMeasurementReading calcAverage(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);

    int numberOfMeasurements(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);

    int getLastMeasurement(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);
}
