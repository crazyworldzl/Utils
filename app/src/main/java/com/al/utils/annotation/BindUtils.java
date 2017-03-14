package com.al.utils.annotation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.webkit.WebView;

import com.al.utils.other.JSInterface;
import com.al.utils.other.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZhangLong on 2017/3/10.
 */

public class BindUtils {
    public static class view{
        /**
         *
         * @param activity Activity
         */
        public static void bindView(final Activity activity) {
            bindView(activity, activity.getWindow().getDecorView());
        }

        /**
         *
         * @param o
         * @param view
         */
        public static void bindView(final Object o, final View view) {
            Field[] declaredFields = o.getClass().getDeclaredFields();
            Method[] declaredMethods = o.getClass().getDeclaredMethods();
            for (Field f : declaredFields) {
                f.setAccessible(true);
                BindView anno = f.getAnnotation(BindView.class);
                if (anno == null) continue;
                try {
                    f.set(o, view.findViewById(anno.id()));
                    if (anno.click()) {
                        for (final Method m : declaredMethods) {
                            m.setAccessible(true);
                            if (m.getName().equals(anno.method())) {
                                view.findViewById(anno.id()).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            m.invoke(o, v);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class web{
        /**
         *
         * @param o class;
         * @param webView webview;
         */
        @SuppressLint("JavascriptInterface")
        public static void bindJSInterface(Object o, WebView webView) {
            Field[] declaredFields = o.getClass().getDeclaredFields();
            Method[] methods = o.getClass().getDeclaredMethods();
            for (Field f : declaredFields) {
                BindJSInterface annotation = f.getAnnotation(BindJSInterface.class);
                if (annotation == null) continue;
                try {
                    LogUtil.d("loading....");
                    if(f.get(o) instanceof JSInterface){
                        LogUtil.d("设置成功");
                        webView.addJavascriptInterface(f.get(o),annotation.method());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}