package com.al.utils.core;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ZhangLong on 2017/3/13.
 */

public abstract class CoreAdapter<T> extends BaseAdapter {
    private List<T> dataList;
    private int pagenumber = 1;
    private int pagesize = 10;
    private int pagetotal;

    @Override
    public int getCount() {
        if (dataList == null)
            return 0;
        else
            return dataList.size();
    }

    private void addData(List<T> list){
        this.dataList = list;
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPagetotal(int pagetotal){
        this.pagetotal = pagetotal;
    }
    public void setPagesize(int pagesize){
        this.pagesize = pagesize;
    }
    public void Refresh() {
        pagenumber = 1;
    }

    public void load() {
        if(pagenumber*pagesize< pagetotal){
           loadData(++pagenumber);
        }else{
            loadData(-1);
        }
    }
    public abstract void loadData(int pagenumber);

}
