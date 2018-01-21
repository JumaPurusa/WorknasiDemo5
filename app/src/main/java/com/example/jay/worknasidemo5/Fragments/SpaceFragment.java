package com.example.jay.worknasidemo5.Fragments;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.jay.worknasidemo5.Adapters.CustomAdapter;
import com.example.jay.worknasidemo5.Activities.SearchPropertyActivity;
import com.example.jay.worknasidemo5.Adapters.CustomAdapter;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
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
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jay on 11/20/2017.
 */

public class SpaceFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    List<OfficeRoom> office_list = new ArrayList<>();

    public SpaceFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_space, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomAdapter(getActivity(), office_list);

        if(recyclerView != null){
            recyclerView.setAdapter(adapter);
        }

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Load_data_from_server load_data = new Load_data_from_server();
        load_data.execute(0);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == office_list.size()-1){
                  new Load_data_from_server().execute(office_list.get(office_list.size()-1).getId());
                }
            }
        });
    }

    private class Load_data_from_server extends AsyncTask<Integer, Void, Void>{

        String roomJsonStr = null;
        public Load_data_from_server(){

        }

        @Override
        protected Void doInBackground(Integer... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                final String BASE_URL = "http://app.worknasi.com/room_scripts.php?";
                final String QUERY_PARAM = "id";
                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM, String.valueOf(params[0]))
                        .build();
                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();

                // get the input stream into a string
                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream ==  null){
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line = " ";
                while((line = reader.readLine()) != null){
                    buffer.append(line).append("\n");
                }

                if(buffer == null){
                    return null;
                }

                roomJsonStr = buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(urlConnection != null){
                    urlConnection.disconnect();
                }

                if(reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                JSONArray jsonArray = new JSONArray(roomJsonStr);

                for(int i=0; i<jsonArray.length(); i++){

                    int id;
                    String image, property_name, property_type_name,duration;
                    double price;
                    JSONObject roomObject = jsonArray.getJSONObject(i);
                    id = roomObject.getInt("property_id");
                    image = roomObject.getString("image");
                    property_name = roomObject.getString("property_name");
                    property_type_name = roomObject.getString("property_type_name");
                    price = roomObject.getDouble("price");
                    duration = roomObject.getString("duration");

                    OfficeRoom officeRoom = new OfficeRoom(id, image, property_name, property_type_name, price, duration);
                    office_list.add(officeRoom);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
             adapter.notifyDataSetChanged();
        }
    }

}
