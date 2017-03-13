package com.al.utils.maintest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.al.utils.R;
import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindView;
import com.al.utils.okhttp.OkHttpUtils;
import com.al.utils.okhttp.callback.Callback;
import com.al.utils.other.LogUtil;
import com.al.utils.other.ToastUtil;

import okhttp3.Call;
import okhttp3.Response;

public class MainTest extends AppCompatActivity {
    Handler handler = new Handler();
    @BindView(id = R.id.tv, click = true)
    TextView tv;
    @BindView(id = R.id.tv2, click = true, method = "hahah")
    ImageButton tv2;
    @BindView(id = R.id.tv3, click = true, method = "hahah")
    TextView tv3;
    @BindView(id = R.id.tv4, click = true, method = "hahah")
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        BindUtils.bind(this);
        LogUtil.d("hahahahahahahahah");
        tv.setText("hhhhhhhhjiji");
        tv3.setText("3333");
        tv4.setText("44444");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                ToastUtil.text("11111111");
                break;
        }
    }

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

        }
        if (v.getId() == R.id.tv3) {
            ToastUtil.text("333333333333");
        }
        if (v.getId() == R.id.tv4) {
            ToastUtil.text("444444444");
        }
    }
}
