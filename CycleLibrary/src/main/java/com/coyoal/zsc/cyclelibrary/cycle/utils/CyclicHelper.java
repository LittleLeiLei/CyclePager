package com.coyoal.zsc.cyclelibrary.cycle.utils;

import android.support.v4.view.ViewPager;

import com.coyoal.zsc.cyclelibrary.cycle.listeners.OnCyclePagerChangedListener;


/**
 * CycleHelper处理循环逻辑
 * Created by Administrator on 2017/3/19.
 */

public class CyclicHelper extends OnCyclePagerChangedListener {
    private ViewPager mPager;

    public CyclicHelper(ViewPager pager) {
        this.mPager = pager;
    }

    @Override
    public void onPageSelected(int position, int scrollOrientation) {
        switch (scrollOrientation) {
            case OnCyclePagerChangedListener.ORIENTATION_LEFT:
                if (position == 0)
                    mPager.setCurrentItem(mPager.getAdapter().getCount() - 2, false);
                break;
            case OnCyclePagerChangedListener.ORIENTATION_RIGHT:
                if (position == mPager.getAdapter().getCount() - 1)
                    mPager.setCurrentItem(1, false);
                break;
        }
    }
}
