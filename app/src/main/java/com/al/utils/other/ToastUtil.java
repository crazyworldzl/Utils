package com.al.utils.other;

import android.content.Context;
import android.widget.Toast;

import com.al.utils.main.AlUtils;

/**
 * Created by ZhangLong on 2017/3/10.
 */

public class ToastUtil {
    private static Toast toast;
    private static Toast mToast;
    private static Context context;
    public static void text(String msg){
       text(msg,Toast.LENGTH_SHORT);
    }
    public static void text(String msg,int duration){
        if(toast==null) {
            toast = Toast.makeText(AlUtils.al.getApp(),msg,duration);
        }
        toast.setDuration(duration);
        toast.setText(msg);
        toast.show();
    }
    public static void newText(String msg){
        Toast.makeText(AlUtils.al.getApp(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void newText(Context c,String msg){
        newText(c,msg,Toast.LENGTH_SHORT);
    }
    public static void newText(Context c,String msg,int duration){
        if(mToast ==null){
            mToast = Toast.makeText(c,msg,duration);
        }else if(c!=context){
            mToast = new Toast(c);
        }
        mToast.setDuration(duration);
        mToast.setText(msg);
        mToast.show();
    }

}
