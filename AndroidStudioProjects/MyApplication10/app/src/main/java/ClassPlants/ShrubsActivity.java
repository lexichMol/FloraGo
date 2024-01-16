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

public class ShrubsActivity extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrubs);

        // Abutilon_start
        ImageView img_abutilon = (ImageView) findViewById(R.id.img_abutilon);
        String url_abutilon = "https://cvetynet.com/wp-content/uploads/2023/11/Abutilon-glavnoe-foto-870x400.webp";

        Picasso.get()
                .load(url_abutilon)
                .into(img_abutilon);

        LinearLayout layout_abutilon = (LinearLayout) findViewById(R.id.layout_abutilon);
        layout_abutilon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_abutilon, "abutilon");
            }
        });
        // Abutilon_end

        // Aglaonema_start
        ImageView img_aglaonema = (ImageView) findViewById(R.id.img_aglaonema);
        String url_aglaonema = "https://cvetynet.com/wp-content/uploads/2023/06/Aglaonema-870x400.webp";

        Picasso.get()
                .load(url_aglaonema)
                .into(img_aglaonema);

        LinearLayout layout_aglaonema = (LinearLayout) findViewById(R.id.layout_aglaonema);
        layout_aglaonema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_aglaonema, "aglaonema");
            }
        });
        // Aglaonema_end


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
                Intent intent = new Intent(ShrubsActivity.this, Myplants.class);

                DatabaseHandler databaseHandler = new DatabaseHandler(ShrubsActivity.this);
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