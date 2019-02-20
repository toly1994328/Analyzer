package com.toly1994.analyzer.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Scroller;

import com.toly1994.analyzer.R;
import com.toly1994.analyzer.analyze.SizeHelper;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/19/019:13:57<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class FlowerLayout extends ViewGroup {
    private int mRadius;
    private static final String TAG = "FlowerLayout";

    private int centerId = 0;//默认中心点

    /**
     * 用来显示点阵的二维数组
     */
    public static final int[][] digit_test = new int[][]
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0},
                    {1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0},
            };

    private Point winSize = new Point();
    private ListAdapter mAdapter;
    private Bitmap mBitmap;
    private ValueAnimator mAnimator;
    private int mCount;
    private VelocityTracker velocityTracker;

    public void setAdapter(ListAdapter adapter) {
        mAdapter = adapter;
    }

    public FlowerLayout(Context context) {
        this(context, null);
    }

    public FlowerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        Log.e(TAG, "构造函数: " + getHeight());
        SizeHelper.loadWinSize(getContext(), winSize);
        setBackgroundColor(0x55D3E8FD);
    }

    private void init(AttributeSet attrs) {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart);
        mAnimator = ValueAnimator.ofInt(0, 360);
        mAnimator.setDuration(3000);
        mAnimator.addUpdateListener(a -> {
            int deg = (int) a.getAnimatedValue();
            first2Center();
            layoutCircle(1, deg);
        });
    }

    private void formFlower() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View petal = mAdapter.getView(i, null, this);
            int position = i;
            if (mOnItemClickListener != null && i != 0) {
                petal.setOnClickListener(v -> {
                    ObjectAnimator.ofFloat(v, "ScaleX", 1f, 0.8f, 1f).setDuration(200).start();
                    ObjectAnimator.ofFloat(v, "ScaleY", 1f, 0.8f, 1f).setDuration(200).start();
                    mOnItemClickListener.onClick(v, this, position);
                    swapWithAnim(position, centerId);
                });
            }
            addView(petal);//填入花瓣
        }
    }

    /**
     * 交换两个View的位置
     *
     * @param positionMe 点击者
     * @param positionHe 目标
     */
    private void swap(int positionMe, int positionHe) {
        View me = getChildAt(positionMe);
        View he = getChildAt(positionHe);

        int TempMeLeft = me.getLeft();
        int TempMeTop = me.getTop();
        int TempMeRight = me.getRight();
        int TempMeBottom = me.getBottom();


        me.layout(he.getLeft(), he.getTop(), he.getRight(), he.getBottom());
        he.layout(TempMeLeft, TempMeTop, TempMeRight, TempMeBottom);
        centerId = positionMe;
    }

    /**
     * 交换两个View的位置
     *
     * @param positionMe 点击者
     * @param positionHe 目标
     */
    private void swapWithAnim(int positionMe, int positionHe) {
        View me = getChildAt(positionMe);
        View he = getChildAt(positionHe);

        int TempMeLeft = me.getLeft();
        int TempMeTop = me.getTop();

        useLayoutAnimate(me, he.getLeft(), he.getTop());
        useLayoutAnimate(he, TempMeLeft, TempMeTop);

        centerId = positionMe;
    }


    private void useLayoutAnimate(View view, int x, int y) {
        int time = 1000;
        //下两句效果同上
        ObjectAnimator.ofInt(view, "Left", x).setDuration(time).start();
        ObjectAnimator.ofInt(view, "Top", y).setDuration(time).start();
        ObjectAnimator.ofInt(view, "Right", x + view.getMeasuredWidth()).setDuration(time).start();
        ObjectAnimator.ofInt(view, "Bottom", y + view.getMeasuredHeight()).setDuration(time).start();
//        ObjectAnimator.ofFloat(view, "ScaleX", 1f,1.5f,1f).setDuration(500).start();
//        ObjectAnimator.ofFloat(view, "ScaleY", 1f,1.5f,1f).setDuration(500).start();
        ObjectAnimator.ofFloat(view, "RotationY", 360, 0).setDuration(time).start();
//        ObjectAnimator.ofFloat(view, "RotationX", 1f,1.5f,1f).setDuration(500).start();
//        view.animate().rotationY(360).setDuration(time).start();

    }

    @Override
    protected void onAttachedToWindow() {
        Log.e(TAG, "onAttachedToWindow: ");
        if (mAdapter != null) {
            formFlower();
        }
        super.onAttachedToWindow();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate: " + getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: " + getHeight());

