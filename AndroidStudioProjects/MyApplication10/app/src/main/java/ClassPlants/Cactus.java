package ClassPlants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Myplants;
import com.example.myapplication.R;
import com.example.myapplication.addPlant;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;
import Plants.Cereus;

public class Cactus extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cactus);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        // Cereus_start
        ImageView img_cereus = (ImageView) findViewById(R.id.img_cereus);
        String url_cereus = "https://cvetynet.com/wp-content/uploads/2023/04/TSereus-Hildmanna-768x576.webp";

        Picasso.get()
                .load(url_cereus)
                .into(img_cereus);

        LinearLayout layout_cereus = (LinearLayout) findViewById(R.id.layout_cereus);
        layout_cereus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_cereus, "cereus");
            }
        });
        // Cereus_end

        // RebutiaCereus_start
        ImageView img_rebutia = (ImageView) findViewById(R.id.img_rebutia);
        String url_rebutia = "https://cvetynet.com/wp-content/uploads/2023/06/Rebutsiya-800x400.jpg";

        Picasso.get()
                .load(url_rebutia)
                .into(img_rebutia);

        LinearLayout layout_rebutia = (LinearLayout) findViewById(R.id.layout_rebutia);
        layout_rebutia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(url_rebutia, "rebutia");
            }
        });
        // Rebutia_end




//        try {
//            bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://cvetynet.com/wp-content/uploads/2023/04/TSereus-Hildmanna-768x576.webp").getContent());
//            img_cereus.setImageBitmap(bitmap);
//
//        } catch (Exception e) {
//            img_cereus.setImageResource(R.drawable.petals);
//        }





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
                Intent intent = new Intent(Cactus.this, Myplants.class);

                DatabaseHandler databaseHandler = new DatabaseHandler(Cactus.this);
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