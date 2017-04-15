package com.coyoal.zsc.CycleDemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */

public class CustomAdapter extends PagerAdapter {

    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN};
    List<ImageView> views = new ArrayList<>();

    public CustomAdapter(Context context) {
        for(int i = 0; i < colors.length; i++) {
            ImageView img = new ImageView(context);
            img.setBackgroundColor(colors[i]);
            views.add(img);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
