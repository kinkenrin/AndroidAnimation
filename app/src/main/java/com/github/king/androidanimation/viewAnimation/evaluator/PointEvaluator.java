package com.github.king.androidanimation.viewAnimation.evaluator;

import android.animation.TypeEvaluator;

import com.github.king.androidanimation.bean.PointInfo;

/**
 * @author Created by jinxl on 2018/12/25.
 */
public class PointEvaluator implements TypeEvaluator<PointInfo> {
    @Override
    public PointInfo evaluate(float fraction, PointInfo startValue, PointInfo endValue) {
        int start = startValue.getRadius();
        int end = endValue.getRadius();
        int curValue = (int) (start + fraction * (end - start));
        return new PointInfo(curValue);
    }
}