//        measureSelf(widthMeasureSpec, heightMeasureSpec);//测量自身
        measureChildren(widthMeasureSpec, heightMeasureSpec);

    }


    /**
     * 测量自身
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void measureSelf(int widthMeasureSpec, int heightMeasureSpec) {


        int width = 0;
        int height = 0;
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int h = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        if (wMode != MeasureSpec.EXACTLY || hMode != MeasureSpec.EXACTLY) {//宽高一者为非精确
            width = getSuggestedMinimumWidth();
            width = width == 0 ? winSize.x : width;
            height = getSuggestedMinimumHeight();
            height = height == 0 ? winSize.y : height;
        } else {
            width = height = Math.max(w, h);
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: " + getHeight());
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        initRotate();

//        List<Point> points = Positoner.renderDigit(
//                getChildAt(0).getMeasuredWidth() / 2,
//                getChildAt(0).getMeasuredHeight() / 2
//        );

//        List<Point> points = Positoner.renderBitmap(
//                mBitmap,
//                getChildAt(0).getMeasuredWidth() / 2,
//                getChildAt(0).getMeasuredWidth() / 2);
//        layout(points, 0);


        first2Center();
        layoutCircle(1, 0);
    }

    private void initRotate() {
        int width = getWidth();
        int height = getHeight();
        mPivotX = width/2;
        mPivotY = height/2;
        mVelocityTracker = VelocityTracker.obtain();
        mScrollAvailabilityRatio = .3F;
    }

    private void first2Center() {
        int h = getHeight();
        int w = getWidth();
        View child0 = getChildAt(0);
        centerId = 0;
        child0.setOnClickListener(v -> {
            swapWithAnim(0, centerId);
        });

        int child0W = child0.getMeasuredWidth();
        int child0H = child0.getMeasuredHeight();
        int left = w / 2 - child0W / 2;
        int top = w / 2 - child0H / 2;
        child0.layout(left, top, left + child0W, top + child0H);
    }


    /**
     * @param start 第一个排成圆的View索引
     * @param dθ    旋转角度
     */
    private void layoutCircle(int start, float dθ) {
        int count = getChildCount();
        for (int i = start; i < count; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            int r = (getWidth() - childW) / 2;
            float posX = childW / 2 + r - r * cos(i * 360.f / (count - 1) + dθ);
            float posY = childH / 2 + r - r * sin(i * 360.f / (count - 1) + dθ);
            int leftPos = (int) (posX - childW / 2);
            int topPos = (int) (posY - childH / 2);
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }
    }

    /**
     * @param points 点位
     * @param start  第一个排成圆的View索引
     */
    private void layout(List<Point> points, int start) {
        int count = getChildCount();
        for (int i = start; i < count; i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            int leftPos = points.get(i).x - childW / 2;
            int topPos = points.get(i).y - childH / 2;
            childView.layout(leftPos, topPos, leftPos + childW, topPos + childH);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.e(TAG, "dispatchDraw: " + getHeight());


//        canvas.drawRect(0,0,200,200,new Paint());
    }

    //----------------------------条目点击监听-------------------
    public interface OnItemClickListener {
        void onClick(View v, ViewGroup viewGroup, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    private float cos(float θ) {
        return (float) Math.cos(θ / 180 * Math.PI);
    }

    private float sin(float θ) {
        return (float) Math.sin(θ / 180 * Math.PI);
    }


    private Scroller mScroller = new Scroller(getContext());

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View centerView = getChildAt(0);


        float x, y;
        x = event.getRawX();
        y = event.getRawY();
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mAnimator.start();
                abortAnimation();
//                centerView.layout(x, y,
//                        x + centerView.getMeasuredWidth(), y + centerView.getMeasuredHeight());
                Log.e("EVENT", "onTouchEvent: " + x + "------" + y);
                break;
            case MotionEvent.ACTION_MOVE:
                handleActionMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                mVelocityTracker.computeCurrentVelocity(1000);
                mScroller.fling(0, 0,
                        (int) mVelocityTracker.getXVelocity(),
                        (int) mVelocityTracker.getYVelocity(),
                        Integer.MIN_VALUE, Integer.MAX_VALUE,
                        Integer.MIN_VALUE, Integer.MAX_VALUE);
                startFling();
                break;
        }
        mStartX = x;
        mStartY = y;
        return true;
    }

    //------------------------惯性旋转----------------------------
    private int mPivotX, mPivotY;
    private float mStartX, mStartY;
    private float mLastScrollOffset;
    private float mScrollAvailabilityRatio;
    private boolean isClockwiseScrolling;
    private boolean isShouldBeGetY;
    private boolean isRecycled;
    private VelocityTracker mVelocityTracker;

    private Handler mHandler = new Handler(msg -> {
        computeInertialSliding();
        return false;
    });

    /**
     * 处理惯性滚动
     */
    private void computeInertialSliding() {
        checkIsRecycled();
        if (mScroller.computeScrollOffset()) {
            float y = ((isShouldBeGetY ? mScroller.getCurrY() : mScroller.getCurrX()) * mScrollAvailabilityRatio);
            if (mLastScrollOffset != 0) {
                float offset = fixAngle(Math.abs(y - mLastScrollOffset));

                float deg = isClockwiseScrolling ? offset : -offset;
                setRotation(getRotation() + deg);
            }
            mLastScrollOffset = y;
            startFling();
        } else if (mScroller.isFinished()) {
            mLastScrollOffset = 0;
        }
    }

    /**
     * 计算滑动的角度
     */
    private void handleActionMove(float x, float y) {
        float l, t, r, b;
        if (mStartX > x) {
            r = mStartX;
            l = x;
        } else {
            r = x;
            l = mStartX;
        }
        if (mStartY > y) {
            b = mStartY;
            t = y;
        } else {
            b = y;
            t = mStartY;
        }
        float pA1 = Math.abs(mStartX - mPivotX);
        float pA2 = Math.abs(mStartY - mPivotY);
        float pB1 = Math.abs(x - mPivotX);
        float pB2 = Math.abs(y - mPivotY);
        float hypotenuse = (float) Math.sqrt(Math.pow(r - l, 2) + Math.pow(b - t, 2));
        float lineA = (float) Math.sqrt(Math.pow(pA1, 2) + Math.pow(pA2, 2));
        float lineB = (float) Math.sqrt(Math.pow(pB1, 2) + Math.pow(pB2, 2));
        if (hypotenuse > 0 && lineA > 0 && lineB > 0) {
            float angle = fixAngle((float) Math.toDegrees(Math.acos((Math.pow(lineA, 2) + Math.pow(lineB, 2) - Math.pow(hypotenuse, 2)) / (2 * lineA * lineB))));

            float deg = (isClockwiseScrolling = isClockwise(x, y)) ? angle : -angle;
            setRotation(getRotation() + deg);
        }
    }

    /**
     * 打断动画
     */
    public void abortAnimation() {
        checkIsRecycled();
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
    }

    /**
     * 释放资源
     */
    public void release() {
        checkIsRecycled();
        mScroller = null;
        mVelocityTracker.recycle();
        mVelocityTracker = null;
        isRecycled = true;
    }

    /**
     * 检测手指是否顺时针滑动
     *
     * @param x 当前手指的x坐标
     * @param y 当前手指的y坐标
     * @return 是否顺时针
     */
    private boolean isClockwise(float x, float y) {
        return (isShouldBeGetY = Math.abs(y - mStartY) > Math.abs(x - mStartX)) ?
                x < mPivotX != y > mStartY : y < mPivotY == x > mStartX;
    }

    /**
     * 开始惯性滚动
     */
    private void startFling() {
        mHandler.sendEmptyMessage(0);
    }

    /**
     * 调整角度，使其在360之间
     *
     * @param rotation 当前角度
     * @return 调整后的角度
     */
    private float fixAngle(float rotation) {
        float angle = 360F;
        if (rotation < 0) {
            rotation += angle;
        }
        if (rotation > angle) {
            rotation = rotation % angle;
        }
        return rotation;
    }

    /**
     * 检查资源释放已经释放
     */
    private void checkIsRecycled() {
        if (isRecycled) {
            throw new IllegalStateException(" is recycled!");
        }
    }
}
