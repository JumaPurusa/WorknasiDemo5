package com.example.jay.worknasidemo5.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.jay.worknasidemo5.Adapters.CustomRowOneAdapter;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
import com.example.jay.worknasidemo5.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 11/23/2017.
 */

public class SearchPropertyActivity extends AppCompatActivity implements CustomRowOneAdapter.OnItemClickListener {

    public static final String OFFICE_URL = "imageUrl";
    public static final String ROOM_TITLE = "title";
    //public static final String ROOM_TYPE = "type";
    //public static final String ROOM_PRICE = "price";
    //public static final String ROOM_DURATION = "duration";

    private RecyclerView recyclerView;
    private CustomRowOneAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    public  List<OfficeRoom> office_list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_property);

        Toolbar toolbar = (Toolbar)findViewById(R.id.searchedToolBar);
        toolbar.setPadding(0, 0, 0, 0);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        String location = getIntent().getStringExtra("location").toString();
        Toast.makeText(this, location, Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomRowOneAdapter(this, office_list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(SearchPropertyActivity.this);

        Load_data load_data = new Load_data();
        load_data.execute(location);

        /*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                 if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == office_list.size()-1){
                    new Load_data().execute(office_list.get(office_list.size()-1).getId());
                }
            }
        });
        */
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SearchPropertyActivity.this, PropertyDetailActivity.class);
        OfficeRoom officeRoom = office_list.get(position);
        intent.putExtra(OFFICE_URL, officeRoom.getRoomImage())
              .putExtra(ROOM_TITLE, officeRoom.getRoomTitle());

        Toast.makeText(this, officeRoom.getRoomImage(), Toast.LENGTH_SHORT ).show();
        startActivity(intent);

    }

    public class Load_data extends AsyncTask<String, Void, Void> {
        public final String LOG_TAG = Load_data.class.getSimpleName();

        public Load_data(){

        }

        public void getRoomJsonData(String roomJsonStr) throws JSONException{
            JSONArray jsonArray = new JSONArray(roomJsonStr);

            // OfficeRoom[] rooms = new OfficeRoom[jsonArray.length()];
            for(int i=0; i<jsonArray.length(); i++){

                int property_id;
                String property_name, property_type_name, duration, image;
                double price;
                JSONObject roomObject = jsonArray.getJSONObject(i);
                property_id = roomObject.getInt("property_id");
                property_name = roomObject.getString("property_name");
                property_type_name = roomObject.getString("property_type_name");
                price = roomObject.getDouble("price");
                duration = roomObject.getString("duration");
                image = roomObject.getString("image");

                OfficeRoom officeRoom = new OfficeRoom(property_id,image, property_name, property_type_name, price, duration);
                office_list.add(officeRoom);
            }
        }

        @Override
        protected Void doInBackground(String... params) {

            String roomJsonStr = null;

            HttpURLConnection urlConnection = null;
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            final String BASE_URL = "http://app.worknasi.com/property_scripts.php";
            String city_location = params[0];

            try {

                URL url = new URL(BASE_URL);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                Log.v(LOG_TAG, "Built Uri: " + BASE_URL.toString());

                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("city_name", "UTF-8")+"="+URLEncoder.encode(city_location, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // get the inputStream into a string
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                if(inputStream == null){
                    return  null;
                }

                StringBuffer buffer = new StringBuffer();
                String line = " ";
                while((line = bufferedReader.readLine()) != null){
                    buffer.append(line+"\n");
                }

                roomJsonStr = buffer.toString();

                Log.v(LOG_TAG, "roomJsonStr : " + buffer.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }

                if (bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                getRoomJsonData(roomJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();

        }
    }
}
