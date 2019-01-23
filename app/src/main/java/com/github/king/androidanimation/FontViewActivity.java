package com.github.king.androidanimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FontViewActivity extends AppCompatActivity {

    public static Intent initIntent(Context context) {
        return new Intent(context, FontViewActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_view);
        //22222222222222

    }

}
