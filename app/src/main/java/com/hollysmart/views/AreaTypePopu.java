package com.hollysmart.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.APIs.getDataListAPI;
import com.hollysmart.huanwei.R;
import com.hollysmart.values.DirctroyBean;
import com.hollysmart.values.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunpengfei on 2017/8/8.
 */

public class AreaTypePopu {
    private String Type;
    private Context mContext;
    private ListView lv_popup;
    private PopupWindow mPopupWindow;
    private List<DirctroyBean>areaList;
    private PopupAdapter popupAdapter;
    private UserInfo userInfo;

    public AreaTypePopu(String Type, Context mContext, UserInfo userInfo) {
        this.Type = Type;
        this.mContext = mContext;
        this.userInfo = userInfo;
        initData();
    }

    private void initData() {
        areaList = new ArrayList<>();
        DirctroyBean bean = new DirctroyBean();
        bean.setLabel("所有");
        bean.setValue(null);
        areaList.add(bean);
        new getDataListAPI(userInfo,Type, new getDataListAPI.DataListIF() {
            @Override
            public void getResult(String str) {
                Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                List<DirctroyBean> dirList = mGson.fromJson(str, new TypeToken<List<DirctroyBean>>() {
                }.getType());
                areaList.addAll(dirList);
                initwindow();
                if (popupAdapter != null) {
                    popupAdapter.notifyDataSetChanged();
                } else {
                    popupAdapter = new PopupAdapter(areaList);
                    lv_popup.setAdapter(popupAdapter);
                }

            }
        }).request();
    }

    private void initwindow() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_popup_1, null);
        lv_popup = (ListView) view.findViewById(R.id.lv_popup);
        mPopupWindow = new PopupWindow(view,  ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissWindow();
            }
        });

        lv_popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (popupIF != null) {
                    popupIF.item(1, position, areaList.get(position));
                    dismissWindow();
                }
            }
        });

    }

    public void dismissWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public interface PopupShaiXuanIF {
        void item(int type, int position, DirctroyBean bean);
    }

    private PopupShaiXuanIF popupIF;



    public void showPopuWindow(final View view) {
        if (popupAdapter == null) {
            popupAdapter = new PopupAdapter(areaList);
            lv_popup.setAdapter(popupAdapter);
        } else {
            popupAdapter = new PopupAdapter(areaList);
            lv_popup.setAdapter(popupAdapter);
        }

        Drawable win_bg = mContext.getResources().getDrawable(R.color.baise);
        mPopupWindow.setBackgroundDrawable(win_bg);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(view);
        mPopupWindow.setFocusable(true);
    }


    public void setPopupListener(final PopupShaiXuanIF if1) {
        this.popupIF = if1;
    }

    private class PopupAdapter extends BaseAdapter {
//
        private List<DirctroyBean> quyuBeanList;

        public PopupAdapter(List<DirctroyBean> quyuBeanList) {
            this.quyuBeanList = quyuBeanList;
        }


        @Override
        public int getCount() {
            return areaList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView != null && convertView.getTag() != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popup_zhong, null);
                holder = new ViewHolder();
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            }

            String title = areaList.get(position).getLabel();
            holder.tv_title.setText(title);

//            if (areaList.get(position).isTag()) {
//                holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.text_zise));
//            } else {
//                holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.huise_text));
//            }
            return convertView;
        }

        private class ViewHolder {
            TextView tv_title;
        }
    }
}
