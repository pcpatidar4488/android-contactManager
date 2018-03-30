package com.example.berylsystems.apiusingservices.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.berylsystems.apiusingservices.R;
import com.example.berylsystems.apiusingservices.object_preference.AppUser;
import com.example.berylsystems.apiusingservices.object_preference.LocalRepositories;
import com.example.berylsystems.apiusingservices.shared_preference.Preferences;

public class SplashActivity extends AppCompatActivity {

    AppUser appUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appUser = LocalRepositories.getAppUser(this);
        if (null == appUser) {
            appUser = new AppUser();
            LocalRepositories.saveAppUser(this, appUser);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                finish();
            }
        },3*1000);
    }
}
