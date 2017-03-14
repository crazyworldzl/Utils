package com.al.utils.other;

import android.annotation.SuppressLint;
import android.webkit.JavascriptInterface;

/**
 * Created by ZhangLong on 2017/3/14.
 */
@SuppressLint("JavascriptInterface")
public abstract class JSInterface {
    @JavascriptInterface
    public abstract void method();
}
