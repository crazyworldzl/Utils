package com.al.utils.annotation;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZhangLong on 2017/3/10.
 */

public class BindUtils {
    public static void bind(final Activity o) {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field f : declaredFields) {
            f.setAccessible(true);
            BindView anno = f.getAnnotation(BindView.class);
            if(anno==null)continue;
                try {
                    f.set(o, o.findViewById(anno.id()));
                    if(anno.click()){
                        Method[] declaredMethods = o.getClass().getDeclaredMethods();
                        for (final Method m:declaredMethods) {
                            m.setAccessible(true);
                            if(m.getName().equals(anno.method())){
                                o.findViewById(anno.id()).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        try {
                                            m.invoke(o,v);
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
