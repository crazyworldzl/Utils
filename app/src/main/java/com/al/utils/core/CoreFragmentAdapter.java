package com.al.utils.core;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ZhangLong on 2017/3/14.
 */

public abstract class CoreFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> dataList;

    public CoreFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addData(List<Fragment> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }
}
