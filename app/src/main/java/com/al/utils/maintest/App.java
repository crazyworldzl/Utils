package com.al.utils.maintest;

import android.app.Application;
import android.preference.PreferenceManager;

import com.al.utils.main.AlUtils;
import com.al.utils.other.LogUtil;


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

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
