package com.example.jay.worknasidemo5.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jay.worknasidemo5.R;
import com.example.jay.worknasidemo5.TopLevelActivity;

public class WorknasiSplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worknasi_splash_screen);

        Thread thread = new Thread(){
            @Override
            public void run() {

                try{
                    sleep(3000);
                    Intent intent = new Intent(WorknasiSplashScreenActivity.this, TopLevelActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
