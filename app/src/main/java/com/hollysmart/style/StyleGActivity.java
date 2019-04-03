package com.hollysmart.style;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;

/**
 * Created by cai on 15/10/15.
 */
public abstract class StyleGActivity extends StyleAnimActivity implements View.OnTouchListener, GestureDetector.OnGestureListener{


    GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGestureDetector = new GestureDetector(this, this);
    }



}
