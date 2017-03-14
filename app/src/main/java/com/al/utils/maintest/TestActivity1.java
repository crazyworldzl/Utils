package com.al.utils.maintest;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.al.utils.core.CoreActivity;
import com.al.utils.main.AlUtils;
import com.al.utils.other.LogUtil;
import com.al.utils.other.ToastUtil;

/**
 * Created by ZhangLong on 2017/3/13.
 */

public class TestActivity1 extends CoreActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.text("TestActivity1");
            }
        });
        Button button1 = new Button(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlUtils.al.getActivityMange().AppExit();
            }
        });
        Button button2 = new Button(this);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity1.this,TestWebView.class));
            }
        });
        button1.setText("exit");
        button.setText("click");
        EditText editText = new EditText(this);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                    LogUtil.d("EditText KEYCODE_BACK=="+keyCode+"==="+event.getAction());
                return false;
            }
        });
        linearLayout.addView(button);
        linearLayout.addView(button1);
        linearLayout.addView(button2);
        linearLayout.addView(editText);
        setTitle("TestActivity1");
        setContentView(linearLayout);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
