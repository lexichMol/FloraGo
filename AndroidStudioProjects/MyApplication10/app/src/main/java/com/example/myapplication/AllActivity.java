package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import Data.DatabaseHandler;
import Fragments.AdviceFragment;
import Fragments.RecordFragment;
import Fragments.ViewPageAdapter;
import Model.Bdplants;

public class AllActivity extends AppCompatActivity {
    String id;
    TabLayout mtabLayout;
    private static ViewPager2 mviewPager;
    RecordFragment recordFragment;
    AdviceFragment adviceFragment;
    ViewPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);



        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();
        String frame = arguments.get("frame").toString();
        int number_frame = Integer.parseInt(frame);
        Bdplants bdplants = databaseHandler.getPlant(Integer.parseInt(id.toString()));



        TextView name = (TextView) findViewById(R.id.name);
        int strlight_int = getResources().getIdentifier(bdplants.getName(), "string", getPackageName());
        String strname = getString(strlight_int);
        name.setText(strname);



        mtabLayout = findViewById(R.id.tablayout);
        mviewPager = findViewById(R.id.viewPager);

        adviceFragment = new AdviceFragment();
        recordFragment = new RecordFragment();

        mviewPager.setOffscreenPageLimit(2);

        adapter = new ViewPageAdapter(this);

        adapter.add(adviceFragment, "Уход");
        adapter.add(recordFragment, "Журнал");

        mviewPager.setAdapter(adapter);

        new TabLayoutMediator(mtabLayout, mviewPager, adapter.configurationTitle()).attach();
        mviewPager.setCurrentItem(number_frame, false);


    }
    public void initPagerAdapter(){
        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(1);

    }
    public static ViewPager2 getViewPager() { return mviewPager; }


    public String getid(){
        return id;
    }

    public void all_back_to_main(View v){

        Intent intent = new Intent(this, Myplants.class);
        startActivity(intent);

    }



}