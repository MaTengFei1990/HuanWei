package com.hollysmart.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by cai on 2017/2/20.
 */

public class CaiPopupWindow extends PopupWindow{
    public CaiPopupWindow(Context context) {
        super(context);
    }

    public CaiPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CaiPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }



    @Override
    public void showAsDropDown(View anchorView, int xoff, int yoff) {

        if(Build.VERSION.SDK_INT ==  Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, xoff, a[1] + anchorView.getHeight() + yoff);
        } else {
            super.showAsDropDown(anchorView, xoff, yoff);
        }
    }
}
