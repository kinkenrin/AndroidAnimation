package com.github.king.androidanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Created by jinxl on 2019/1/2.
 */
public class FontView extends View {

    private Paint mPaint;
    private Rect mMinRect;

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    final int baseLineY = 200;
    final int baseLineX = 0;

    String mContent = "harvic\'s blog你好";

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 30, getResources().getDisplayMetrics()));
        mPaint.setTextAlign(Paint.Align.LEFT);
        mMinRect = new Rect();
        mPaint.getTextBounds(mContent, 0, mContent.length(), mMinRect);
        //最小矩形，实际top位置
        mMinRect.top = mMinRect.top + baseLineY;
        //最小矩形，实际bottom位置
        mMinRect.bottom = mMinRect.bottom + baseLineY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        ascent: 系统建议的，绘制单个字符时，字符应当的最高高度所在线
//        descent:系统建议的，绘制单个字符时，字符应当的最低高度所在线
//        top: 可绘制的最高高度所在线
//        bottom: 可绘制的最低高度所在线
        //计算各线所在位置
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float top = baseLineY + fm.top;
        float bottom = baseLineY + fm.bottom;

        //所占高度
        float height = bottom - top;
        //所占宽度
        float width = mPaint.measureText(mContent);

        //画所占矩形
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(baseLineX, top, baseLineX + width, bottom, mPaint);

        //画最小矩形
        mPaint.setColor(Color.RED);
        canvas.drawRect(mMinRect, mPaint);

        //写文字
        mPaint.setColor(Color.BLACK);
        canvas.drawText(mContent, baseLineX, baseLineY, mPaint);

        //画基线
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, mPaint);

        //画top
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, top, 3000, top, mPaint);

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
