package com.example.jay.worknasidemo5.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/20/2017.
 */

public class FindSpace extends Fragment {


    public FindSpace(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_findspace, container, false);



         return view;
    }
}
