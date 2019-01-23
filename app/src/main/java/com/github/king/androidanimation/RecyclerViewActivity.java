package com.github.king.androidanimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RecyclerViewActivity extends AppCompatActivity {

    public static Intent initIntent(Context context) {
        return new Intent(context, RecyclerViewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


    }

}
