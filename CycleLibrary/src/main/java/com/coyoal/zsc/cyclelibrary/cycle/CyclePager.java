package com.coyoal.zsc.cyclelibrary.cycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.coyoal.zsc.cyclelibrary.cycle.adapter.CycleAdapter;

import java.util.List;

/**
 * 轮播ViewPager
 * Created by Administrator on 2017/3/19.
 */

public class CyclePager extends BaseCyclePager {

    private ViewPager mPager;
    private Indicator mIndicator;

    public CyclePager(Context context) {
        this(context, null);
    }

    public CyclePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public ViewPager injectViewPager() {
        mPager = new ViewPager(getContext());
        ViewPager.LayoutParams params = new ViewPager.LayoutParams();
        mPager.setLayoutParams(params);
        return mPager;
    }

    @Override
    public Indicator injectIndicator() {
        mIndicator = new Indicator(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mIndicator.setLayoutParams(params);
        return mIndicator;
    }

    @Override
    public void onPageActive(int position) {
        mIndicator.setCurrentItem(position);
    }

    /**
     * 设置轮播图链接
     * @param data
     */
    public void setData(@NonNull List<String> data) {
        try {
            if (data.size() <= 1) throw new Exception("length must be bigger than 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        CycleAdapter adapter = new CycleAdapter(getContext(), data, isCyclic());
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(isCyclic() ? 1 : 0);
        mIndicator.setIndicatorCount(data.size());
    }
}
