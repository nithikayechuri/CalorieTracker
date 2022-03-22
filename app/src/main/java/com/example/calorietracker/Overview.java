package com.example.calorietracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Overview extends AppCompatActivity {

    String currentDate;
    List<String> foodLog;
    TextView dateText;
    BroadcastReceiver _foodReceiver;
    SharedPreferences sharedPref;
    TextView caloriesDoneTextView;
    ProgressBar progressBar;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        caloriesDoneTextView = findViewById(R.id.caloriesDoneTextView);
        progressBar = findViewById(R.id.progressBar);
        IntentFilter filter = new IntentFilter("FOODADDED");
        _foodReceiver = new NewFoodReceiver();
        this.registerReceiver(_foodReceiver, filter);
        dateText = findViewById(R.id.dateText);

        currentDate = getCurrentDate();
        foodLog = getTodaysFoodLog();

        dateText.setText(currentDate);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,foodLog);
        listView.setAdapter(arrayAdapter);
        Button logFoodItem = findViewById(R.id.button);
        logFoodItem.setOnClickListener(AddClicked);
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("MM/dd/yy", Locale.getDefault()).format(new Date());
    }

    private List<String> getTodaysFoodLog() {
        return new ArrayList<>();
    }

    public View.OnClickListener AddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newActivity = new Intent(Overview.this,foodSearchBar.class);
            startActivity(newActivity);
        }
    };
    public class NewFoodReceiver extends BroadcastReceiver {
        private static final String TAG = "MyBroadcastReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            Snackbar.make(findViewById(R.id.topViewOverview), "Item Saved", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                //Undo Adding foo
                        }
                    })

                    .show();
            String data = intent.getExtras().getString("data");
            int selectedFoodCal = Integer.parseInt(data.split(",")[1].split(" ")[1]);
            foodLog.add(data);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Overview.this, android.R.layout.simple_list_item_1,foodLog);
            listView.setAdapter(arrayAdapter);
            String caloriesConsumed = getApplicationContext().getSharedPreferences("_", MODE_PRIVATE).getString("calorieString","");
            int caloriesEatenSoFar = Integer.parseInt(caloriesConsumed.split("/")[0]);
            int caloriesTotalGoal = Integer.parseInt(caloriesConsumed.split("/")[1]);
            caloriesEatenSoFar += selectedFoodCal;
            getApplicationContext().getSharedPreferences("_", MODE_PRIVATE).edit().putString("calorieString",caloriesEatenSoFar+"/"+caloriesTotalGoal).apply();
            caloriesDoneTextView.setText(caloriesEatenSoFar +"/" + caloriesTotalGoal);
            progressBar.setProgress((int)((double)caloriesEatenSoFar/(double)caloriesTotalGoal * 100));
        }
    }
    //
}