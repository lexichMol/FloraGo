package Fragments;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Add_record_Activity;
import com.example.myapplication.AllActivity;
import com.example.myapplication.R;

import java.util.Collections;
import java.util.List;

import javax.security.auth.Destroyable;

import Data.DatabaseHandler;
import Model.Bdplants;
import Model.Bdrecords;


public class RecordFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());

        AllActivity activity = (AllActivity) getActivity();
        String id = activity.getid();


        Bdplants bdplants = databaseHandler.getPlant(Integer.parseInt(id.toString()));

        LinearLayout arr_record = (LinearLayout) view.findViewById(R.id.arr_record);



        List<Bdrecords> bdplantslist = databaseHandler.getAllRecords(Integer.parseInt(id.toString()));
        Collections.reverse(bdplantslist);
        for (Bdrecords bdrecords  : bdplantslist){

            TextView line_top = new TextView(getActivity());

            LinearLayout.LayoutParams params_line_top = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            line_top.setBackground(ContextCompat.getDrawable(getActivity(), R.color.green));
            line_top.setTextSize(2);
            float params_pixels_margin_line_top = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
            params_line_top.setMargins(0, 0, 0, (int) params_pixels_margin_line_top);
            line_top.setLayoutParams(params_line_top);


            LinearLayout record_element = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params_record_element = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            record_element.setOrientation(LinearLayout.HORIZONTAL);
            record_element.setLayoutParams(params_record_element);


            ImageView image_record_el = new ImageView(getActivity());
            String img_if = bdrecords.getimage();

            if ("spray_icon".indexOf(img_if) != -1) {

                image_record_el.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.custom_rectangle));
            }
            if ("watering_can".indexOf(img_if) != -1){
                image_record_el.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.custom_rectangle_water));
            }
            if ("fertilizer".indexOf(img_if) != -1){
                image_record_el.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.custom_rectangle_3));
            }
            if ("plant_add_icon".indexOf(img_if) != -1){
                image_record_el.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.custom_rectangle_plant_add));
            }
//            image_record_el.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_rectangle));
            float pixels_image_record_el = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

            String uri = "@drawable/" + bdrecords.getimage(); // database
            int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
//            int imageResource = bdrecords.getimage();

            image_record_el.setImageResource(imageResource);
            LinearLayout.LayoutParams params_image_record_el = new LinearLayout.LayoutParams(
                    (int) pixels_image_record_el,
                    (int) pixels_image_record_el
            );
            float pixels_margin_image_record_el = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
            params_image_record_el.setMargins(0, 0, (int) pixels_margin_image_record_el, 0);
            image_record_el.setLayoutParams(params_image_record_el);


            LinearLayout text_record_el = new LinearLayout(getActivity());
            LinearLayout.LayoutParams params_text_record_el = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            );
            text_record_el.setOrientation(LinearLayout.VERTICAL);
            text_record_el.setLayoutParams(params_text_record_el);



            TextView time_record_el = new TextView(getActivity());
            LinearLayout.LayoutParams params_time_record_el = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            float pixels_margin_time_record_el = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, getResources().getDisplayMetrics());
            params_time_record_el.setMargins(0, 0, 0, (int) pixels_margin_time_record_el);
            float pixels_textsize_time_record_el = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());
            time_record_el.setText(bdrecords.getTime());
            time_record_el.setLayoutParams(params_time_record_el);


            TextView description_record_el = new TextView(getActivity());
            LinearLayout.LayoutParams params_description_record_el = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            description_record_el.setText(bdrecords.getTitle());
            description_record_el.setLayoutParams(params_description_record_el);

            arr_record.addView(line_top);
            record_element.addView(image_record_el);

            text_record_el.addView(time_record_el);
            text_record_el.addView(description_record_el);
            record_element.addView(text_record_el);



            if ("plant_add_icon".indexOf(img_if) == -1){
                ImageView three_point = new ImageView(getActivity());
                three_point.setImageResource(R.drawable.three_dots);
                float pixels_image_three_point = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
                LinearLayout.LayoutParams params_image_three_point = new LinearLayout.LayoutParams(
                        (int) pixels_image_three_point,
                        (int) pixels_image_three_point
                );
                params_image_three_point.gravity = Gravity.CENTER_VERTICAL;
                three_point.setLayoutParams(params_image_three_point);

                PopupWindow attachmentPopup = new PopupWindow(getActivity());
                three_point.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LayoutInflater layoutInflater = getLayoutInflater();
                        int popupWidth = 500;//ViewGroup.LayoutParams.WRAP_CONTENT;
                        int popupHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
                        View popupView = layoutInflater.inflate(R.layout.diologactive, null);

                        TextView btn_del = (TextView) popupView.findViewById(R.id.delete);
                        btn_del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                databaseHandler.delete_record(bdrecords);


                                attachmentPopup.dismiss();
                                AllActivity activity = (AllActivity) getContext();
                                ViewPager2 mviewPager = activity.getViewPager();
                                mviewPager.clearAnimation();
                                activity.initPagerAdapter();
                                AllActivity.getViewPager().setCurrentItem(1, false);
                            }
                        });


                        TextView btn_change = (TextView) popupView.findViewById(R.id.change);
                        btn_change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });


                        attachmentPopup.setFocusable(true);
                        attachmentPopup.setWidth(popupWidth);
                        attachmentPopup.setHeight(popupHeight);
                        attachmentPopup.setContentView(popupView);
                        attachmentPopup.setBackgroundDrawable(new BitmapDrawable());
                        float yoff = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                        attachmentPopup.showAsDropDown(view, 0, (int) yoff * (-1));
                    }
                });
                record_element.addView(three_point);
            }


            arr_record.addView(record_element);


        }
        Button btn_write = (Button) view.findViewById(R.id.write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.diolog_option_rec);

                LinearLayout btn_watering = (LinearLayout) dialog.findViewById(R.id.watering);
                btn_watering.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("care", "Полив");
                        intent.putExtra("img", "watering_can");
                        startActivity(intent);
                    }
                });
                LinearLayout btn_spray = (LinearLayout) dialog.findViewById(R.id.spray);
                btn_spray.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("care", "Опрыскивание");
                        intent.putExtra("img", "spray_icon");
                        startActivity(intent);
                    }
                });
                LinearLayout btn_fertilizer = (LinearLayout) dialog.findViewById(R.id.fertilizer);
                btn_fertilizer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Add_record_Activity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("care", "Удобрение");
                        intent.putExtra("img", "fertilizer");
                        startActivity(intent);
                    }
                });

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                lp.windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
            }
        });

        return view;
    }


}