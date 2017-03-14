package com.al.utils.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.al.utils.annotation.BindUtils;

/**
 * Created by ZhangLong on 2017/3/13.
 */

public abstract class CoreFragment extends Fragment{
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindUtils.view.bindView(this,view);
    }
}
