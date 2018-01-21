package com.example.jay.worknasidemo5.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.worknasidemo5.Adapters.HomeTabsAdapter;
import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/20/2017.
 */

public class HomeFragment extends Fragment {


    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomeTabsAdapter homeTabsAdapter;

    public HomeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container, false);

         homeTabsAdapter = new HomeTabsAdapter(getFragmentManager());
         viewPager = view.findViewById(R.id.pager);
         setupViewPager(viewPager);

         tabLayout = view.findViewById(R.id.tabs);
         tabLayout.setupWithViewPager(viewPager);
         setUpTabTitle();

         return view;
    }

    public void setUpTabTitle(){
        tabLayout.getTabAt(0).setText(HomeTabsAdapter.fragmentListTitle.get(0));
        tabLayout.getTabAt(1).setText(HomeTabsAdapter.fragmentListTitle.get(1));
    }

    public void setupViewPager(ViewPager viewPager){
         HomeTabsAdapter adapter = new HomeTabsAdapter(getFragmentManager());
         adapter.addFragment(new Dashboard(), "DASHBOARD");
         adapter.addFragment(new Map(), "MAP");
         viewPager.setAdapter(adapter);
    }


}
