package com.example.ecomersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.slider.Slider;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class imageActivity extends AppCompatActivity {

    public static String imageSliderArray = "";
    public static String DESCRIPSTION = "";


    ArrayList<SlideModel> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageSlider image_slider = findViewById(R.id.image_slider);
        TextView  textView = findViewById(R.id.textView);

        try {
            JSONArray jsonArray = new JSONArray(imageSliderArray);
            if (jsonArray.length()>0){
                JSONArray imageLinkArry = jsonArray.getJSONArray(0);

                for (int x=0;x<imageLinkArry.length();x++){
                    String imageLinl = imageLinkArry.getString(x);

                    imageList.add(new SlideModel(imageLinl,null));
                }
            }


        } catch (JSONException e) {
           e.printStackTrace();
        }
        image_slider.setImageList(imageList);
        textView.setText(DESCRIPSTION);

    }
}