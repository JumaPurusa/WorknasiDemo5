package com.example.jay.worknasidemo5;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jay.worknasidemo5.Fragments.Account;
import com.example.jay.worknasidemo5.Fragments.FindSpace;
import com.example.jay.worknasidemo5.Fragments.HomeFragment;
import com.example.jay.worknasidemo5.Fragments.SpaceFragment;
import com.example.jay.worknasidemo5.Model.BottomNavigationViewHelper;

public class TopLevelActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                    return true;
                case R.id.available_space:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SpaceFragment()).commit();
                    return true;
                case R.id.add:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindSpace()).commit();
                    return true;

                case R.id.account:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Account()).commit();
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarTop);
        toolbar.setPadding(0, 0, 0, 0);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(false); //disable the button
        getSupportActionBar().setDisplayShowHomeEnabled(false); // remove the icon*/
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView bnav = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bnav);
        bnav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();


    }

    /*public void onClickHome(View view){

        // this line will enable haptic feedback
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    public void onClickAvailable(View view){
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SpaceFragment()).commit();
    }

    public void onClickFindMe(View view){
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FindSpace()).commit();
    }

    public void onClickAccount(View view){
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Account()).commit();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_right_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Go to Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms_privacy:
                Toast.makeText(this, "Go to terms and privacy", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
