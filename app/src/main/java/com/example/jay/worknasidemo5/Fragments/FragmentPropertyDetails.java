package com.example.jay.worknasidemo5.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jay.worknasidemo5.Activities.SearchPropertyActivity;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
import com.example.jay.worknasidemo5.R;

import java.util.List;

/**
 * Created by Kaka on 2/4/2018.
 */

@SuppressLint("ValidFragment")
public class FragmentPropertyDetails extends Fragment {

    private ImageView propertyImage;
    private TextView officeTitle;
    private String office_url;
    private String roomTitle;
    Context context;

    public FragmentPropertyDetails(Context context, String office_url, String roomTitle){
        this.context = context;
        this.office_url = office_url;
        this.roomTitle = roomTitle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_property_detail, container, false);

         propertyImage =  view.findViewById(R.id.office_room_image);
         officeTitle = view.findViewById(R.id.roomTitle);

         Glide.with(context)
                .load("http://app.worknasi.com/app-content/uploads/2018/01/" + office_url)
                .into(propertyImage);

         officeTitle.setText(roomTitle);
         return view;
    }

   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        propertyImage = getActivity().findViewById(R.id.office_room_image);
    }*/

    /*public void passPosition(String office_url){
        Glide.with(getActivity().getApplicationContext())
                .load("http://app.worknasi.com/app-content/uploads/2018/01/" + office_url)
                .into(propertyImage);
    }*/
}
