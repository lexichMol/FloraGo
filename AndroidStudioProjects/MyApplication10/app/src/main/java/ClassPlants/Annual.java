package ClassPlants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Myplants;
import com.example.myapplication.R;
import com.example.myapplication.addPlant;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;

public class Annual extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual);

        // petunia_start
        ImageView img_petunia = (ImageView) findViewById(R.id.img_petunia);
        String url_petunia = "https://rastenievod.com/wp-content/uploads/2016/05/1-98-700x692.jpg";

        Picasso.get()
                .load(url_petunia)
                .into(img_petunia);

        LinearLayout layout_petunia = (LinearLayout) findViewById(R.id.layout_petunia);
        layout_petunia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_petunia, "petunia");
            }
        });
        // petunia_end



    }
    public void back_to_add_plants(View v){
        Intent intent = new Intent(this, addPlant.class);
        startActivity(intent);
    }

    private void showBottomSheetDialog(String url, String name){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        int layout = getResources().getIdentifier("diolog_"+name, "layout", getPackageName());
        bottomSheetDialog.setContentView(layout);


        ImageView main = bottomSheetDialog.findViewById(R.id.main);


        try {
            Picasso.get()
                    .load(url)
                    .into(main);

        } catch (Exception e) {
            main.setScaleType(ImageView.ScaleType.FIT_CENTER);
            main.setImageResource(R.drawable.petals);
        }



        ImageView close = bottomSheetDialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        Button btn_add = bottomSheetDialog.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Annual.this, Myplants.class);

                DatabaseHandler databaseHandler = new DatabaseHandler(Annual.this);
                Bdplants bdplants = new Bdplants(name, url);
                databaseHandler.addPlant(bdplants);
                databaseHandler.getAllPlants();
                List<Bdplants> bdplantslist = databaseHandler.getAllPlants();
                Bdplants bdplant = bdplantslist.get(bdplantslist.size() - 1);
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                databaseHandler.addRecord(new Bdrecords(bdplant.getId(), "plant_add_icon", currentDate, "Растение добавлено"));

                startActivity(intent);
            }
        });
        bottomSheetDialog.show();
    }

}