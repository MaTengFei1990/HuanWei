package com.hollysmart.tools;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;

import com.hollysmart.values.DirctroyBean;

import java.util.Calendar;
import java.util.List;

/**
 * Created by cai on 16/8/23.
 */
public class UtilsDialog {
    private static UtilsDialog instance = null;

    public static UtilsDialog getInstance() {
        synchronized (UtilsDialog.class) {
            if (instance == null) {
                instance = new UtilsDialog();
            }
        }
        return instance;
    }

    //车站选择
    public interface DataCallBack {
        void callBack(DirctroyBean bean);
    }

    public void getData(Context mContext, final List<DirctroyBean>list, String flagcar, final DataCallBack callBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (flagcar.equals("car")) {
            builder.setItems(listToStrings2(list), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DirctroyBean stationinfoBean = list.get(which);
                    callBack.callBack(stationinfoBean);
                    list.clear();
                }
            });

        } else {
            builder.setTitle("请选择所属区域");
            builder.setItems(listToStrings1(list), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DirctroyBean stationinfoBean = list.get(which);
                    callBack.callBack(stationinfoBean);
                    list.clear();
                }
            });

        }
        builder.create().show();
    }

    private String[] listToStrings1(List<DirctroyBean> beans) {
        String[] names = new String[beans.size()];
        for (int i = 0; i < beans.size(); i++) {
            names[i] = beans.get(i).getLabel();
        }
        return names;
    }
    private String[] listToStrings2(List<DirctroyBean> beans) {
        String[] names = new String[beans.size()];
        for (int i = 0; i < beans.size(); i++) {
            names[i] = beans.get(i).getLabel();
        }
        return names;
    }


//    倍速

    public void getBeiSuData(Context mContext, final List list, final BeiSuCallBack callBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setItems(listToStrings3(list), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String stationinfoBean = (String) list.get(which);
                    callBack.callBack(stationinfoBean);
                    list.clear();
                }
            });

        builder.create().show();
    }

    public interface BeiSuCallBack {
        void callBack(String  String );
    }
    private String[] listToStrings3(List beans) {
        String[] names = new String[beans.size()];
        for (int i = 0; i < beans.size(); i++) {
            names[i] = (String) beans.get(i);
        }
        return names;
    }



    //日期选择
    public interface DateCallBack {
        void date(int year, int month, int day);
    }

    public void DateXuanzhe(Context mContext, final DateCallBack dateCallBack) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {
                dateCallBack.date(year, month, dayOfMonth);
            }
        },
                c.get(Calendar.YEAR), // 传入年份
                c.get(Calendar.MONTH), // 传入月份
                c.get(Calendar.DAY_OF_MONTH) // 传入天数
        ).show();
    }

}












