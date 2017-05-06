package com.al.utils.maintest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.al.utils.R;
import com.al.utils.View.PopupMenu;
import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindView;
import com.al.utils.bean.MenuBean;
import com.al.utils.core.CoreActivity;
import com.al.utils.main.AlUtils;
import com.al.utils.okhttp.OkHttpUtils;
import com.al.utils.okhttp.callback.Callback;
import com.al.utils.other.ToastUtil;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class MainTest extends CoreActivity {
    Handler handler = new Handler();
    @BindView(id = R.id.tv, isClick = true)
    TextView tv;
    @BindView(id = R.id.tv2, isClick = true, clickMethod = "hahah")
    ImageButton tv2;
    @BindView(id = R.id.tv3, isClick = true, clickMethod = "hahah")
    TextView tv3;
    @BindView(id = R.id.tv4, isClick = true, clickMethod = "hahah")
    TextView tv4;
    @BindView(id = R.id.tv5, isClick = true, clickMethod = "hahah")
    TextView tv5;
    @BindView(id = R.id.tv6, isClick = true, clickMethod = "hahah")
    TextView tv6;
    @BindView(id = R.id.tv7, isClick = true, clickMethod = "hahah")
    TextView tv7;
    @BindView(id = R.id.ctv, isClick = true, clickMethod = "hahah")
    CheckedTextView ctv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        BindUtils.view.bindView(this);
        tv.setText("hhhhhhhhjiji");
        tv3.setText("3333");
        tv4.setText("44444");
        setTitle("hahahah");
        tv5.setText("去像样点的界面");
        tv7.setText("testScrollView");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                ToastUtil.text("11111111");
                OkHttpUtils.get().url("http://op.juhe.cn/onebox/weather/query").addParams("cityname", "杭州").addParams("key", "08da3765803e802fbe5a1693b88de9d3").build().execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(final Response response, int id) throws Exception {
                        final String s = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(s);
                            }
                        });
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {

                    }
                });
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void hahah(View v) {
        if (v.getId() == R.id.tv2) {
            ToastUtil.newText("2222222222");
            OkHttpUtils.get().id(111).url("https://www.baidu.com/img/bd_logo1.png").build().execute(new Callback() {
                @Override
                public Object parseNetworkResponse(Response response, int id) throws Exception {
                    byte[] bytes = response.body().bytes();
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setImageBitmap(bitmap);
                        }
                    });
                    return null;
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                }

                @Override
                public void onResponse(Object response, int id) {
                }
            });
        } else if (v.getId() == R.id.tv3) {
            ToastUtil.text("333333333333");
            startActivity(new Intent(this, TestActivity1.class));
        } else if (v.getId() == R.id.tv4) {
            ToastUtil.text("444444444");
            AlUtils.al.getActivityMange().exit();
        } else if (v.getId() == R.id.tv5) {
            startActivity(new Intent(this, MainActivity.class));
        }else if(v.getId()==R.id.tv7){
            startActivity(new Intent(this, TestScrollView.class));
        } else if(v==ctv){
           if(ctv.isChecked()){
               new AlertDialog.Builder(v.getContext()).setMessage("是否关闭？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       ctv.setChecked(false);
                   }
               }).setNegativeButton("否",null).create().show();
           }else{
               new AlertDialog.Builder(v.getContext()).setMessage("是否打开？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       ctv.setChecked(true);
                   }
               }).setNegativeButton("否",null).create().show();
           }
        } else if (v.getId() == R.id.tv6) {
            ArrayList<MenuBean> objects = new ArrayList<>();
            MenuBean menuBean = new MenuBean(R.mipmap.ic_launcher, "haha", "haha");
            objects.add(menuBean);
            for (int i = 0; i < 27; i++) {
                objects.add(new MenuBean(R.mipmap.ic_launcher,"hehe","="+i));
            }
            PopupMenu popupMenu = new PopupMenu(MainTest.this, objects);
            popupMenu.setItemLinstener(new PopupMenu.ItemClickLinstener() {
                @Override
                public void click(MenuBean menu) {
                    ToastUtil.text(menu.getMenuName());
                }
            });
            popupMenu.setBackgroundDrawable(new BitmapDrawable());
            popupMenu.setLineNumber(3).setVpBackgroundResource(R.drawable.background_shape).setBackgroundColor(0xff00ff00);
            popupMenu.setDecreaseHeight(200);
            popupMenu.setShowDots(true);
            popupMenu.setVpTopPadding(30);
            popupMenu.setVpBottomPadding(30);
            popupMenu.setVpRightPadding(30);
            popupMenu.setVpLeftPadding(30);
            popupMenu.setDotSize(30);
            popupMenu.setDotBackgroundResource(R.drawable.rb_selector);
//            popupMenu.setDecreaseWidth(400);
//            popupMenu.setScrollViewPadding(50);
//            popupMenu.setVpPadding(100);
            popupMenu.setOutsideTouchDissmiss(true);
            popupMenu.setOutsideTouchable(true);
            popupMenu.setFocusable(true);
            popupMenu.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,-1,-1);
        }
    }
}
