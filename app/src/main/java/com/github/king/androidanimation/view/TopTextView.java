package com.github.king.androidanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 给定左上顶点写字
 *
 * @author Created by jinxl on 2019/1/2.
 */
public class TopTextView extends View {

    private Paint mPaint;

    public TopTextView(Context context) {
        this(context, null);
    }

    public TopTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    final int mTopY = 300;
    final int mTopX = 30;

    String mContent = "harvic\'s blog你好";

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics()));
        mPaint.setTextAlign(Paint.Align.LEFT);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        ascent: 系统建议的，绘制单个字符时，字符应当的最高高度所在线
//        descent:系统建议的，绘制单个字符时，字符应当的最低高度所在线
//        top: 可绘制的最高高度所在线
//        bottom: 可绘制的最低高度所在线
        //计算各线所在位置
//        Paint.FontMetrics fm = mPaint.getFontMetrics();
//        float ascent = baseLineY + fm.ascent;
//        float descent = baseLineY + fm.descent;
//        float top = baseLineY + fm.top;
//        float bottom = baseLineY + fm.bottom;
        //因为top = baseLineY + fm.top;
        //所以 baseLineY = top - fm.top;
        //给定左上角顶点时计算方法
        /*Paint.FontMetrics fm = mPaint.getFontMetrics();
        float baseLineY = mTopY - fm.top;
        float baseLineX = mTopX;
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float bottom = baseLineY + fm.bottom;*/

        //给定中间线时计算方法
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float baseLineY = mTopY + (fm.bottom - fm.top) / 2 - fm.bottom;
        float baseLineX = mTopX;
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float bottom = baseLineY + fm.bottom;

        //画顶点
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.GREEN);
        canvas.drawPoint(mTopX, mTopY, mPaint);
        mPaint.setStrokeWidth(1);
        //写文字
        mPaint.setColor(Color.BLACK);
        canvas.drawText(mContent, baseLineX, baseLineY, mPaint);

        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);

        //画top
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, mTopY, 3000, mTopY, mPaint);

        //画ascent
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, ascent, 3000, ascent, mPaint);

        //画descent
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX, descent, 3000, descent, mPaint);

        //画bottom
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, bottom, 3000, bottom, mPaint);


    }
}
