package com.github.king.androidanimation.viewAnimation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.github.king.androidanimation.R;

public class ViewAnimationActivity extends AppCompatActivity {

    private TextView mTv_animation;

    public static Intent initIntent(Context context) {
        Intent intent = new Intent(context, ViewAnimationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        mTv_animation = findViewById(R.id.tv_animation);
    }

    public void alpha(View view) {
        Animation alphaAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        mTv_animation.startAnimation(alphaAnim);
    }

    public void scale(View view) {
        Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        mTv_animation.startAnimation(scaleAnim);
    }

    public void rotate(View view) {
        Animation rotateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mTv_animation.startAnimation(rotateAnim);
    }

    public void trans(View view) {
        Animation rotateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.trans);
        mTv_animation.startAnimation(rotateAnim);
    }

    public void set(View view) {
        Animation setAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set);
        mTv_animation.startAnimation(setAnim);
    }
}
