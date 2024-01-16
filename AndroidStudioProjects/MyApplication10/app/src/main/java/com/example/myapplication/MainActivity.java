package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import Data.DatabaseHandler;
import Model.Bdrecords;
import Plants.Cereus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
//        databaseHandler.addRecord(new Bdrecords(1, "cer", "16:00", "Поливка"));
//        databaseHandler.addRecord(new Bdrecords(1, "kaktus", "16:30", "Удобрение"));
//        databaseHandler.addRecord(new Bdrecords(2, "cereus2", "2:50", "Полив"));
//        databaseHandler.addRecord(new Bdrecords(3, "cereus3", "23:05", "Удобрение"));
//        Bdplants bdplantsdel1 = databaseHandler.getPlant(1);
//        databaseHandler.delete_el(bdplantsdel1);
//        Bdplants bdplantsdel2 = databaseHandler.getPlant(2);
//        databaseHandler.delete_el(bdplantsdel2);
//        Bdplants bdplantsdel3 = databaseHandler.getPlant(3);
//        databaseHandler.delete_el(bdplantsdel3);


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

    }
}