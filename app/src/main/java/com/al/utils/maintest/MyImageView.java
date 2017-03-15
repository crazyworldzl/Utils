package com.al.utils.maintest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by ZhangLong on 2017/3/15.
 */

public class MyImageView extends ImageView{
    private Animation mShowAction;
    private Animation mHiddenAction;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void initAnimation(Animation showAction,Animation hideAction){
        this.mShowAction = showAction;
        this.mHiddenAction = hideAction;
//        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//        mShowAction.setDuration(500);
//        mHiddenAction= new TranslateAnimation(Animation.RELATIVE_TO_SELF,
//                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f);
//        mHiddenAction.setDuration(500);
    }
    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility== View.VISIBLE){
            if(mShowAction!=null)
            this.startAnimation(mShowAction);
        }else if(visibility==View.INVISIBLE&&visibility==View.GONE){
            if(mHiddenAction!=null)
            this.startAnimation(mHiddenAction);
        }
    }
}
