package com.github.king.androidanimation.viewAnimation.evaluator;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * @author Created by jinxl on 2018/12/25.
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        Log.i("aaaa", "evaluate:" + fraction);
        int startint = startValue;
        return (int) (200 + startValue + fraction * (endValue - startValue));
    }
}
