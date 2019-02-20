package com.toly1994.analyzer.analyze;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toly1994.analyzer.event_parser.EventParser;
import com.toly1994.analyzer.event_parser.OnEventAdapter;
import com.toly1994.analyzer.event_parser.Orientation;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/16/016:14:59<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class AnalyzeView extends RelativeLayout {
    private static final String TAG = "AnalyzeView";
    private boolean isShow;
    public static final int BlUE_COLOR = 0x503D63F1;
    public static final int RED_COLOR = 0x50ff0000;
    public static final int YELLOW_COLOR = 0x50F3D32D;
    public static final int TOUCHABLE_COLOR = 0x3097FFFA;//浅蓝背景，可操作
    public static final int COLOR_BODY = 0x80FDDC9A;//浅蓝背景，可操作
    private static final int COLOR_PADDING = 0x80C2CE89;
    private TextView mTextView;
    private Paint mPaint;
    private Picture mGrid_50X50;
    private boolean isTouchable;
    private Paint mCrossPaint;


    int x, y;
    float height, width;
    private Paint mTextPaint;
    private View targetView;
    private Paint mFillPaint;
    private EventParser mParser;
    private Point mWinSize;

    public AnalyzeView(@NonNull Context context) {
        this(context, null);
    }

    public AnalyzeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "AnalyzeView: ");
        init();
    }

    private void init() {

        mWinSize = new Point();
        SizeHelper.loadWinSize(getContext(), mWinSize);

        setBgBlue();
        mTextView = new TextView(getContext());
        mTextView.setTextSize(14);
        mTextView.setTextColor(Color.BLACK);
        addView(mTextView);
        this.setVisibility(GONE);
        setGravity(Gravity.CENTER);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(1);
        mCrossPaint = new Paint(mPaint);
        mCrossPaint.setColor(Color.BLUE);
        mCrossPaint.setStrokeWidth(2);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);

        mTextPaint.setTextSize(40);

        mFillPaint = new Paint(mPaint);
        mFillPaint.setStyle(Paint.Style.FILL);

        mParser = new EventParser();
        mParser.setOnEventListener(new OnEventAdapter() {
            @Override
            public void move(double v, float dy, float dx, double dir, Orientation orientation) {
                scrollTo(-(int) dx * 2, 0);
                Log.e(TAG, "move: " + dx);
            }
        });


    }


    @Override
    protected void onFinishInflate() {
        Log.e(TAG, "onFinishInflate: ");
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                4000,
                MeasureSpec.getSize(heightMeasureSpec)
        );
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout: ");
        mGrid_50X50 = GridLine.getGrid(mPaint, 50, mWinSize.x, mWinSize.y);
        height = getHeight();
        width = getWidth();


//        new FrameLayout(getContext());
//
//        int childCount = getChildCount();//获取子view个数
//        int mWidth = getWidth();//MyPager的宽度
//        int mHeight = getHeight();//MyPager的高度
//        for (int i = 0; i < childCount; i++) {
//            View childView = getChildAt(i);//一次获取子view
//
//            //指定子view的位置  ,  左，上，右，下，是指在viewGround坐标系中的位置
//            childView.layout(0 + mWidth * i, 0, mWidth * (i + 1), mHeight);
//        }


    }

    /**
     * 展开或隐藏
     */
    public void toggle() {//展开或隐藏
        if (!isShow) {
            this.setVisibility(VISIBLE);
        } else {
            this.setVisibility(GONE);
        }
        isShow = !isShow;
    }

    public TextView getTextView() {//获取TextView引用
        return mTextView;
    }

    public void setBgRed() {//设为红色
        setBackgroundColor(RED_COLOR);
    }

    public void setBgBlue() {//设为蓝色
        setBackgroundColor(BlUE_COLOR);
    }

    public void setBgYellow() {//设为黄色
        setBackgroundColor(YELLOW_COLOR);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCross(canvas);

        mGrid_50X50.draw(canvas);
        viewPath(canvas, targetView, mFillPaint);

    }

    private void drawCross(Canvas canvas) {
        canvas.drawLine(0, y, width, y, mCrossPaint);
        canvas.drawLine(x, 0, x, height, mCrossPaint);
        canvas.drawCircle(x, y, 4, mCrossPaint);
        canvas.drawText("(" + x + "," + y + ")", x + 20, y - 20, mTextPaint);


    }

    public void updateXY(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
        invalidate();
    }

    public void analyze(View view) {
        targetView = view;
        invalidate();
    }

    private void viewPath(Canvas canvas, View view, Paint paint) {
        int w = view.getWidth();
        int h = view.getHeight();

        int pl = view.getPaddingLeft();
        int pr = view.getPaddingRight();
        int pb = view.getPaddingBottom();
        int pt = view.getPaddingTop();

//        ViewGroup.LayoutParams params = view.getLayoutParams();
////        view.get
//        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//
//        int leftMargin = lp.leftMargin;
        canvas.save();
//        canvas.translate(300, 300);
        mFillPaint.setColor(COLOR_BODY);
        canvas.drawRect(0, 0, w, h, paint);
        paint.setColor(COLOR_PADDING);
        canvas.drawRect(pl, pt, w - pr, h - pb, paint);
        canvas.drawText("(width,height)/px:" + "(" + w + "," + h + ")",
                w + 20, 60, mTextPaint);
        canvas.drawText("padding/px:" + "(" + pl + ","  + pt + "," + pr + "," + pb + ")",
                w + 20, 100, mTextPaint);
        canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!isTouchable) {
            return false;
        }
        mParser.parseEvent(event);

        return isTouchable;
    }

    public boolean isTouchable() {
        return isTouchable;
    }

    public void setTouchable(boolean touchable) {
        isTouchable = touchable;
    }

    public void toggleTouchable() {
        if (!isTouchable) {
            setBackgroundColor(TOUCHABLE_COLOR);
        } else {
            setBackgroundColor(BlUE_COLOR);
        }
        isTouchable = !isTouchable;
    }


}
