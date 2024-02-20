package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import Data.DatabaseHandler;
import Model.Bdrecords;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        List<Bdrecords> bdrecordslist = databaseHandler.getAllRecords(1);
        for (Bdrecords bdrecord : bdrecordslist){
            Log.d("Id ", bdrecord.getId() + ", id_pl " + bdrecord.getPlant_id() + ", name " + bdrecord.getimage() + ", time " + bdrecord.getTime() + ", Title " + bdrecord.getTitle());
        }
    }
    public void menu_my_plants(View v){
        Intent intent = new Intent(this, Myplants.class);
        startActivity(intent);
    }
    public void menu_user(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);

    }

}