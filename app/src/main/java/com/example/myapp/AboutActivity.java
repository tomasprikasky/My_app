package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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