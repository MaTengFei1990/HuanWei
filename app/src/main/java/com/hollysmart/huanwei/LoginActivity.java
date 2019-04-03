package com.hollysmart.huanwei;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hollysmart.APIs.UserLoginAPI;
import com.hollysmart.dialog.LoadingProgressDialog;
import com.hollysmart.style.StyleSlidingBackAnimActivity;
import com.hollysmart.utils.ACache;
import com.hollysmart.utils.Utils;
import com.hollysmart.values.UserInfo;
import com.hollysmart.values.Values;
import com.vondear.rxtools.RxAnimationTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialog;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.vondear.rxtools.view.dialog.RxDialogShapeLoading;


public class LoginActivity extends StyleSlidingBackAnimActivity implements View.OnClickListener,UserLoginAPI.LoginInfoIF {
    private EditText ed_username;
    private EditText ed_password;
    private boolean passwordFlag = false;
    private RxDialogLoading lpd;
    private Context mContext;
    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private ScrollView mScrollView;
    private LinearLayout mContent;
    private ImageView mLogo;
    private View mService;
    private ImageView mIvCleanPhone;
    private ImageView mCleanPassword;
    private ImageView mIvShowPwd;

    @Override
    public int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void findView() {
        RxBarTool.setTransparentStatusBar(this);//状态栏透明化
        RxBarTool.StatusBarLightMode(this);
        findViewById(R.id.bn_login).setOnClickListener(this);
//        findViewById(R.id.tv_find).setOnClickListener(this);

        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_password = (EditText) findViewById(R.id.ed_password);
        mScrollView = (ScrollView) findViewById(R.id.scrollview);
        mContent = (LinearLayout) findViewById(R.id.content);
        mLogo = (ImageView) findViewById(R.id.logo);
        mService = (View) findViewById(R.id.Service);
        mIvCleanPhone = (ImageView) findViewById(R.id.iv_clean_phone);
        mCleanPassword = (ImageView) findViewById(R.id.clean_password);
        mIvShowPwd = (ImageView) findViewById(R.id.iv_show_pwd);
        mIvCleanPhone.setOnClickListener(this);
        mCleanPassword.setOnClickListener(this);
        mIvShowPwd.setOnClickListener(this);

    }

    @Override
    public void init() {
        mContext = this;
        setLpd();
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }

        ed_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        ed_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    ed_password.setSelection(s.length());
                }
            }
        });

        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        RxAnimationTool.zoomIn(mLogo, 0.6f, dist);
                    }
                    mService.setVisibility(View.INVISIBLE);

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        RxAnimationTool.zoomOut(mLogo, 0.6f);
                    }
                    mService.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void setLpd() {
        lpd = new RxDialogLoading(this);
        lpd.getTextView().setText("正在登录中，请稍等...");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.bn_login:
                login();
                break;
//            case R.id.tv_find:
//                break;
            case R.id.iv_clean_phone:
                ed_username.setText("");
                break;
            case R.id.clean_password:
                ed_password.setText("");
                break;
            case R.id.iv_show_pwd:
                if (ed_password.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    ed_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.mipmap.mima_after);
                } else {
                    ed_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.mipmap.mima);
                }
                String pwd = ed_password.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    ed_password.setSelection(pwd.length());
                break;
        }
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }


    private void login(){
        String username = ed_username.getText().toString().trim();
        if (Utils.isEmpty(username)){
//            RxToast.error(this, R.string.str_tishi_username);
            RxToast.info(mContext, "请输入用户名", Toast.LENGTH_SHORT, true).show();
            return;
        }
        String password = ed_password.getText().toString().trim();
        if (Utils.isEmpty(password)){
//            Utils.showToast(this, R.string.str_tishi_password);
            RxToast.info(mContext,"请输入密码", Toast.LENGTH_SHORT, true).show();
            return;
        }
        lpd.show();
        new UserLoginAPI(username, Utils.md5Sign(password), this).request();
    }

    @Override
    public void loginResult(boolean isOk, String msg) {
        if (isOk){
            UserInfo userInfo = new UserInfo();
            userInfo.setAccess_token("Bearer " + msg);
            ACache.get(this, Values.CACHE_USER).put(Values.CACHE_USERINFO, userInfo);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }else {
            lpd.cancel();
            Utils.showToast(mContext, R.string.err_login);
            ed_password.setText("");
            ed_username.setText("");
        }

    }
}
