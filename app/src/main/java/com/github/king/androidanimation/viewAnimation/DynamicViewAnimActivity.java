package com.github.king.androidanimation.viewAnimation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.github.king.androidanimation.R;

public class DynamicViewAnimActivity extends AppCompatActivity {

    public static Intent initIntent(Context context) {
        Intent intent = new Intent(context, DynamicViewAnimActivity.class);
        return intent;
    }

    private TextView mTv_animation;

    ScaleAnimation scaleAnim;
    AlphaAnimation alphaAnim;
    RotateAnimation rotateAnim;
    TranslateAnimation translateAnim;
    AnimationSet setAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mTv_animation = findViewById(R.id.tv_animation);
        scaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(700);

        alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        alphaAnim.setDuration(3000);
        alphaAnim.setFillBefore(true);

        rotateAnim = new RotateAnimation(0, -650, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3000);
        rotateAnim.setFillAfter(true);

        translateAnim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -80);
        translateAnim.setDuration(2000);
        translateAnim.setFillBefore(true);
        translateAnim.setInterpolator(new BounceInterpolator());

        //shareInterpolator取值true或false，
        // 取true时，指在AnimationSet中定义一个插值器（interpolater）
        // ，它下面的所有动画共同。如果设为false，则表示它下面的动画自己定义各自的插值器。
        setAnim = new AnimationSet(false);
        setAnim.addAnimation(scaleAnim);
        setAnim.addAnimation(alphaAnim);
        setAnim.addAnimation(rotateAnim);
        setAnim.addAnimation(translateAnim);

    }

    public void alpha(View view) {
        mTv_animation.startAnimation(alphaAnim);
    }

    public void scale(View view) {
        mTv_animation.startAnimation(scaleAnim);
    }

    public void rotate(View view) {
        mTv_animation.startAnimation(rotateAnim);
    }

    public void trans(View view) {
        mTv_animation.startAnimation(translateAnim);
    }

    public void set(View view) {

        mTv_animation.startAnimation(setAnim);
    }
}
