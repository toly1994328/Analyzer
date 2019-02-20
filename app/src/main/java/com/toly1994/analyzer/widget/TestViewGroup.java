package com.toly1994.analyzer.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/20/020:10:30<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：测试ViewGroup
 */
public class TestViewGroup extends ViewGroup {

    private View mChild;
    private View mChild2;

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mChild = getChildAt(0);
        mChild2 = getChildAt(1);

        mChild.setOnClickListener(v -> {
            Toast.makeText(getContext(), "你敢戳我?", Toast.LENGTH_SHORT).show();
        });

        mChild.layout(0, 0, mChild.getMeasuredWidth(), mChild.getMeasuredHeight());
        mChild2.layout(0, 200, mChild2.getMeasuredWidth(), 200 + mChild2.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
//                useLayout(mChild, x, y);
//                useTranslation(mChild, x, y);
//                useTranslationAnimation(mChild, x, y);
//                useViewPropertyAnimator(mChild, x, y);
//                useLayoutAnimate(mChild, x, y);
//                useScrollTo(-x, -y);
                useScrollBy(-x, -y);


                Log.e("onTouchEvent---", "onTouchEvent: " + x + "------" + y);
        }

        return super.onTouchEvent(event);
    }

    private void useLayout(View view, int x, int y) {
        view.layout(x, y,
                x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
        //以下四行等价上一行
//                mChild.setLeft(x);
//                mChild.setTop(y);
//                mChild.setRight(x + mChild.getMeasuredWidth());
//                mChild.setBottom(y + mChild.getMeasuredHeight());
    }

    private void useTranslation(View view, int x, int y) {
        view.setTranslationX(x);
        view.setTranslationY(y);
    }

    private void useTranslationAnimation(View view, int x, int y) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, x, 0, y);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }

    private void useViewPropertyAnimator(View view, int x, int y) {
        view.animate().translationX(x).translationY(y).setDuration(500).start();
        //下两句效果同上
//        ObjectAnimator.ofFloat(view, "translationX", x).setDuration(500).start();
//        ObjectAnimator.ofFloat(view, "translationY", y).setDuration(500).start();
    }

    private void useLayoutAnimate(View view, int x, int y) {
        //下两句效果同上
        ObjectAnimator.ofInt(view, "Left", x).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Top", y).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Right", x + view.getMeasuredWidth()).setDuration(500).start();
        ObjectAnimator.ofInt(view, "Bottom", y + view.getMeasuredHeight()).setDuration(500).start();
    }

    private Scroller mScroller = new Scroller(getContext());

    private void useScrollTo(int x, int y) {
        scrollTo(x, y);
    }


    private void useScrollBy(int x, int y) {
        scrollBy(x, y);
    }

}
