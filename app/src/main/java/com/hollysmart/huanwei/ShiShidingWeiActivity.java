package com.hollysmart.huanwei;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hollysmart.style.CaiSlidingBackActivity;
import com.hollysmart.values.Values;

public class ShiShidingWeiActivity extends CaiSlidingBackActivity implements View.OnClickListener {

    private WebView webView;

    @Override
    public int layoutResID() {
        return R.layout.activity_shishi_dingwei;
    }

    private ProgressBar pg1;

    @Override
    public void findView() {
        pg1 = (ProgressBar) findViewById(R.id.progressBar1);
        findViewById(R.id.tv_home).setOnClickListener(this);
        webView = (WebView) findViewById(R.id.wb_webview);
        findViewById(R.id.iv_fanhui).setOnClickListener(this);
        WebSettings settings = webView.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultFontSize(14);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 图片大小
        webView.addJavascriptInterface(new JavaScriptinterface(this),
                "android");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(Values.LOGINURL);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_home:
                Intent homeintent = new Intent(this, MainActivity.class);
                startActivity(homeintent);
                finish();
                break;
        }

    }

    public class JavaScriptinterface {
        Context context;
//        String id;

        public JavaScriptinterface(Context c) {
            context = c;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startAcitvity(String id) {

//            if (!id.equals(this.id)){
//                this.id = id;
            Intent intent = new Intent(ShiShidingWeiActivity.this, XianluActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
//            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();

        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();

        webView.setWebChromeClient(null);

        webView.setWebViewClient(null);
        webView.getSettings().setJavaScriptEnabled(false);

        webView.clearCache(true);
    }

}
