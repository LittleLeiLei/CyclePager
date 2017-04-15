package com.coyoal.zsc.CycleDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coyoal.zsc.cyclelibrary.cycle.CyclePager;
import com.coyoal.zsc.cyclelibrary.cycle.adapter.CycleAdapter;

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
        CustomAdapter adapter = new CustomAdapter(this);
        pager.setAdapter(adapter);
        pager.setLoop(true);
    }
}
