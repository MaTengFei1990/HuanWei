package com.hollysmart.style;

import android.content.Intent;
import android.os.Bundle;

import com.hollysmart.huanwei.R;
import com.hollysmart.utils.Mlog;

/**
 * 实现activity退出是的动画效果
 * intent.putExtra("animType",1);
 * animType 1：上退出 、2：下退出 、3：左退出 、4：右退出 、5：中间缩回退出 、6：左退出 350ms、7：右退出 350ms。
 * @author caipc
 */
public abstract class StyleSlidingBackAnimActivity extends CaiSlidingBackActivity {
	public int animType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
//			Toast.makeText(this, "创建", Toast.LENGTH_SHORT).show();
			animType = savedInstanceState.getInt("animType");
		}
		animType = getIntent().getIntExtra("animType", 4);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Mlog.d("保存");
		outState.putInt("animType", animType);
	}


	@Override
	public void startActivity(Intent intent) {
		Mlog.d("start");
		super.startActivity(intent);
		animEnter(intent.getIntExtra("animType", 4));
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		animEnter(intent.getIntExtra("animType", 4));
	}

	@Override
	public void finish() {
		super.finish();
		animExit();
	}
	private void animEnter(int animType){

		Mlog.d("animType = " + animType);
		switch (animType) {
		case 1:
			overridePendingTransition(R.anim.activity_enter_shang, R.anim.activity_yuandian);
			break;
		case 2:
			overridePendingTransition(R.anim.activity_enter_xia, R.anim.activity_yuandian);
			break;
		case 3:
			overridePendingTransition(R.anim.activity_enter_left, R.anim.activity_yuandian);
			break;
		case 4:
			overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_yuandian);
			break;
		case 5:
			overridePendingTransition(R.anim.activity_enter_soufang, R.anim.activity_yuandian);
			break;
		case 6:
			overridePendingTransition(R.anim.activity_enter_long_left, R.anim.activity_yuandian);
			break;
		case 7:
			overridePendingTransition(R.anim.activity_enter_long_right, R.anim.activity_yuandian);
			break;
		}
	}

	private void animExit(){
		switch (animType) {
		case 1:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_shang);
			break;
		case 2:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_xia);
			break;
		case 3:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_left);
			break;
		case 4:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_right);
			break;
		case 5:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_soufang);
			break;
		case 6:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_long_left);
			break;
		case 7:
			overridePendingTransition(R.anim.activity_yuandian, R.anim.activity_exit_long_right);
			break;
		}
	}


	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		animExit();
	}

}
