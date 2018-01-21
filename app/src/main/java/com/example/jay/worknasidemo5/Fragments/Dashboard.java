package com.example.jay.worknasidemo5.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.jay.worknasidemo5.Activities.SearchPropertyActivity;
import com.example.jay.worknasidemo5.Adapters.ImageSlideShowAdapter;
import com.example.jay.worknasidemo5.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jay on 11/21/2017.
 */

public class Dashboard extends Fragment {

    private static int currentPage = 0;
    private static int NUM_IMAGES = 0;
    private View view;
    private ViewPager viewPager;
    private Button searchButton;
    private ArrayAdapter<String> adapter;
    private Spinner locationSpinner;

    public Dashboard(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_dashboard, container, false);

         runTimer();


         locationSpinner = view.findViewById(R.id.location_list);

         adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text, getCityArray());
         adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
         locationSpinner.setAdapter(adapter);

         searchButton = view.findViewById(R.id.searchButton);

         searchButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 final String location = locationSpinner.getSelectedItem().toString();
                 Intent intent = new Intent(getActivity(), SearchPropertyActivity.class);
                 intent.putExtra("location", location);
                 startActivity(intent);
             }
         });

         return view;
    }

    public void runTimer(){
        viewPager = view.findViewById(R.id.viewpager_slider);
        viewPager.setAdapter(new ImageSlideShowAdapter(getActivity()));

        NUM_IMAGES = ImageSlideShowAdapter.images.length;

        final Handler handler = new Handler();
        final Runnable update = new Runnable(){
            @Override
            public void run(){
                if(currentPage == NUM_IMAGES){
                    currentPage = 0;

                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 2000, 2000);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public String[] getCityArray(){

        String cityJson = getCityJSON();
        String[] cityArray = null;
        try {
            JSONObject jsonObject = new JSONObject(cityJson);
            JSONArray jsonArray = jsonObject.getJSONArray("city_scripts");
            cityArray = new String[jsonArray.length()];
            for(int i=0; i<jsonArray.length(); i++){

                JSONObject cityObject = jsonArray.getJSONObject(i);
                cityArray[i] = cityObject.getString("city_name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cityArray;
    }

    public String getCityJSON(){
        String json = null;

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.cityjson);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

    public void getOfficeTypeList(){

        final String[] officeTypes = {"Classroom", "Boardroom", "Meeting room", "Office room"};


    }

}
