package com.al.utils.maintest;

import android.app.Application;

import com.al.utils.main.AlUtils;


/**
 * Created by ZhangLong on 2017/3/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AlUtils.init(this);
        AlUtils.al.setIsDebug(true);
    }
}
