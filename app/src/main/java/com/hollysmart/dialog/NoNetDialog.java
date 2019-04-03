package com.hollysmart.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hollysmart.huanwei.R;


public class NoNetDialog {
	private Context context;

	public NoNetDialog(Context context) {
		this.context = context;
		WifiDialog();
//		newWifiDialog();
	}
	private void WifiDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("温馨提示");
		builder.setMessage("没网络，no problem，您可以把私藏的我喊出来！记得点击离线导览哦！");
		builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				((Activity) context).finish();
			}
		});
		
		builder.setPositiveButton("离线导览",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
//						Intent intent = new Intent(context, LiXianActivity.class);
//						intent.putExtra(LiXianActivity.ENTER, false);
//						context.startActivity(intent);
//						((Activity) context).overridePendingTransition( R.anim.activity_enter_right, R.anim.activity_yuandian);
//						((Activity) context).finish();
					}
				});
		builder.setNeutralButton("设置网络", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = null;
				// 判断手机系统的版本 即API大于10 就是3.0或以上版本
				if (android.os.Build.VERSION.SDK_INT > 10) {
					intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				} else {
					intent = new Intent();
					ComponentName component = new ComponentName(
							"com.android.settings",
							"com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_yuandian);
				((Activity) context).finish();
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
}
