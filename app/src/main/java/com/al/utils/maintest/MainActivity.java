package com.al.utils.maintest;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.al.utils.R;
import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindView;
import com.al.utils.core.CoreActivity;
import com.al.utils.core.CoreFragment;
import com.al.utils.core.CoreFragmentAdapter;
import com.al.utils.other.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangLong on 2017/3/14.
 */

public class MainActivity extends CoreActivity {
    @BindView(id=R.id.main_rg)
    RadioGroup rg;
    @BindView(id=R.id.main_vp)
    ViewPager vp;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUtils.view.bindView(this);
        List<Fragment>fragments = new ArrayList<>();
        fragments.add(new a());
        fragments.add(new b());
        fragments.add(new c());
        fragments.add(new d());
        CoreFragmentAdapter coreFragmentAdapter = new CoreFragmentAdapter(getSupportFragmentManager()){};
        vp.setAdapter(coreFragmentAdapter);
        coreFragmentAdapter.addData(fragments);
       vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               RadioButton childAt = (RadioButton) rg.getChildAt(position);
               childAt.setChecked(true);
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rg_rb1:
                        vp.setCurrentItem(0,true);
                        break;
                    case R.id.main_rg_rb2:
                        vp.setCurrentItem(1,true);
                        break;
                    case R.id.main_rg_rb3:
                        vp.setCurrentItem(2,true);
                        break;
                    case R.id.main_rg_rb4:
                        vp.setCurrentItem(3,true);
                        break;
                }
            }
        });
        ((RadioButton)rg.getChildAt(0)).setChecked(true);
    }
    public static class a extends CoreFragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button button = new Button(container.getContext());
            button.setText("a");
            return button;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

        }
    }
    public static class b extends CoreFragment{
        @BindView(id = R.id.fragment_b_tv,clickMethod = "click",isClick = true)
        TextView tv;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_b,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            BindUtils.view.bindView(this,view);
            tv.setText("hahah");

        }
        public void click(View v){
            ToastUtil.text("hahahahahaVVVVVVV");
        }
    }

    public static class c extends CoreFragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button button = new Button(container.getContext());
            button.setText("c");
            return button;
        }
    }

    public static class d extends CoreFragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button button = new Button(container.getContext());
            button.setText("d");
            return button;
        }
    }
}
