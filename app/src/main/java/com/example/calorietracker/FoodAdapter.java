package com.example.calorietracker;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    Activity context;
    List<Food> foodList;
    private static LayoutInflater inflater = null;

    public FoodAdapter(Activity context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_item, null): itemView;
        TextView name = (TextView) itemView.findViewById(R.id.nameText);
        TextView calorie = (TextView) itemView.findViewById(R.id.calorieText);
        Food selectedFood = foodList.get(i);
        name.setText(selectedFood.getName());
        calorie.setText(selectedFood.getCalories());
        return itemView;
    }
}
