package com.coyoal.zsc.cyclelibrary.cycle;

import android.support.v4.view.PagerAdapter;

/**
 * Created by Administrator on 2017/4/14.
 */

public interface ICycle {
    /**
     * set ViewPager infinite
     * @param isLoop
     */
    void setLoop(boolean isLoop);

    /**
     * @return true: loop
     */
    boolean isLoop();

    /**
     * set Indicator style
     * @param mode
     */
    void setIndicator(int mode);
}
