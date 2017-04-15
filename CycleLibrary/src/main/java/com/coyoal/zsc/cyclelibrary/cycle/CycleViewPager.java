package com.coyoal.zsc.cyclelibrary.cycle;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.coyoal.zsc.cyclelibrary.cycle.adapter.CycleAdapter;

/**
 * 自定义
 * Created by Administrator on 2017/4/15.
 */

public class CycleViewPager extends ViewPager {
    private CycleAdapter mAdapter;

    public CycleViewPager(Context context) {
        super(context);
    }

    public CycleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CycleAdapter getCycleAdapter() {
        return (CycleAdapter) getAdapter();
    }

    public void setLoop(boolean loop) {
        if (mAdapter != null) {
            mAdapter.setLoop(loop);
            setCurrentItem(loop ? mAdapter.getCount() / 2 : 0);
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (adapter != null) {
            mAdapter = new CycleAdapter(adapter);
            super.setAdapter(mAdapter);
        }
    }
}
