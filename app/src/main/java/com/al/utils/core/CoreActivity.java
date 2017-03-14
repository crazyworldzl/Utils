package com.al.utils.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

import com.al.utils.main.AlUtils;
import com.al.utils.other.LogUtil;

/**
 * Created by ZhangLong on 2017/3/13.
 */
public abstract class CoreActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlUtils.al.getActivityMange().pushActivity(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            AlUtils.al.getActivityMange().popActivity().finish();
            LogUtil.d("coreactivity KEYCODE_BACK");
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}
