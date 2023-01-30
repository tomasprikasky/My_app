package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.workoutmeasurement.DB.WorkoutMeasurementReadingDAO;
import com.example.myapp.workoutmeasurement.WorkoutMeasurementCalcImpl;
public class MainActivity extends AppCompatActivity {
    Button btnBodyWeight;
    TextView weightAvgValue;

    WorkoutMeasurementReadingDAO dataBaseHelperWeight = new WorkoutMeasurementReadingDAO(MainActivity.this);
    WorkoutMeasurementCalcImpl bodyWeightCalc = new WorkoutMeasurementCalcImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBodyWeight = findViewById(R.id.btnBodyWeight);

        weightAvgValue = findViewById(R.id.weightAvgValue);


        /* setting values from DB when the page is loaded */
        showAvgDataFromDatabase();


        /* Intents for BloodPressure & BodyWeight Activities */

        btnBodyWeight.setOnClickListener(view -> {

            Intent bodyWeightPage = new Intent(MainActivity.this, WorkoutMeasurementActivity.class);
            startActivityForResult(bodyWeightPage, 2);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        showAvgDataFromDatabase();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

            /* Getting data from activities */
            int avgWeight = data.getIntExtra("avgWeight", 0);

            weightAvgValue.setText("Tva prumerna vaha je: ".concat(String.valueOf(avgWeight)));

        }
    }

    void showAvgDataFromDatabase() {

        if (dataBaseHelperWeight.getAllWeightRecords().size() >= 1) {
            int avgWeight = bodyWeightCalc.calcAverage(dataBaseHelperWeight.getAllWeightRecords()).getBodyWeight();
            weightAvgValue.setText("Your average weight is: ".concat(String.valueOf(avgWeight)));

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_about:
                Intent about=new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(about);
                finish();
                break;
            case R.id.nav_diary:
                Intent diary=new Intent(getApplicationContext(), WorkoutMeasurementActivity.class);
                startActivity(diary);
                finish();
                break;
            case R.id.nav_home:
                Intent home=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
