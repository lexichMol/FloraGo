package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;


public class Myplants extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplants);




        DatabaseHandler databaseHandler = new DatabaseHandler(this);
//        databaseHandler.addPlant(new Bdplants("cactus1", "image1"));
//        databaseHandler.addPlant(new Bdplants("Flower", "cereus"));
//        databaseHandler.addPlant(new Bdplants("cactus]", "cereus"));
//        Bdplants bdplantsdel1 = databaseHandler.getPlant(1);
//        databaseHandler.delete_el(bdplantsdel1);
//        Bdplants bdplantsdel2 = databaseHandler.getPlant(2);
//        databaseHandler.delete_el(bdplantsdel2);
//        Bdplants bdplantsdel3 = databaseHandler.getPlant(3);
//        databaseHandler.delete_el(bdplantsdel3);







        List<Bdplants> bdplantslist = databaseHandler.getAllPlants();
        Collections.reverse(bdplantslist);
        for (Bdplants bdplants : bdplantslist){
            final LinearLayout subLayout = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            float params_pixels_params = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            params.setMargins(0, 0, 0, (int) params_pixels_params);
            params.gravity = Gravity.CENTER;
            subLayout.setLayoutParams(params);



            String uri = bdplants.getImage();


            ImageView imageView = new ImageView(this);
            Picasso.get()
                    .load(uri)
                    .into(imageView);

            float pixels_img = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams params_img = new LinearLayout.LayoutParams(
                    (int) (pixels_img),
                    (int) (pixels_img)
            );
            float params_pixels_img = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
            params_img.setMargins(0, 0, (int) (params_pixels_img), 0);

            imageView.setBackground(ContextCompat.getDrawable(this, R.drawable.radious));
            imageView.setClipToOutline(Boolean.TRUE);

            imageView.setLayoutParams(params_img);





            TextView textView = new TextView(this);

            int strId = getResources().getIdentifier(bdplants.getName(), "string", getPackageName());
            String strValue = getString(strId);

            textView.setText(strValue);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            float params_pixels_text_size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics());
            textView.setTextSize(params_pixels_text_size);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(Color.BLACK);






            ImageButton imageButton = new ImageButton(this);
            imageButton.setBackgroundResource(R.drawable.trash);

            float pixels_del = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) pixels_del,
                    (int) pixels_del
            );

            float params_pixels_img_del = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
            layoutParams.setMargins(0, 0, (int) (params_pixels_img_del), 0);

            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            imageButton.setLayoutParams(layoutParams);
            imageButton.setAdjustViewBounds(true);
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);

            int id = bdplants.getId();

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<Bdrecords> bdrecordslist = databaseHandler.getAllRecords(id);
                    for (Bdrecords bdrecords  : bdrecordslist){
                        databaseHandler.delete_record(bdrecords);
                    }
                    databaseHandler.delete_el(bdplants);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });


            TextView textView_last_rec = new TextView(this);
            List<Bdrecords> records_last_list = databaseHandler.getAllRecords(id);
            Collections.reverse(records_last_list);
            for (Bdrecords bdrecords  : records_last_list){
                String title = bdrecords.getTitle();
                String time = bdrecords.getTime();
                textView_last_rec.setText(title + " " + time);
                break;
            }
            textView_last_rec.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));


            subLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Myplants.this, AllActivity.class);
                    intent.putExtra("id", bdplants.getId());
                    intent.putExtra("frame", "0");
                    startActivity(intent);
                }
            });


            LinearLayout text_layout = new LinearLayout(this);
            LinearLayout.LayoutParams params_text_layout = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
            );
//            float params_pixels_params = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
//            params.setMargins(0, 0, 0, (int) params_pixels_params);
            params.gravity = Gravity.CENTER_VERTICAL;
            text_layout.setOrientation(LinearLayout.VERTICAL);
            text_layout.setLayoutParams(params_text_layout);


            subLayout.addView(imageView);
            text_layout.addView(textView);
            text_layout.addView(textView_last_rec);
            subLayout.addView(text_layout);
            subLayout.addView(imageButton);

            LinearLayout LinearLayout_main = (LinearLayout) findViewById(R.id.scrolLinearLayout);
            LinearLayout_main.addView(subLayout);

            Log.d("Id " + bdplants.getId() + ", Name " + bdplants.getName() + ", img ", bdplants.getImage());
        }

    }





    public void go_to_add_plant(View v){
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, addPlant.class);
        startActivity(intent);
    }
    public void menu_go_home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}