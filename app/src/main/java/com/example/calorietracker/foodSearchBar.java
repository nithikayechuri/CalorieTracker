package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class foodSearchBar extends AppCompatActivity {

    List<Food> foodList = new ArrayList<>();
    Button searchBtn;
    EditText searchTxt;
    ListView listViewFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search_bar);
        loadData();
        searchBtn = findViewById(R.id.searchBtn);
        searchTxt = findViewById(R.id.editTextFoodSearch);
        searchBtn.setOnClickListener(searchListener);
        listViewFood = findViewById(R.id.listviewfood);
    }

    public void loadData() {
        InputStream inputStream = getResources().openRawResource(R.raw.nutrition);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                foodList.add(new Food(row[1], Integer.parseInt(row[3])));
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
    }

    private List<Food> filteredFood(String input) {
        return foodList.stream()
                .filter(f -> f.getName().toLowerCase().contains(input.toLowerCase())
                        || input.toLowerCase().contains(f.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<String> foodStrings(List<Food> matches) {
        List<String> foodStrings = new ArrayList<>();
        for (Food food : matches) {
            foodStrings.add(food.getName() + ", " + food.getCalories() + " cal");
        }
        return foodStrings;
    }

    public View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String input = searchTxt.getText().toString();
            List<Food> matches = filteredFood(input);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(foodSearchBar.this,
                    android.R.layout.simple_list_item_1, foodStrings(matches));
            listViewFood.setAdapter(adapter);
            listViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    Intent intent = new Intent();
                    intent.setAction("FOODADDED");
                    intent.putExtra("data",selectedItem);
                    sendBroadcast(intent);
                    finish();
                }
            });
        }
    };
}