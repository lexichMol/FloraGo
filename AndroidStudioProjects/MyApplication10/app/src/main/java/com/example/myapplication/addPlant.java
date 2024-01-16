package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ClassPlants.Cactus;
import ClassPlants.ShrubsActivity;

public class addPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
    }
    public void back_my_plants(View v){
        Intent intent = new Intent(this, Myplants.class);
        startActivity(intent);
    }
    public void go_to_cactus(View v){
        Intent intent = new Intent(this, Cactus.class);
        startActivity(intent);
    }
    public void go_to_shrubs(View v){
        Intent intent = new Intent(this, ShrubsActivity.class);
        startActivity(intent);
    }
}