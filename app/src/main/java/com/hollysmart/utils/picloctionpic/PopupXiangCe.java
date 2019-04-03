package com.hollysmart.utils.picloctionpic;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.hollysmart.huanwei.R;
import com.hollysmart.interfaces.MyInterface;

import java.util.List;

/**
 * Created by cai on 16/10/10
 */

public class PopupXiangCe {
    private PopupWindow mPopupWindow;
    private Context mContext;
    private AlbumListAdapter popupAdapter;

    public PopupXiangCe(Context mContext) {
        this.mContext = mContext;
        initPopupWindow();
    }

    private ListView lv_popup;
    public void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_popup, null);
        lv_popup = (ListView) view.findViewById(R.id.lv_popup);
        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissWindow();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (popupIF != null)
                    popupIF.onListener();
            }
        });

        lv_popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupIF != null) {
                    popupIF.item(position);
                    dismissWindow();
                }
            }
        });
    }

    public void showPopuWindow(final View view , List<AlbumBean> popupInfoList) {
        if (popupAdapter == null){
            lv_popup.setAdapter(new AlbumListAdapter(mContext, popupInfoList));
        }else {
            popupAdapter.notifyDataSetChanged();
        }

        Drawable win_bg = mContext.getResources().getDrawable(R.color.heise_b_80);
        mPopupWindow.setBackgroundDrawable(win_bg);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(view);
        mPopupWindow.setFocusable(true);
    }

    public boolean isShowing() {
        if (mPopupWindow != null) {
            if (mPopupWindow.isShowing()) {
                return true;
            }
        }
        return false;
    }

    public void dismissWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }


    private MyInterface.PopupIF popupIF;
    public void setPopupListener(final MyInterface.PopupIF if1) {
        this.popupIF = if1;
    }


}
















































