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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.al.utils.R;
import com.al.utils.View.ScaleTransformer;
import com.al.utils.View.ZoomOutPageTransformer;
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
    @BindView(id = R.id.main_rg)
    RadioGroup rg;
    @BindView(id = R.id.main_vp)
    ViewPager vp;
    List<Fragment> fragments = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindUtils.view.bindView(this);
        fragments.add(new a());
        fragments.add(new b());
        fragments.add(new c());
        fragments.add(new d());
        CoreFragmentAdapter coreFragmentAdapter = new CoreFragmentAdapter(getSupportFragmentManager()) {
        };
        vp.setAdapter(coreFragmentAdapter);
        coreFragmentAdapter.addData(fragments);
        vp.setOffscreenPageLimit(3);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position < rg.getChildCount()) {
                    RadioButton childAt = (RadioButton) rg.getChildAt(position);
                    childAt.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rg_rb1:
                        vp.setCurrentItem(0, true);
                        break;
                    case R.id.main_rg_rb2:
                        vp.setCurrentItem(1, true);
                        break;
                    case R.id.main_rg_rb3:
                        vp.setCurrentItem(2, true);
                        break;
                    case R.id.main_rg_rb4:
                        vp.setCurrentItem(3, true);
                        break;
                }
            }
        });
        ((RadioButton) rg.getChildAt(0)).setChecked(true);
        initViewPager();
    }

    public static class a extends CoreFragment {
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

    public static class b extends CoreFragment {
        @BindView(id = R.id.fragment_b_tv, clickMethod = "click", isClick = true)
        TextView tv;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_b, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            BindUtils.view.bindView(this, view);
            tv.setText("hahah");

        }

        public void click(View v) {
            ToastUtil.text("hahahahahaVVVVVVV");
        }
    }
    private void initViewPager() {
        vp.setPageMargin(0);//设置页面的间距
        vp.setOffscreenPageLimit(3);//设置缓存的页面数量
        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        vp.setPageTransformer(false, new ScaleTransformer());
//        vp.setPageTransformer(true,new ZoomOutPageTransformer());
//        vp.setPageTransformer(true,new ViewPager.PageTransformer(){
//            private float DEFAULT_ALPHA=0.6F;
//            private float mMinAlpha=DEFAULT_ALPHA;
//            private float DEFALUT_SCALE=0.75F;
//            private float mMinScale=DEFALUT_SCALE;
//            @Override
//            public void transformPage(View page, float position) {
//                if(position< -1){
//                    page.setAlpha(mMinAlpha);
//                    page.setScaleY(mMinScale);
//                    page.setScaleX(mMinScale);
//                }else if(position<=1){
//                    float alpha=0;
//                    float scale= 0;
//                    if(position<0){ //(0,-1]
//                        //页面向左滑动
//                        alpha= mMinAlpha + (1- mMinAlpha)* (1+ position);
//                        scale = mMinScale + (1-mMinAlpha) * (1 + position);
//                    }else{ //[1,0] //第二页面显示进来
//                        alpha= mMinAlpha + (1- mMinAlpha)* (1-  position);
//                        scale = mMinScale + (1-mMinAlpha) * (1 - position);
//                    }
//
//                    page.setAlpha(alpha);
//                    page.setScaleY(scale);
//                    page.setScaleX(scale);
//
//                }else{
//                    page.setAlpha(mMinAlpha);
//                    page.setScaleY(mMinScale);
//                    page.setScaleX(mMinScale);
//                }
//
//            }
//        });
//                if(event.getAction()==MotionEvent.ACTION_MOVE){
//                    swipeRefreshLayout.setEnabled(false);
//                }else if(event.getAction()==MotionEvent.ACTION_UP){
//                    swipeRefreshLayout.setEnabled(true);
//                }

    }
    public static class c extends CoreFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout linearLayout = new LinearLayout(container.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            Button button = new Button(container.getContext());
            button.setText("c");
            final MyImageView imageView = new MyImageView(container.getContext());
            imageView.setImageResource(R.mipmap.ic_launcher);
            TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(500);
            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    -1.0f);
            mHiddenAction.setDuration(500);
            imageView.initAnimation(mShowAction, mHiddenAction);
//            imageView.setAnimation(AnimationUtils.loadAnimation(container.getContext(),R.anim.shape));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageView.isShown()) {
                        imageView.setVisibility(View.GONE);
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }

                }
            });
            linearLayout.addView(imageView);
            linearLayout.addView(button);

            return linearLayout;
        }
    }

    public static class d extends CoreFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Button button = new Button(container.getContext());
            button.setText("d");
            return button;
        }
    }

}
