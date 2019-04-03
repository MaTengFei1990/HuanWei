package com.hollysmart.utils;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkDetector {  
   
    public static boolean detect(Activity act) {
        
       ConnectivityManager manager = (ConnectivityManager) act
              .getApplicationContext().getSystemService(  
                     Context.CONNECTIVITY_SERVICE);
        
       if (manager == null) {  
           return false;  
       }  
        
       NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        
       if (networkinfo == null || !networkinfo.isAvailable()) {  
           return false;  
       }  
   
       return true;  
    }

    public static boolean isGpsEnable(Context mContext) {
        LocationManager locationManager = ((LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE));
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}