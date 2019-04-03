package com.hollysmart.style;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


import com.hollysmart.huanwei.R;
import com.vondear.rxtools.RxBarTool;

import java.lang.reflect.Method;

/**
 * @author cai
 * @author 2014.08.12
 */

public abstract class CaiSlidingBackActivity extends BaseSwipeBackActivity implements
		OnClickListener {

	private View contentView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (checkDeviceHasNavigationBar(this)){
			setContentView(R.layout.daolan_style);
			LinearLayout content = (LinearLayout) findViewById(true,R.id.content);
			contentView = LayoutInflater.from(this).inflate(layoutResID(), null);
			content.addView(contentView);
		}else
			setContentView(layoutResID());
		findView();
		init();
	}


	@Override
	public View findViewById(int id) {
		if (checkDeviceHasNavigationBar(this)){
			return contentView.findViewById(id);
		}else
			return super.findViewById(id);
	}
	public View findViewById(boolean isSuper, int id) {
		if (!isSuper){
			return findViewById(id);
		}else
			return super.findViewById(id);
	}


	/**
	 * layout绑定
	 */
	public abstract int layoutResID();

	/**
	 * 控件绑定
	 */
	public abstract void findView();

	/**
	 * 逻辑操作
	 */
	public abstract void init();


	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			finish();
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.hollysmart.ExitApp");
		this.registerReceiver(broadcastReceiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(broadcastReceiver);
	}

	public void closeOther() {
		Intent intent = new Intent();
		intent.setAction("com.hollysmart.ExitApp");
		this.sendBroadcast(intent);
	}




	private boolean checkDeviceHasNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hasNavigationBar;

	}
	//	获取NavigationBar的高度：
	private int getNavigationBarHeight(Context context) {
		int navigationBarHeight = 0;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
		if (id > 0 && checkDeviceHasNavigationBar(context)) {
			navigationBarHeight = rs.getDimensionPixelSize(id);
		}
		return navigationBarHeight;
	}

}
