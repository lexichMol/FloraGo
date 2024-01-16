package Plants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ClassPlants.Cactus;
import com.example.myapplication.Myplants;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;


public class Cereus extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cereus);
        String url = "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_1080,q_100,w_1080/v1/gcs/platform-data-goog/events/1024px-Android_Studio_icon.svg_3u7h3BG.png";
        ImageView imageView = findViewById(R.id.cereus_cactus);
        Picasso.get().load(url).into(imageView);
        ImageView button = (ImageView) findViewById(R.id.back_st);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("cereus", "ok");
            }
        });

    }

    public void back(View v){
        Intent intent = new Intent(Cereus.this, Cactus.class);

        startActivity(intent);
    }
    public void add_element(View v){
        Intent intent = new Intent(Cereus.this, Myplants.class);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Bdplants bdplants = new Bdplants("cereus", "cereus");
        databaseHandler.addPlant(bdplants);
        databaseHandler.getAllPlants();
        List<Bdplants> bdplantslist = databaseHandler.getAllPlants();
        Bdplants bdplant = bdplantslist.get(bdplantslist.size() - 1);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        databaseHandler.addRecord(new Bdrecords(bdplant.getId(), "plant_add_icon", currentDate, "Растение добавлено"));


//        String id = String.valueOf(bdplant.getId());
//        Log.d("idishnik", id);



        startActivity(intent);
    }

}