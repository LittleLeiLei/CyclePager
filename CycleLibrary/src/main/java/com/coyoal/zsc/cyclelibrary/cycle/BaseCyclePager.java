package com.coyoal.zsc.cyclelibrary.cycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.coyoal.zsc.cyclelibrary.R;
import com.coyoal.zsc.cyclelibrary.cycle.listeners.OnCyclePagerChangedListener;
import com.coyoal.zsc.cyclelibrary.cycle.utils.CyclicHelper;


/**
 *
 * Created by Administrator on 2017/3/21.
 */

public abstract class BaseCyclePager extends RelativeLayout {

    private int lastPos = -1;
    private boolean cyclic = false;
    private boolean showIndicator = true;

    public BaseCyclePager(Context context) {
        this(context, null);
    }

    public BaseCyclePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CyclePager);
            cyclic = ta.getBoolean(R.styleable.CyclePager_cyclic, false);
            showIndicator = ta.getBoolean(R.styleable.CyclePager_showIndicator, true);
            ta.recycle();
        }
        init();
    }

    private void init() {
        final ViewPager mPager = injectViewPager();
        View mIndicator = injectIndicator();

        addView(mPager);
        if(showIndicator) addView(mIndicator);

        final CyclicHelper mHelper = new CyclicHelper(mPager);
        mPager.addOnPageChangeListener(new OnCyclePagerChangedListener() {
            @Override
            public void onPageSelected(int position, int scrollOrientation) {
                if(lastPos == position) return;

                if(cyclic) {
                    //处理循环的逻辑
                    mHelper.onPageSelected(position, scrollOrientation);
                    //将循环的position对应到Indicator上
                    if(position >= 1 && position <= mPager.getAdapter().getCount() - 2) onPageActive(position - 1);
                } else {
                    onPageActive(position);
                }
                lastPos = position;
            }
        });

    }

    protected boolean isCyclic() {
        return cyclic;
    }

    protected boolean isShowIndicator() {
        return isShowIndicator();
    }

    public abstract ViewPager injectViewPager();

    public abstract View injectIndicator();

    public abstract void onPageActive(int position);
}
