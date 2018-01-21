package com.example.jay.worknasidemo5.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/21/2017.
 */

public class Map extends Fragment {

    public Map(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_map, container, false);

         return view;
    }
}
