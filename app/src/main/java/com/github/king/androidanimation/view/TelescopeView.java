package com.github.king.androidanimation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.king.androidanimation.R;

/**
 * @author Created by jinxl on 2019/1/8.
 */
public class TelescopeView extends View {
    public TelescopeView(Context context) {
        this(context, null);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private Paint mPaint;
    private Bitmap mBitmap, mBitmapBG;
    private int mDx = -1, mDy = -1;

    private void initView() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmapBG == null) {
//            mBitmapBG = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvasbg = new Canvas(mBitmapBG);
//            canvasbg.drawBitmap(mBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
            mBitmapBG = Bitmap.createScaledBitmap(mBitmap, getWidth(), getHeight(), true);
        }

        if (mDx != -1 && mDy != -1) {
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx, mDy, 150, mPaint);
        }
    }
}
