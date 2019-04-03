package com.hollysmart.views;

import android.support.v7.widget.RecyclerView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.wuxiaolong.pullloadmorerecyclerview.RecyclerViewOnScroll;

/**
 * Created by cai on 16/11/30.
 */

public class MyRecycViewOnScroll extends RecyclerViewOnScroll {
    private MyScrollView mScrollView;

    public MyRecycViewOnScroll(PullLoadMoreRecyclerView pullLoadMoreRecyclerView, MyScrollView scrollView) {
        super(pullLoadMoreRecyclerView);
        mScrollView = scrollView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (!recyclerView.canScrollVertically(-1)){
            mScrollView.setScrollFlag(true);
        }
    }
}
