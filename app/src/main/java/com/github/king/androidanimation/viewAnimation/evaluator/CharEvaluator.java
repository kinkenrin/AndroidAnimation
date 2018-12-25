package com.github.king.androidanimation.viewAnimation.evaluator;

import android.animation.TypeEvaluator;

/**
 * @author Created by jinxl on 2018/12/25.
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
