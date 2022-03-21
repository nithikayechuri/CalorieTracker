package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalorieActivity extends AppCompatActivity {

    TextView calorieText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        calorieText = findViewById(R.id.calorieText);

        Bundle extras = getIntent().getExtras();
        int calories = extras.getInt("calories");
        System.out.println(calories);

        calorieText.setText(Integer.toString(calories));
        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(okButtonClicked);
    }
    public View.OnClickListener okButtonClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            Intent newActivity = new Intent(CalorieActivity.this,Overview.class);
            startActivity(newActivity);
        }
    };
}