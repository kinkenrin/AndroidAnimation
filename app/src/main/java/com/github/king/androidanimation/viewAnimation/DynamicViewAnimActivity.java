package com.github.king.androidanimation.viewAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.github.king.androidanimation.R;
import com.github.king.androidanimation.viewAnimation.evaluator.CharEvaluator;
import com.github.king.androidanimation.viewAnimation.evaluator.MyEvaluator;
import com.github.king.androidanimation.viewAnimation.evaluator.ReverseEvaluator;
import com.github.king.androidanimation.viewAnimation.interpolator.HesitateInterpolator;

public class DynamicViewAnimActivity extends AppCompatActivity implements View.OnClickListener {

    public static Intent initIntent(Context context) {
        Intent intent = new Intent(context, DynamicViewAnimActivity.class);
        return intent;
    }

    private TextView mTv_animation;
    private Button mBtn_char;

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
        mBtn_char = findViewById(R.id.btn_char);
        mBtn_char.setOnClickListener(this);
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

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                Log.i("aaa", "animatedValue:" + animatedValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

            }
        });
        valueAnimator.setInterpolator(new HesitateInterpolator());
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setRepeatCount(100);
        valueAnimator.setEvaluator(new MyEvaluator());
//        valueAnimator.setEvaluator(new ReverseEvaluator());
        valueAnimator.start();

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

    public void color(View view) {
        ValueAnimator animator = ValueAnimator.ofInt(0xffff0000, 0xffffff00);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                mTv_animation.setBackgroundColor(curValue);

            }
        });

        animator.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_char:
                charAnim();
                break;
        }
    }

    private void charAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char curValue = (char) animation.getAnimatedValue();
                mTv_animation.setText(String.valueOf(curValue));

            }
        });
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(3000);
        animator.start();
    }
}
