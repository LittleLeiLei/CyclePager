package com.coyoal.zsc.cyclelibrary.cycle.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import com.coyoal.zsc.cyclelibrary.cycle.IndicatorDot;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 * Created by Administrator on 2017/3/19.
 */

public class Utils {
    public static List<String> convert2CycleList(@NonNull List<String> items) {
        try {
            if (items.size() == 0)
                throw new Exception("convert2CycleList arguments can not be empty");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> result = new ArrayList<>();
        result.add(items.get(items.size() - 1));
        result.addAll(items);
        result.add(items.get(0));

        return result;
    }

    public static List<ImageView> convert2ImageList(Context context, @NonNull List<String> items) {
        String[] colors = {"#00ffff", "#ff0000", "#00ff00", "#0000ff", "#00ffff", "#ff0000"};
        try {
            if (items.size() == 0)
                throw new Exception("convert2ImageList arguments can not be empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ImageView> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ImageView iv = new ImageView(context);
            iv.setBackgroundColor(Color.parseColor(colors[i]));
            result.add(iv);
        }
        return result;
    }

    public static List<IndicatorDot> convert2Dot(Context context, @NonNull int count) {
        List<IndicatorDot> indicators = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IndicatorDot dot = new IndicatorDot(context);
            indicators.add(dot);
        }
        return indicators;
    }

    public static int dip2px(Context context, float dp) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
    }
}
