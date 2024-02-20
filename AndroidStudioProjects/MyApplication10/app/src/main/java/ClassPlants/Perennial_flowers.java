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

public class Perennial_flowers extends AppCompatActivity {

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perennial_flowers);

        // Dianthus_start
        ImageView img_dianthus = (ImageView) findViewById(R.id.img_dianthus);
        String url_dianthus = "https://rastenievod.com/wp-content/uploads/2023/11/1-11-700x577.jpg";

        Picasso.get()
                .load(url_dianthus)
                .into(img_dianthus);

        LinearLayout layout_dianthus = (LinearLayout) findViewById(R.id.layout_dianthus);
        layout_dianthus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_dianthus, "dianthus");
            }
        });
        // Dianthus_end



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
                Intent intent = new Intent(Perennial_flowers.this, Myplants.class);

                DatabaseHandler databaseHandler = new DatabaseHandler(Perennial_flowers.this);
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