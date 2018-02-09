package com.example.jay.worknasidemo5.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jay.worknasidemo5.Fragments.FragmentPropertyDetails;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
import com.example.jay.worknasidemo5.R;

import static com.example.jay.worknasidemo5.Activities.SearchPropertyActivity.OFFICE_URL;
import static com.example.jay.worknasidemo5.Activities.SearchPropertyActivity.ROOM_TITLE;

public class PropertyDetailActivity extends AppCompatActivity {

    private FragmentPropertyDetails fragmentPropertyDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        Intent intent = getIntent();
        String office_image = intent.getStringExtra(OFFICE_URL);
        String office_title = intent.getStringExtra(ROOM_TITLE);

        if(savedInstanceState == null){
            fragmentPropertyDetails = new FragmentPropertyDetails(this, office_image, office_title);
            getSupportFragmentManager().beginTransaction().add(R.id.contents, fragmentPropertyDetails, "FragmentPropertyDetails")
                    .commit();
        }


    }


}
