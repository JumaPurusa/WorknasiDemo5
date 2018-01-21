package com.example.jay.worknasidemo5.Model;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;

import java.lang.reflect.Field;

import static java.security.AccessController.getContext;

/**
 * Created by Jay on 11/22/2017.
 */

public class BottomNavigationViewHelper {

    public static void disableShiftMode(BottomNavigationView view){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView)view.getChildAt(0);
        try{
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for(int i=0; i<menuView.getChildCount(); i++){
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                // noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
             e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
