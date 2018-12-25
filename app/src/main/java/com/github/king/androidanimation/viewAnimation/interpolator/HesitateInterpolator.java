package com.github.king.androidanimation.viewAnimation.interpolator;

import android.util.Log;
import android.view.animation.Interpolator;

/**
 * @author Created by jinxl on 2018/12/21.
 */
public class HesitateInterpolator implements Interpolator {

    public HesitateInterpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        Log.i("HesitateInterpolator", "getInterpolation -----input:" + input);
        float x = 2.0f * input - 1.0f;
        Log.i("HesitateInterpolator", "getInterpolation -----222:" + 0.5f * (x * x * x + 1.0f));
        return 0.5f * (x * x * x + 1.0f);
    }

}
