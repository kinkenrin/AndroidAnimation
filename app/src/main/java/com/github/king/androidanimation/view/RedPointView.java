package com.github.king.androidanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.king.androidanimation.R;

/**
 * @author Created by jinxl on 2019/1/7.
 */
public class RedPointView extends FrameLayout {

    private Paint mPaint;
    private Path mPath;
    private PointF mStartPoint;
    private PointF mCurPoint;
    private float DEFAULT_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
    private float MIN_RADIUS = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
    private float mRadius = DEFAULT_RADIUS;
    private boolean mTouch;
    private boolean isAnimStart;

    private TextView mTipTextView;
    private ImageView exploredImageView;

    public RedPointView(@NonNull Context context) {
        this(context, null);
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedPointView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mStartPoint = new PointF(100, 100);
        mCurPoint = new PointF();
        mPath = new Path();

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTipTextView = new TextView(getContext());
        mTipTextView.setLayoutParams(params);
        mTipTextView.setPadding(10, 10, 10, 10);
        mTipTextView.setBackgroundResource(R.drawable.tv_bg);
        mTipTextView.setTextColor(Color.GREEN);
        mTipTextView.setText("99+");

        exploredImageView = new ImageView(getContext());
        exploredImageView.setLayoutParams(params);
        exploredImageView.setImageResource(R.drawable.tip_anim);
        exploredImageView.setVisibility(View.INVISIBLE);

        addView(mTipTextView);
        addView(exploredImageView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 判断触摸点是否在tipImageView中
                RectF rect = new RectF();
                int[] location = new int[2];
                mTipTextView.getLocationOnScreen(location);
                rect.left = location[0];
                rect.top = location[1];
                rect.right = mTipTextView.getWidth() + location[0];
                rect.bottom = mTipTextView.getHeight() + location[1];
                if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    mTouch = true;
//                    isAnimStart = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouch = false;
                break;
            default:

                break;
        }

        mCurPoint.set(event.getX(), event.getY());
        invalidate();
//        return super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);
        if (!mTouch || isAnimStart) {
            mTipTextView.setX(mStartPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mStartPoint.y - mTipTextView.getHeight() / 2);
        } else {
            calculatePath();
            canvas.drawCircle(mStartPoint.x, mStartPoint.y, mRadius, mPaint);
            canvas.drawCircle(mCurPoint.x, mCurPoint.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);//将textview的中心放在当前手指位置
            mTipTextView.setX(mCurPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mCurPoint.y - mTipTextView.getHeight() / 2);

        }
//        canvas.restore();
        super.dispatchDraw(canvas);
    }


    private void calculatePath() {

        float x = mCurPoint.x;
        float y = mCurPoint.y;
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;
        // 根据角度算出四边形的四个点
        float dx = x - startX;
        float dy = y - startY;
        double a = Math.atan(dy / dx);
        float offsetX = (float) (mRadius * Math.sin(a));
        float offsetY = (float) (mRadius * Math.cos(a));

        float distance = (float) Math.sqrt(Math.pow(y - startY, 2) + Math.pow(x - startX, 2));
        mRadius = DEFAULT_RADIUS - distance / 15;
        if (mRadius < MIN_RADIUS) {
            isAnimStart = true;
            exploredImageView.setX(mCurPoint.x - mTipTextView.getWidth() / 2);
            exploredImageView.setY(mCurPoint.y - mTipTextView.getHeight() / 2);
            exploredImageView.setVisibility(View.VISIBLE);
            ((AnimationDrawable) exploredImageView.getDrawable()).start();

            mTipTextView.setVisibility(View.GONE);
        }

        // 根据角度算出四边形的四个点
        float x1 = startX + offsetX;
        float y1 = startY - offsetY;

        float x2 = x + offsetX;
        float y2 = y - offsetY;

        float x3 = x - offsetX;
        float y3 = y + offsetY;

        float x4 = startX - offsetX;
        float y4 = startY + offsetY;

        float anchorX = (startX + x) / 2;
        float anchorY = (startY + y) / 2;

        mPath.reset();
        mPath.moveTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x3, y3);
        mPath.quadTo(anchorX, anchorY, x4, y4);
        mPath.lineTo(x1, y1);
    }
}
