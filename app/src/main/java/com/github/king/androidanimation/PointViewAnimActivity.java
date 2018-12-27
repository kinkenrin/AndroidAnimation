package com.github.king.androidanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.king.androidanimation.view.MyPointView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class PointViewAnimActivity extends AppCompatActivity {

    public static Intent initIntent(Context context) {
        return new Intent(context, PointViewAnimActivity.class);
    }

    private MyPointView mMyPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponit_view_anim);
        mMyPointView = findViewById(R.id.pv_point);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mMyPointView, "point", 1, 2, 3, 2, 1, 0);

        animator.start();

        PropertyValuesHolder point = PropertyValuesHolder.ofInt("backgroundColor", 1, 0);
        ObjectAnimator.ofPropertyValuesHolder(mMyPointView, point);

        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation",frame0,frame1,frame2);
        Animator animator2 = ObjectAnimator.ofPropertyValuesHolder(mMyPointView,frameHolder);
        animator2.setDuration(1000);
        animator2.start();


        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(animator2);
        builder.with(animator);
        animatorSet.start();



    }

    public void doAnim(View view) {
        mMyPointView.doPointAnim();

    }
}
