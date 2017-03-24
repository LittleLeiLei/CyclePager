package com.coyoal.zsc.cyclelibrary.cycle;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 指示器
 * Created by Administrator on 2017/3/20.
 */

public class Indicator extends LinearLayout {

    private List<IndicatorDot> mDots = new ArrayList<>();
    private int lastPos = 0;

    public Indicator(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
    }

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    public void setIndicatorCount(int count) {
        removeAllViews();
        for (int i = 0; i < count; i++) {
            IndicatorDot dot = new IndicatorDot(getContext());
            mDots.add(dot);
            addView(dot);
        }
        mDots.get(0).full();
    }

    /**
     * 设置当前选中项
     *
     * @param pos 选中项的索引值
     */
    public void setCurrentItem(int pos) {
        if (lastPos == pos) return;
        mDots.get(lastPos).empty();
        mDots.get(pos).full();
        lastPos = pos;
    }

    public int getCurrentItem() {
        return lastPos;
    }
}
