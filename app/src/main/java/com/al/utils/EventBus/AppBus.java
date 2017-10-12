package com.al.utils.EventBus;

import com.squareup.otto.Bus;

/**
 * Created by ZhangLong on 2017/8/7.
 */

public class AppBus extends Bus {
    private  static AppBus appBus;
    public static AppBus getInstance(){
        if (appBus==null){
            appBus = new AppBus();
        }
        return appBus;
    }

}
