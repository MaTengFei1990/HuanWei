package com.hollysmart.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hollysmart.utils.Mlog;

/**
 * Created by cai on 2017/2/21.
 */

public class Cai_RecyclerView extends RecyclerView {


    public Cai_RecyclerView(Context context) {
        super(context);
    }

    public Cai_RecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Cai_RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

     /*处理事件用*/


    private float downX = -1;
    private boolean isCanMore = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                if (downX == -1) {
                    downX = moveX;
                }
                if (downX - moveX > 100) {
//                    Mlog.d("向左滑动");
//                    Mlog.d("computeHorizontalScrollExtent = " + computeHorizontalScrollExtent());
//                    Mlog.d("computeHorizontalScrollOffset = " + computeHorizontalScrollOffset());
//                    Mlog.d("computeHorizontalScrollRange  = " + computeHorizontalScrollRange());
                    if ((computeHorizontalScrollOffset() + computeHorizontalScrollExtent()) >= computeHorizontalScrollRange()) {
                        if (isCanMore && scrollOnMore != null) {
                            isCanMore = false;
                            Mlog.d("加载更多");
                            scrollOnMore.onMore();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isCanMore = true;
                downX = -1;
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * scrollState = SCROLL_STATE_TOUCH_SCROLL(1)：表示正在滚动。当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1
     * scrollState =SCROLL_STATE_FLING(2) ：表示手指做了抛的动作（手指离开屏幕前，用力滑了一下，屏幕产生惯性滑动）。
     * scrollState =SCROLL_STATE_IDLE(0) ：表示屏幕已停止。屏幕停止滚动时为0。
     *
     * @param state
     */


    private OnMoreLeftScroll scrollOnMore;
    public void setOnMore(OnMoreLeftScroll scrollOnMore) {
        this.scrollOnMore = scrollOnMore;
    }
    public interface OnMoreLeftScroll {
        void onMore();
    }
}





















