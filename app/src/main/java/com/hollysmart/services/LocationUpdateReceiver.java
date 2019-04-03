package com.hollysmart.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LocationUpdateReceiver extends BroadcastReceiver {


    private ILocationUpdate locationUpdate;

    public LocationUpdateReceiver(ILocationUpdate locationUpdate) {
        this.locationUpdate = locationUpdate;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        locationUpdate.locationUpdate();
    }
}
