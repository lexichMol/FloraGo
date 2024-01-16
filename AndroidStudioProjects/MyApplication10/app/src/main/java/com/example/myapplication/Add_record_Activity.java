package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;

public class Add_record_Activity extends AppCompatActivity {
    String id;
    String calendar_day;
    String calendar_year;
    String calendar_month;
    String calendar_last_date;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();
        String care = arguments.get("care").toString();
        image = arguments.get("img").toString();

        ImageView image_record = (ImageView) findViewById(R.id.image_record);
        String uri = "@drawable/" + image; // database
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        image_record.setImageResource(imageResource);




        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Bdplants bdplants = databaseHandler.getPlant(Integer.parseInt(id.toString()));

        TextView text_record = (TextView) findViewById(R.id.text_record);
        text_record.setText(care);


        int strId = getResources().getIdentifier(bdplants.getName(), "string", getPackageName());
        String strValue = getString(strId);
        TextView text_name_plant = (TextView) findViewById(R.id.text_name_plant);
        text_name_plant.setText(strValue);

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        EditText time = (EditText) findViewById(R.id.time_txt);
        String title = care + " в " + currentTime;
        time.setText(title);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView Date = (TextView) findViewById(R.id.calendar_date);
        calendar_last_date = currentDate;
        Date.setText(calendar_last_date);




        PopupWindow attachmentPopup = new PopupWindow(this);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Add_record_Activity.this);
                dialog.setContentView(R.layout.calendar_diolog);


                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.windowAnimations = R.style.DialogAnimation;
                CalendarView calendar = (CalendarView) dialog.findViewById(R.id.calendarView);

                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                                    int date) {
                        calendar_day = String.valueOf(date);
                        calendar_year = String.valueOf(year);
                        calendar_month = String.valueOf(month);
                        calendar_last_date = calendar_day+"-"+calendar_month+"-"+calendar_year;
                        Date.setText(calendar_last_date);
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setAttributes(lp);
                dialog.show();

            }
        });

        TextView add_text = (TextView) findViewById(R.id.add);
        add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String title_txt = String.valueOf(time.getText());
                databaseHandler.addRecord(new Bdrecords(Integer.parseInt(id), image, calendar_last_date, title_txt));
                Intent intent = new Intent(Add_record_Activity.this, AllActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("frame", "1");
                startActivity(intent);
//                Log.d("date", calendar_day + " " + calendar_year + " " + calendar_month + "   " + image);
            }
        });

    }

    public void back(View v){
        Intent intent = new Intent(this, AllActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("frame", "1");
        startActivity(intent);
    }
}