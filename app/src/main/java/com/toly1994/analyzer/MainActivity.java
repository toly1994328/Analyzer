package com.toly1994.analyzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.toly1994.analyzer.analyze.AnalyzeView;
import com.toly1994.analyzer.analyze.Viewer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AnalyzeView mAv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.test_list_item);

        View view = findViewById(android.R.id.content);

        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        Viewer viewer = new Viewer(view);


        String info = viewer.showInfo();
        Log.e(TAG, "onCreate: "+info);


//        mAv = findViewById(R.id.id_av);
//
//        mAv.analyze(view);
//
//        view.setOnClickListener(v -> {
//            ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0, 360).setDuration(2000);//设置时常
//
//            animator.addUpdateListener(animation -> {
////                mAv.getTextView().setText("旋转角度:" + Math.round(v.getRotation()) + "°");
////                mAv.invalidate();
//            });
//            animator.start();
//        });
//
//        View fab = findViewById(R.id.id_fab_b);
//
//        fab.setOnClickListener(v -> {
//            mAv.toggle();
//            mAv.getTextView().setText(viewer.getLayoutInfo());
//        });
//
//        fab.setOnLongClickListener(v -> {
//            mAv.toggleTouchable();
//            return true;
//        });
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        if (mAv.isTouchable()) {
//            return false;
//        }
//
//        // Calculate ActionBar height
//        TypedValue tv = new TypedValue();
//        int actionBarHeight = 0;
//        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
//        }
//
//        mAv.updateXY(event.getX(), event.getY() - actionBarHeight - SizeHelper.getStatusBarHeight(this));
//        return super.onTouchEvent(event);
    }


}
