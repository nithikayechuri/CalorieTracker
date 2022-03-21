package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText ageText;
    Button calculateText;
    EditText weightText;
    EditText heightText;
    EditText inchesText;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ageText= findViewById(R.id.editTextTextPersonName2);
        calculateText= findViewById(R.id.button2);
        weightText= findViewById(R.id.editTextTextPersonName4);
        heightText= findViewById(R.id.editTextTextPersonName5);
        inchesText= findViewById(R.id.editTextTextPersonName6);
        radioGroup1= findViewById(R.id.radioGroup1);
        radioGroup2= findViewById(R.id.radioGroup2);
        calculateText.setOnClickListener(buttonClickListener);

    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            String ageTextString= ageText.getText().toString();
            String weightTextString= weightText.getText().toString();
            String heightTextString= heightText.getText().toString();
            String inchesTextString= inchesText.getText().toString();
            boolean isMale=false;
            int activityLevel=0;
            if(radioGroup2.getCheckedRadioButtonId()==R.id.moderateRadio){
                activityLevel= 1;
            }
            else if(radioGroup2.getCheckedRadioButtonId()==R.id.activeRadio){
                activityLevel=2;
            }
            if(radioGroup1.getCheckedRadioButtonId()==R.id.maleRadio){
                isMale=true;
            }
            double weight = Double.parseDouble(weightTextString) * 0.4534; // kg
            int feet = Integer.parseInt(heightTextString); // feet
            double inches = Double.parseDouble(inchesTextString); // inches
            double height = (feet * 12 + inches) * 2.54; // cm
            double age = Double.parseDouble(ageTextString); // years

            double BMR;
            int calories;
            if (isMale) {
                BMR = 10 * weight + 6.25 * height - 5 * age + 5;
            } else {
                BMR = 10 * weight + 6.25 * height - 5 * age - 161;
            }
            if (activityLevel==1) {
                calories = (int) (BMR * 1.2);
            } else if (activityLevel==2) {
                calories = (int) (BMR * 1.375);
            } else {
                calories = (int) (BMR * 1.55);
            }

            Intent intent = new Intent(MainActivity.this, CalorieActivity.class);
            intent.putExtra("calories", calories);
            startActivity(intent);
            // Switch to a different activity here
        }
    };

}