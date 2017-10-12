package com.al.utils.maintest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.al.utils.R;
import com.al.utils.annotation.BindJSInterface;
import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindView;
import com.al.utils.core.CoreActivity;
import com.al.utils.other.JSInterface;
import com.al.utils.other.LogUtil;

/**
 * Created by ZhangLong on 2017/3/14.
 */

public class TestWebView extends CoreActivity implements View.OnClickListener {
    @BindView(id = R.id.testweb_wv)
    private WebView webView;
    @BindView(id = R.id.testweb_btn, isClick = true)
    private Button btn;
    @BindView(id = R.id.testweb_et)
    private EditText et;
    @BindJSInterface(method = "hh")
    public JSInterface hh = new JSInterface() {
        @Override
        @JavascriptInterface
        public void method() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:changeTi('ddddd')");
                }
            });
        }
    };



    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testweb);
        BindUtils.view.bindView(this);
        btn.setOnClickListener(this);
        BindUtils.web.bindJSInterface(this, webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                return super.onJsAlert(view, url, message, result);
                AlertDialog.Builder builder = new AlertDialog.Builder(TestWebView.this);
                builder.setTitle("来自张龙的关怀！").setMessage(message).setNegativeButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
                return true;
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                LogUtil.d(request.getUrl().toString() + "===" + request.getRequestHeaders().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.testweb_btn:
                webView.loadUrl("javascript:changeTi('"+et.getText().toString()+"')");
                break;
        }
    }
}
