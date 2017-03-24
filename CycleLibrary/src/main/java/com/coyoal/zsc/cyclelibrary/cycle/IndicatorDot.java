package com.coyoal.zsc.cyclelibrary.cycle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.coyoal.zsc.cyclelibrary.cycle.utils.Utils;

/**
 * 指示器小圆点
 * Created by Administrator on 2017/3/20.
 */

public class IndicatorDot extends View {

    private Paint mPaint;
    private int radius = 0;
    private int width = 0;
    private int height = 0;
    private int ringWidth = 4;
    private Path path = new Path();
    private final int DURATION = 500;

    private ValueAnimator fullAnim;

    public IndicatorDot(Context context) {
        super(context);
        init();
    }

    public IndicatorDot(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    private void init() {
        radius = Utils.dip2px(getContext(), 5);
        width = 3 * radius;
        height = 3 * radius;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);

        fullAnim = ValueAnimator.ofInt(1, 0);
        fullAnim.setDuration(DURATION);
        fullAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ringWidth = (int) (radius * animation.getAnimatedFraction());
                ringWidth = Math.max(ringWidth, 4);
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.addCircle(width/2, height/2, radius - ringWidth, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.XOR);
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
    }

    public void full() {
        fullAnim.setInterpolator(new OvershootInterpolator());
        fullAnim.start();
    }

    public void empty() {
        //反转动画
        fullAnim.setInterpolator(new ReverseInterpolator());
        fullAnim.start();
    }

    class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return 1 - input;
        }
    }
}
