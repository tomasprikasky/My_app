package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.workoutmeasurement.DB.WorkoutMeasurementReadingDAO;
import com.example.myapp.workoutmeasurement.WorkoutMeasurementCalcImpl;
import com.example.myapp.workoutmeasurement.WorkoutMeasurementReading;

import java.util.Random;

public class WorkoutMeasurementActivity extends AppCompatActivity {

    ImageView bodyWeightImg;
    EditText bodyWeightValue;
    Button btnSaveWeight;
    Button btnGenerateWeight;
    Button btnWeightBackHome;
    TextView lastMeasurementText;
    TextView lastMeasurement;
    TextView numberOfMeasurementsText;
    TextView numberOfMeasurements;

    WorkoutMeasurementCalcImpl bodyWeight = new WorkoutMeasurementCalcImpl();
    WorkoutMeasurementReadingDAO dataBaseHelper = new WorkoutMeasurementReadingDAO(WorkoutMeasurementActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyweight);

        bodyWeightImg = findViewById(R.id.bodyWeightImg);
        bodyWeightValue = findViewById(R.id.bodyWeightValue);
        btnSaveWeight = findViewById(R.id.btnSaveWeight);
        btnGenerateWeight = findViewById(R.id.btnGenerateWeight);
        lastMeasurementText = findViewById(R.id.lastMeasurementText);
        lastMeasurement = findViewById(R.id.lastMeasurement);
        numberOfMeasurementsText = findViewById(R.id.numberOfMeasurementsText);
        numberOfMeasurements = findViewById(R.id.numberOfMeasurements);
        btnWeightBackHome = findViewById(R.id.btnWeightBackHome);
        btnWeightBackHome.setBackgroundColor(Color.GRAY);

        /* setting values from DB when the page is loaded */

        if( dataBaseHelper.getAllWeightRecords().size() >=1){
            int lastMeasurementValueOnStart = bodyWeight.getLastMeasurement(dataBaseHelper.getAllWeightRecords());
            int numberOfMeasurementsValue = bodyWeight.numberOfMeasurements(dataBaseHelper.getAllWeightRecords());

            lastMeasurement.setText(Integer.toString(lastMeasurementValueOnStart));
            numberOfMeasurements.setText(Integer.toString(numberOfMeasurementsValue));

        }

        /* intent handling data */
        btnWeightBackHome.setOnClickListener(view -> {
            Intent homePage = new Intent(WorkoutMeasurementActivity.this, MainActivity.class);
            int avgWeight = calAverage(view);

            homePage.putExtra("avgWeight", avgWeight);
            setResult(2, homePage);
            finish();
        });
    }




    /* Onclick methods  */

    @SuppressLint("SetTextI18n")
    public void onClickGenerate(View view) {
        int randomRoll = getRandomWeight();
        bodyWeightValue.setText(Integer.toString(randomRoll));
    }


    public void onClickSave(View v) {
        String data = bodyWeightValue.getText().toString();
        WorkoutMeasurementReading bodyWeightReading = new WorkoutMeasurementReading(Integer.parseInt(data));
        dataBaseHelper.addWeightRecord(bodyWeightReading);
        showMeasurements(v);

    }
    /* Private support methods  */

    private int getRandomWeight() {
        Random random = new Random();
        return random.nextInt(220 - 45) + 45;
    }

    public void showMeasurements(View v) {
        int measurements = bodyWeight.numberOfMeasurements(dataBaseHelper.getAllWeightRecords());
        numberOfMeasurements.setText(Integer.toString(measurements));

        int lastMeasurementValue = bodyWeight.getLastMeasurement(dataBaseHelper.getAllWeightRecords());
        lastMeasurement.setText(Integer.toString(lastMeasurementValue));
    }

    public int calAverage(View v) {
        WorkoutMeasurementReading average = bodyWeight.calcAverage(dataBaseHelper.getAllWeightRecords());
        return average.getBodyWeight();
    }


}
