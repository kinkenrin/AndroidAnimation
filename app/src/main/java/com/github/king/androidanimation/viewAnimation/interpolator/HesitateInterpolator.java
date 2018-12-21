package com.github.king.androidanimation.viewAnimation.interpolator;

import android.view.animation.Interpolator;

/**
 * @author Created by jinxl on 2018/12/21.
 */
public class HesitateInterpolator implements Interpolator {

    public HesitateInterpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        float x = 2.0f * input - 1.0f;
        return 0.5f * (x * x * x + 1.0f);
    }

}
