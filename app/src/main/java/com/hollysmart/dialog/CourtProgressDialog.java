package com.hollysmart.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.hollysmart.huanwei.R;

import java.text.DecimalFormat;


public class CourtProgressDialog {
	private AlertDialog dialog;
	private TextView tv;
	private ProgressBar pb;
	private Handler handler;
	private int max;
	
	public CourtProgressDialog(Context context){
		View view = LayoutInflater.from(context).inflate(R.layout.view_dialog_progress, null);
		dialog = new AlertDialog.Builder(context).setTitle("数据下载中").setView(view).create();
		dialog.setCancelable(false);
		pb = (ProgressBar) view.findViewById(R.id.pb_dialog);
		tv = (TextView)view.findViewById(R.id.tv_dialog);
		tv.setText("连接中，请稍候。");
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				tv.setText("已完成："+tvNum(msg.arg1)+"/"+tvMax(max));
			}
		};
	}
	public void show(){
		dialog.show();
	}
	public void setMax(int max){
		this.max = max;
		pb.setMax(max);
	}
	public void setValues(int values){
		pb.setProgress(values);
		Message msg = new Message();
		msg.arg1 = values;
		handler.sendMessage(msg);
	}
	public void cancel(){
		dialog.cancel();
	}

	private String tvMax(int max){
		if (max >= 1024){
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(max / 1024f) + "MB";
		}else {
			return  max + "KB";
		}
	}
	private String tvNum(int num){
		if (num >= 1024){
			DecimalFormat df = new DecimalFormat("#.00");
			return df.format(num / 1024f) + "MB";
		}else {
			return  num + "KB";
		}
	}

}
