package com.github.king.androidanimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TelescopeViewActivity extends AppCompatActivity {

    public static Intent initIntent(Context context) {
        return new Intent(context, TelescopeViewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telescope_view);


    }

}
