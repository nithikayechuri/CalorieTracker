package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ageText= findViewById(R.id.editTextTextPersonName2);
        Button calculateText= findViewById(R.id.button2);
        EditText weightText= findViewById(R.id.editTextTextPersonName4);
        EditText heightText= findViewById(R.id.editTextTextPersonName5);
        EditText inchesText= findViewById(R.id.editTextTextPersonName6);
        calculateText.setOnClickListener(buttonClickListener);
    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v){

            }
        };

}