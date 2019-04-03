package com.hollysmart.huanwei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hollysmart.APIs.TongJiAPI;
import com.hollysmart.utils.ACache;
import com.hollysmart.values.HWStaticInfo;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class TuBiaoActivity extends AppCompatActivity implements TongJiAPI.TongJiAPIIF {
    private ColumnChartView columnChartView;
    private ColumnChartData data;

    public String[] week;
    private UserInfo userInfo;
    private List<HWStaticInfo>staticList;

    private List<Integer> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_biao);
        findView();
        initData();

    }

    private void findView() {
        columnChartView = (ColumnChartView) findViewById(R.id.Column);
        Object obj = ACache.get(getApplicationContext(), Values.CACHE_USER).getAsObject(Values.CACHE_USERINFO);
        if (obj != null) {
            userInfo = (UserInfo) obj;
        }
        staticList = new ArrayList<>();
        new TongJiAPI(userInfo,"facility",this).request();
    }

    private void initData() {
    }

    @Override
    public void getReuslt(String msg) {
        Gson mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        staticList = mGson.fromJson(msg, new TypeToken<List<HWStaticInfo>>() {
        }.getType());
        week = new String[staticList.size()];
        for (int i = 0; i < staticList.size(); i++) {
            week[i] = staticList.get(i).getAreaName();
            list.add(staticList.get(i).getTotalTypeNum());

        }
        setFirstChart(week.length);

    }


    private void setFirstChart(int numColumns) {
        // 使用的 7列，每列1个subcolumn。
        int numSubcolumns = 1;
        //定义一个圆柱对象集合
        List<Column> columns = new ArrayList<Column>();
        //子列数据集合
        List<SubcolumnValue> values;

        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        //遍历列数numColumns
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            //遍历每一列的每一个子列
            for (int j = 0; j < numSubcolumns; ++j) {
                //为每一柱图添加颜色和数值
                float f = list.get(i);
                values.add(new SubcolumnValue(f, ChartUtils.pickColor()));
            }
            //创建Column对象
            Column column = new Column(values);
            //这一步是能让圆柱标注数据显示带小数的重要一步 让我找了好久问题
            ColumnChartValueFormatter chartValueFormatter = new SimpleColumnChartValueFormatter(2);
            column.setFormatter(chartValueFormatter);
            //是否有数据标注
            column.setHasLabels(true);
            //是否是点击圆柱才显示数据标注
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
            //给x轴坐标设置描述
            axisValues.add(new AxisValue(i).setLabel(week[i]));
        }
        //创建一个带有之前圆柱对象column集合的ColumnChartData
        data= new ColumnChartData(columns);

        //定义x轴y轴相应参数
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("设施数量");//轴名称
        axisY.hasLines();//是否显示网格线
        axisY.setTextColor(R.color.ERROR_COLOR);//颜色
//        axisX.setHasTiltedLabels(true);// 设置X轴文字向左旋转45度
        axisX.hasLines();
        axisX.setTextColor(R.color.SUCCESS_COLOR);
        axisX.setValues(axisValues);
        //把X轴Y轴数据设置到ColumnChartData 对象中
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        //给表填充数据，显示出来
        columnChartView.setColumnChartData(data);
    }
}
