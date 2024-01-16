package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Add_record_Activity;
import com.example.myapplication.AllActivity;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import Data.DatabaseHandler;
import Model.Bdplants;

public class AdviceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());

        AllActivity activity = (AllActivity) getActivity();
        String id = activity.getid();


        Bdplants bdplants = databaseHandler.getPlant(Integer.parseInt(id.toString()));
        View view = inflater.inflate(R.layout.fragment_advice, container, false);


        int strlight_int = getResources().getIdentifier(bdplants.getName()+"_text_light", "string", getActivity().getPackageName());
        String strlight = getString(strlight_int);
        TextView light_text = (TextView) view.findViewById(R.id.light_text);
        light_text.setText(strlight);

        int watering_int = getResources().getIdentifier(bdplants.getName()+"_text_light", "string", getActivity().getPackageName());
        String strwatering = getString(watering_int);
        TextView watering_text = (TextView) view.findViewById(R.id.watering_text);
        watering_text.setText(strwatering);

        int fertilizer_int = getResources().getIdentifier(bdplants.getName()+"_text_light", "string", getActivity().getPackageName());
        String strfertilizer = getString(fertilizer_int);
        TextView fertilizer_text = (TextView) view.findViewById(R.id.fertilizer_text);
        fertilizer_text.setText(strfertilizer);

        int transfer_int = getResources().getIdentifier(bdplants.getName()+"_text_light", "string", getActivity().getPackageName());
        String strtransfer = getString(transfer_int);
        TextView transfer_text = (TextView) view.findViewById(R.id.transfer_text);
        transfer_text.setText(strtransfer);

        LinearLayout linear_spray = (LinearLayout)  view.findViewById(R.id.linear_spray);
        linear_spray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("care", "Опрыскивание");
                intent.putExtra("img", "spray_icon");
                startActivity(intent);
            }
        });

        LinearLayout linear_watering = (LinearLayout)  view.findViewById(R.id.linear_watering);
        linear_watering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("care", "Полив");
                intent.putExtra("img", "watering_can");
                startActivity(intent);
            }
        });

        LinearLayout linear_fertizer = (LinearLayout)  view.findViewById(R.id.linear_fertizer);
        linear_fertizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                intent.putExtra("id", id);
                intent.putExtra("care", "Удобрение");
                intent.putExtra("img", "fertilizer");
                startActivity(intent);
            }
        });
        ImageView mainImage = (ImageView) view.findViewById(R.id.main_image_all);
        Picasso.get()
                .load(bdplants.getImage())
                .into(mainImage);

        return view;
    }


}