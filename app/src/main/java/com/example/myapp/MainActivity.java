package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.workoutmeasurement.DB.WorkoutMeasurementReadingDAO;
import com.example.myapp.workoutmeasurement.WorkoutMeasurementCalcImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Button btnBodyWeight;
    TextView weightAvgValue;
    TextView currentTempText;
    TextView currentTemp;
    int temperature;
    WorkoutMeasurementReadingDAO dataBaseHelperWeight = new WorkoutMeasurementReadingDAO(MainActivity.this);
    WorkoutMeasurementCalcImpl bodyWeightCalc = new WorkoutMeasurementCalcImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBodyWeight = findViewById(R.id.btnBodyWeight);

        weightAvgValue = findViewById(R.id.weightAvgValue);

        currentTempText = findViewById(R.id.currentTempText);
        currentTemp = findViewById(R.id.currentTemp);

        //nastaveni hodnot z db, kdyz se stranka nacita
        showAvgDataFromDatabase();




        btnBodyWeight.setOnClickListener(view -> {

            Intent bodyWeightPage = new Intent(MainActivity.this, WorkoutMeasurementActivity.class);
            startActivityForResult(bodyWeightPage, 2);
        });


    //API

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    String uri = "https://api.openweathermap.org/data/2.5/weather?q=Hodonin,cz&APPID=268e549e36646048006f58be1eed345b";

    StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("MainActivity", response);
            try {
                JSONObject weatherInfo = new JSONObject(response);
                JSONObject main = weatherInfo.getJSONObject("main");
                int temp = main.getInt("temp");
                temperature = (temp-273);
                currentTemp.setText(String.valueOf(temperature));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("MainActivity", String.valueOf(error));
        }
    });
        requestQueue.add(stringRequest);


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

            weightAvgValue.setText("Tvá průměrná váha je: ".concat(String.valueOf(avgWeight)));

        }
    }

    void showAvgDataFromDatabase() {

        if (dataBaseHelperWeight.getAllWeightRecords().size() >= 1) {
            int avgWeight = bodyWeightCalc.calcAverage(dataBaseHelperWeight.getAllWeightRecords()).getBodyWeight();
            weightAvgValue.setText("Tvá průměrná váha je: ".concat(String.valueOf(avgWeight)));

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
