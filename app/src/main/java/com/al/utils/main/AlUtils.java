package com.al.utils.main;

import android.app.Activity;
import android.app.Application;

import com.al.utils.other.ActivityManage;
import com.al.utils.other.LogUtil;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by ZhangLong on 2017/3/10.
 */
public class AlUtils {
    public static AlUtils al = new AlUtils();
    private static Application app;
    private boolean isDebug;
    private String tag="zl";
    private ActivityManage activityManage = new ActivityManage();
    public static AlUtils init(Application a) {
        if(al==null){
            al = new AlUtils();
        }
        al.app = a;
        if(app==null){
            LogUtil.d("AlUtil 初始化失败");
        }
        return al;
    }
    public ActivityManage getActivityMange(){
        return activityManage;
    }
    public Application getApp() {
        return app;
    }

    public void setIsDebug(boolean b) {
        isDebug = b;
    }

    public boolean getIsDebug() {
        return isDebug;
    }
    public void setTag(String tag){
        this.tag = tag;
    }
    public String getTag(){
        return tag;
    }
}
