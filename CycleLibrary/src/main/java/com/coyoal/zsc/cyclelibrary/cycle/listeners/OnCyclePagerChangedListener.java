package com.coyoal.zsc.cyclelibrary.cycle.listeners;

import android.support.v4.view.ViewPager;

/**
 * 用于监听滑动方向
 * Created by Administrator on 2017/3/19.
 */

public abstract class OnCyclePagerChangedListener implements ViewPager.OnPageChangeListener {

    private float lastOffset = 0;
    private int currentOrientation = -1;
    private int currentPos = -1;
    private int currentState = -1;
    public static final int ORIENTATION_LEFT = 0;
    public static final int ORIENTATION_RIGHT = 1;

    public abstract void onPageSelected(int position, int scrollOrientation);

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0) return;

        if (positionOffset > lastOffset) {
            currentOrientation = ORIENTATION_RIGHT;
        } else {
            currentOrientation = ORIENTATION_LEFT;
        }
        lastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        currentPos = position;
        if (currentState == ViewPager.SCROLL_STATE_IDLE) {
            onPageSelected(currentPos, currentOrientation);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        this.currentState = state;
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            onPageSelected(currentPos, currentOrientation);
        }
    }
}
