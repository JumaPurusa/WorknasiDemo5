package com.example.jay.worknasidemo5.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 11/21/2017.
 */

public class HomeTabsAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    public static final List<String> fragmentListTitle = new ArrayList<>();

    public HomeTabsAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentListTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(fragmentList != null){
            return fragmentList.size();
        }else{
            return 0;
        }
    }
}
