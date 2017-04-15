package com.coyoal.zsc.cyclelibrary.cycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.coyoal.zsc.cyclelibrary.R;
import com.coyoal.zsc.cyclelibrary.cycle.adapter.CycleAdapter;

/**
 * 轮播ViewPager
 * Created by Administrator on 2017/3/19.
 */

public class CyclePager extends RelativeLayout implements ICycle {
    private boolean loop = false;
    private CycleViewPager mViewPager;
    private Indicator indicator;

    public CyclePager(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.cycle_pager, this);
        initView();
        bindIndicator();
    }

    public CyclePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.cycle_pager, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CyclePager);
        loop = ta.getBoolean(R.styleable.CyclePager_cyclic, false);
        ta.recycle();
        initView();
        bindIndicator();
    }

    private void initView() {
        mViewPager = (CycleViewPager) findViewById(R.id.view_pager);
        indicator = (Indicator) findViewById(R.id.indicator);
    }

    /**
     * 绑定ViewPager与Indicator
     */
    private void bindIndicator() {
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentItem(position % mViewPager.getCycleAdapter().getRealCount());
            }
        });
    }

    @Override
    public void setLoop(boolean isLoop) {
        this.loop = true;
        mViewPager.setLoop(true);
    }

    @Override
    public boolean isLoop() {
        return loop;
    }

    @Override
    public void setIndicator(int mode) {
        // TODO: 2017/4/16 待实现Indicator效果 
    }

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setLoop(loop);
        mViewPager.setAdapter(adapter);
        indicator.setIndicatorCount(adapter.getCount());
    }

    public  PagerAdapter getAdapter() {
        return ((CycleAdapter) mViewPager.getAdapter()).getAdapter();
    }
}
