package com.al.utils.maintest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by allen on 2017/3/21.
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, i);

    }
}
