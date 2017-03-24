package com.coyoal.zsc.cyclelibrary.cycle.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coyoal.zsc.cyclelibrary.cycle.utils.Utils;

import java.util.List;

/**
 * 适配器
 * Created by Administrator on 2017/3/19.
 */

public class CycleAdapter extends PagerAdapter {

    private List<String> mRealItems;
    private List<String> mCycleItems;
    private List<ImageView> mViewItems;
    private boolean cyclic = false;

    public CycleAdapter(Context context, List<String> items, boolean cyclic) {
        this.cyclic = cyclic;
        this.mRealItems = items;
        this.mCycleItems = this.cyclic? Utils.convert2CycleList(mRealItems) : mRealItems;
        this.mViewItems = Utils.convert2ImageList(context, mCycleItems);
    }

    @Override
    public int getCount() {
        return mCycleItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewItems.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewItems.get(position));
        return mViewItems.get(position);
    }
}
