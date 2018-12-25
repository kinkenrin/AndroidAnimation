package com.github.king.androidanimation.viewAnimation.evaluator;

import android.animation.TypeEvaluator;

/**
 * @author Created by jinxl on 2018/12/25.
 */
public class ReverseEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (endValue - fraction * (endValue - startInt));
    }
}
