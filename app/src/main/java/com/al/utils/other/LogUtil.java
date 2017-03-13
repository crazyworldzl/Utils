package com.al.utils.other;

import android.util.Log;

import com.al.utils.main.AlUtils;

/**
 * Created by ZhangLong on 2017/3/10.
 */

public class LogUtil {
    public static void d(String msg){
        if(AlUtils.al.getIsDebug()) {
            Log.d(AlUtils.al.getTag(), msg);
        }
    }
    public static void d(String msg,Throwable tr){
        if(AlUtils.al.getIsDebug()) {
            Log.d(AlUtils.al.getTag(), msg,tr);
        }
    }
    public static void i(String msg){
        if(AlUtils.al.getIsDebug()){
            Log.i(AlUtils.al.getTag(),msg);
        }
    }
    public static void w(String msg,Throwable tr){
        if(AlUtils.al.getIsDebug()){
            Log.w(AlUtils.al.getTag(),msg,tr);
        }
    }

    public static void w(String msg){
        if(AlUtils.al.getIsDebug()){
            Log.w(AlUtils.al.getTag(),msg);
        }
    }
    public static void e(String msg){
        if(AlUtils.al.getIsDebug()){
            Log.e(AlUtils.al.getTag(),msg);
        }
    }
    public static void e(String msg,Throwable tr){
        if(AlUtils.al.getIsDebug()){
            Log.e(AlUtils.al.getTag(),msg,tr);
        }
    }
    public static void v(String msg){
        if(AlUtils.al.getIsDebug()){
            Log.v(AlUtils.al.getTag(),msg);
        }
    }
    public static String getStackTraceString(Throwable tr){
        return Log.getStackTraceString(tr);
    }
    public static boolean isLoggable(String tag, int level){

        return Log.isLoggable(tag,level);
    }
    public static int println(int priority, String tag, String msg){

        return Log.println(priority,tag,msg);
    }
    public static int wtf(String msg){
        if(AlUtils.al.getIsDebug()) {
           return Log.wtf(AlUtils.al.getTag(),msg);
        }
        return 0;
    }
    public static int wtf(String msg,Throwable tr){
        if(AlUtils.al.getIsDebug()) {
           return Log.wtf(AlUtils.al.getTag(),msg,tr);
        }
        return 0;
    }
    public static int wtf(Throwable tr){
           return Log.wtf(AlUtils.al.getTag(),tr);

    }

}
