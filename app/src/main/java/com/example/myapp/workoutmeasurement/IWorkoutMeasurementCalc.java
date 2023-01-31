package com.example.myapp.workoutmeasurement;

import java.util.LinkedList;


public interface IWorkoutMeasurementCalc {

    WorkoutMeasurementReading calcAverage(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);

    int numberOfMeasurements(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);

    int getLastMeasurement(LinkedList<WorkoutMeasurementReading> weightLiftedReadings);
}
