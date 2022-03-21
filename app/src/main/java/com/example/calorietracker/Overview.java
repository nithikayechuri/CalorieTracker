package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> foodList = new ArrayList<>();
        foodList.add("dosa");
        foodList.add("african kids");
        foodList.add("dirt");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,foodList);
        listView.setAdapter(arrayAdapter);
        Button Add = findViewById(R.id.button);
        Add.setOnClickListener(AddClicked);


        };

    public View.OnClickListener AddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newActivity = new Intent(Overview.this,foodSearchBar.class);
            startActivity(newActivity);
        }
    };
}