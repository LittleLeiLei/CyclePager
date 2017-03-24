package com.coyoal.zsc.CycleDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coyoal.zsc.cyclelibrary.cycle.CyclePager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CyclePager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
    }

    private void initPager() {
        pager = (CyclePager) findViewById(R.id.pager);
        List<String> data = new ArrayList<>();
        //没有添加图片加载框架, 只设置轮播图的背景色
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        pager.setData(data);
    }
}
