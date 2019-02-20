package com.toly1994.analyzer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.toly1994.analyzer.event_parser.ArcSlidingHelper;
import com.toly1994.analyzer.widget.FlowerAdapter;
import com.toly1994.analyzer.widget.FlowerLayout;
import com.toly1994.analyzer.widget.Petal;

import java.util.ArrayList;

public class FlowerActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArcSlidingHelper mArcSlidingHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FlowerLayout", "onCreate中加载布局完成后: --------------");

        setContentView(R.layout.activity_flower);
        FlowerLayout flowerLayout = findViewById(R.id.id_fl);

//
//        flowerLayout.post(() -> {
//            //创建对象
//            mArcSlidingHelper = ArcSlidingHelper.create(flowerLayout,
//                    angle -> flowerLayout.setRotation(flowerLayout.getRotation() + angle));
//            //开启惯性滚动
//            mArcSlidingHelper.enableInertialSliding(true);
//
//        });
//        getWindow().getDecorView().setOnTouchListener((v, event) -> {
//            //处理滑动事件
//            mArcSlidingHelper.handleMovement(event);
//            return true;
//        });


        ArrayList<Petal> petals = new ArrayList<>();
        petals.add(new Petal(R.mipmap.icon_gql, "葛强丽"));
        petals.add(new Petal(R.mipmap.icon_1, "icon_1"));
        petals.add(new Petal(R.mipmap.icon_2, "icon_2"));
        petals.add(new Petal(R.mipmap.icon_3, "icon_3"));
        petals.add(new Petal(R.mipmap.icon_4, "icon_4"));
        petals.add(new Petal(R.mipmap.icon_5, "icon_5"));
        petals.add(new Petal(R.mipmap.icon_6, "icon_6"));
        petals.add(new Petal(R.mipmap.icon_7, "icon_7"));
        petals.add(new Petal(R.mipmap.icon_8, "icon_8"));
        petals.add(new Petal(R.mipmap.icon_9, "icon_9"));
        petals.add(new Petal(R.mipmap.icon_10, "icon_10"));
        petals.add(new Petal(R.mipmap.icon_11, "icon_11"));
        petals.add(new Petal(R.mipmap.icon_12, "icon_12"));
        petals.add(new Petal(R.mipmap.icon_13, "icon_13"));
//        petals.add(new Petal(R.mipmap.icon_14, "icon_14"));

        flowerLayout.setOnItemClickListener((v, viewGroup, position) -> {
            Toast.makeText(this, "Position:"+position, Toast.LENGTH_SHORT).show();

//            TranslateAnimation translateAnimation = new TranslateAnimation(0, 150, 0, 300);
//            translateAnimation.setDuration(500);
//            translateAnimation.setFillAfter(true);
//            v.startAnimation(translateAnimation);

//            v.animate().translationX(150).translationY(300).setDuration(500).start();
//
//             ObjectAnimator.ofFloat(v, "TranslationX", 150).setDuration(500).start();
//             ObjectAnimator.ofFloat(v, "TranslationY", 300).setDuration(500).start();
        });

        flowerLayout.setAdapter(new FlowerAdapter(petals));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("FlowerLayout", "onStart: ...................");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("FlowerLayout", "onResume: ...................");

    }
}























