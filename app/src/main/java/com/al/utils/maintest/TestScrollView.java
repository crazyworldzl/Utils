package com.al.utils.maintest;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.al.utils.R;
import com.al.utils.annotation.BindUtils;
import com.al.utils.annotation.BindView;
import com.al.utils.core.CoreActivity;
import com.al.utils.main.AlUtils;
import com.al.utils.okhttp.OkHttpUtils;
import com.al.utils.okhttp.callback.Callback;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by allen on 2017/3/21.
 */

public class TestScrollView extends CoreActivity {
    @BindView(id=R.id.testscrollview_lv)
    ListView lv;
    @BindView(id=R.id.testscrollview_sv)
    ScrollView sv;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testscrollview);
        BindUtils.view.bindView(this);


        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add(i+";;");
        }
       arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strings);
        lv.setAdapter(arrayAdapter);
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(motionEvent.getAction()==MotionEvent.ACTION_SCROLL) {
                    sv.requestDisallowInterceptTouchEvent(true);
//                }
                return false;
            }
        });
        dowLoaddata();
    }
    public void dowLoaddata(){
        OkHttpUtils.get().url("http://192.168.1.105:8080/AllenWebSet/a").build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                JSONArray jsonArray = new JSONArray(response.body().string());
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    PersonModel personModel = new Gson().fromJson(jsonArray.getString(i), PersonModel.class);
                    strings.add("name="+personModel.getName()+"  age="+personModel.getAge()+"  id="+personModel.getId());
                }
                if (strings.size()!=0){
                    arrayAdapter.addAll(strings);
                }
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
}
