package com.al.utils.annotation;

import android.support.annotation.IdRes;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZhangLong on 2017/3/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface BindView {
    /**
     *
     * @return @IdRes
     */
    @IdRes int id();

    /**
     *
     * @return 点击事件方法名
     */
    String funcName() default "onClick";

    /**
     *
     * @return 是否允许点击
     */
    boolean click() default false;

}
