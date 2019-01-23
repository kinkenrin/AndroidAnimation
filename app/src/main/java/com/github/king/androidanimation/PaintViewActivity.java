package com.github.king.androidanimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.king.androidanimation.view.PaintView;

import io.reactivex.Observable;

public class PaintViewActivity extends AppCompatActivity {

    private PaintView mPv_view;

    public static Intent initIntent(Context context) {
        return new Intent(context, PaintViewActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_view);
        mPv_view = findViewById(R.id.pv_view);
        Observable.just("ddd").onTerminateDetach();

    }

    public void resetClick(View view) {
        mPv_view.reset();

    }

}
