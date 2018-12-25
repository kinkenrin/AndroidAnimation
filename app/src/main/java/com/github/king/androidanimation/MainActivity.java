package com.github.king.androidanimation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.king.androidanimation.viewAnimation.DynamicViewAnimActivity;
import com.github.king.androidanimation.viewAnimation.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void viewAnimation(View view) {
        startActivity(ViewAnimationActivity.initIntent(getApplicationContext()));

    }

    public void dynamicViewAnim(View view) {
        startActivity(DynamicViewAnimActivity.initIntent(getApplicationContext()));

    }

    public void pointView(View view) {
        startActivity(PointViewAnimActivity.initIntent(getApplicationContext()));
    }
}
