package com.al.utils.maintest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindJSInterface;
import com.al.utils.core.CoreActivity;
import com.al.utils.other.JSInterface;
import com.al.utils.other.LogUtil;
import com.al.utils.other.ToastUtil;

/**
 * Created by ZhangLong on 2017/3/14.
 */

public class TestWebView extends CoreActivity {
    private WebView webView;
    @BindJSInterface(method = "hh")
    public JSInterface hh = new JSInterface() {
        @Override
        @JavascriptInterface
        public void method() {
            LogUtil.d("进入method");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.text("hehheheheheh");
                }
            });
        }
    };
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        BindUtils.web.bindJSInterface(this,webView);
        setContentView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                webView.loadUrl(request.getUrl());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.loadUrl("file:///android_asset/main.html");
    }
}
