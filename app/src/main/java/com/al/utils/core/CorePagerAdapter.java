package com.al.utils.core;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZhangLong on 2017/3/14.
 */

public  class CorePagerAdapter extends PagerAdapter {

    private List<View> dataList;

    public  void setData(List<View> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(dataList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(dataList.get(position), 0);
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
