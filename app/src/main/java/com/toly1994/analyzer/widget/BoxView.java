package com.toly1994.analyzer.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.toly1994.analyzer.utils.ColUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/18/018:16:08<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class BoxView extends View {
    private static final String TAG = "TestView";

    private final Paint mPaint;
    private int color;

    public BoxView(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setBackgroundColor(ColUtils.randomRGB());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        canvas.drawRect(0, 0, 200, 200, mPaint);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
