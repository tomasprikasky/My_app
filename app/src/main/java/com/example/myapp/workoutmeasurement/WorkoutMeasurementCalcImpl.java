package com.example.myapp.workoutmeasurement;


import java.util.LinkedList;

public class WorkoutMeasurementCalcImpl implements IWorkoutMeasurementCalc {

    @Override
    public WorkoutMeasurementReading calcAverage(LinkedList<WorkoutMeasurementReading> weightLiftedReadings) {
        int bodyWeights = 0;

        WorkoutMeasurementReading avrBodyWeight = new WorkoutMeasurementReading();


        for (WorkoutMeasurementReading weightLiftedReading : weightLiftedReadings) {
            bodyWeights += weightLiftedReading.getBodyWeight();
        }
        if(weightLiftedReadings.size()!=0) {
            int avg = bodyWeights / weightLiftedReadings.size();
            avrBodyWeight.setBodyWeight(avg);
            return avrBodyWeight;
        }

        return new WorkoutMeasurementReading(0);
    }

    @Override
    public int numberOfMeasurements(LinkedList<WorkoutMeasurementReading> weightLiftedReadings) {
        int numberOfMeasurements = weightLiftedReadings.size();
        return numberOfMeasurements;
    }

    @Override
    public int getLastMeasurement(LinkedList<WorkoutMeasurementReading> weightLiftedReadings) {
        int valueOfLastMeasurement = weightLiftedReadings.getLast().getBodyWeight();
        return valueOfLastMeasurement;
    }

}