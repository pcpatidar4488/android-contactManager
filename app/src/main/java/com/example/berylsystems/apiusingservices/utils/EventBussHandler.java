package com.example.berylsystems.apiusingservices.utils;

import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by BerylSystems on 17-Mar-18.
 */

public class EventBussHandler extends AppCompatActivity {
    public EventBussHandler(){
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }
}
