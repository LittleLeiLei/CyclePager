package com.coyoal.zsc.cyclelibrary.cycle.adapter;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 实现循环效果适配器(View由用户业务逻辑定义,CycleAdapter只实现循环效果)
 * Created by Administrator on 2017/3/19.
 */

public class CycleAdapter extends PagerAdapter {

    private PagerAdapter mAdapter;
    private boolean loop = false;
    private final int RATIO = 100;
    private SparseArray<View> mViewArray = new SparseArray<>();

    /**
     *
     * @param adapter ViewPager适配器
     */
    public CycleAdapter(PagerAdapter adapter) {
        this.mAdapter = adapter;
    }

    //代理内部方法
    @Override
    public int getCount() {
        return loop? this.mAdapter.getCount() * RATIO : this.mAdapter.getCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realPos = loop ? position % this.mAdapter.getCount() : position;
        mAdapter.destroyItem(container, realPos, object);
        mViewArray.remove(realPos);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPos = loop ? position % this.mAdapter.getCount() : position;
        Object item = mAdapter.instantiateItem(container, realPos);
        ViewPager viewPager = (ViewPager) container;
        int childCount = viewPager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewPager.getChildAt(i);
            if (isViewFromObject(child, item)) {
                mViewArray.put(realPos, child);
                break;
            }
        }
        return item;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mAdapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int virtualPosition = position % mAdapter.getCount();
        return mAdapter.getPageTitle(virtualPosition);
    }

    @Override
    public float getPageWidth(int position) {
        return mAdapter.getPageWidth(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mAdapter.setPrimaryItem(container, position, object);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return mAdapter.getItemPosition(object);
    }

    public int getRealCount() {
        return mAdapter.getCount();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        notifyDataSetChanged();
    }

    public PagerAdapter getAdapter() {
        return mAdapter;
    }
}
