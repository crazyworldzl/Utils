package com.al.utils.other;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.al.utils.main.AlUtils;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by ZhangLong on 2017/3/13.
 */

public class ActivityManage {
    private final Stack<Activity> activitys = new Stack<Activity>();
    public void pushActivity(Activity a){
        activitys.add(a);
    }
    public Activity popActivity(){
        return activitys.pop();
    }
    public Stack<Activity> getActivitys(){
        return activitys;
    }
    public Activity getActivity(int index){
        return activitys.get(index);
    }
    public void removActivity(int index){
        activitys.remove(index);
    }
    public void exit(){
        for (Activity a:activitys) {
            ToastUtil.newText(a.getTitle().toString());
            AlUtils.al.getApp().onTerminate();
        }
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activitys) {
            if (null != activity) {
                activity.finish();
            }
        }
        activitys.clear();
    }
    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void AppExit() {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) AlUtils.al.getApp().getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(AlUtils.al.getApp().getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
