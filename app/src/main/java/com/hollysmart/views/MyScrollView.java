package com.hollysmart.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by cai on 16/11/29.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean b = super.onInterceptTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            currentY = (int)ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            if (view.canScrollVertically(1))
                return scrollFlag;
        }
        return super.onInterceptTouchEvent(ev);
    }

    int currentY;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Mlog.d("父类 onTouchEvent ======  444" + super.onTouchEvent(ev));

        View child = getChildAt(0);
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            int height = child.getMeasuredHeight();
            height = height - getMeasuredHeight();

            int scrollY = getScrollY();

            int y = (int)ev.getY();
            // 手指向下滑动
            if (currentY < y) {
//                if (scrollY <= 0) {
//                    // 如果向下滑动到头，就把滚动交给父Scrollview
//                    setParentScrollAble(true);
//                    return false;
//                } else {
//                    setParentScrollAble(false);
//
//                }
            } else if (currentY > y) {
                if (scrollY >= height) {
                    // 如果向上滑动到头，就把滚动交给父Scrollview
//                    setParentScrollAble(true);
                    scrollFlag = false;
                    return false;
                }
            }
            currentY = y;
        }

        return super.onTouchEvent(ev);
    }

    private boolean scrollFlag = true;

    public void setScrollFlag(boolean scrollFlag) {
        this.scrollFlag = scrollFlag;
    }

    private RecyclerView view;
    public void setView(RecyclerView view) {
        this.view = view;
    }
}




















