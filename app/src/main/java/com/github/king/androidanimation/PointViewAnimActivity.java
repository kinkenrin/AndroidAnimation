package com.github.king.androidanimation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.king.androidanimation.view.MyPointView;

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
    }


    public void doAnim(View view) {
        mMyPointView.doPointAnim();

    }
}
