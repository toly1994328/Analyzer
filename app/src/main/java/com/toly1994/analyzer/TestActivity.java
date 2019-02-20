package com.toly1994.analyzer;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Point point = new Point();


//        TextView boxRed = baseTest();
        LinearLayout ll = testLinearLayout();

        setContentView(ll);
    }

    @NonNull
    private LinearLayout testLinearLayout() {
        LinearLayout ll = new LinearLayout(this);
        ll.setBackgroundColor(0x5597CCFF);//蓝色背景

        TextView boxRed = new TextView(this);
        boxRed.setText("玉面奕星龙");
        boxRed.setBackgroundColor(Color.RED);//红色背景

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(200, 200);
        llParams.leftMargin = 20;
        llParams.topMargin = 20;
        boxRed.setLayoutParams(llParams);


        LinearLayout.LayoutParams llParamsBlue = new LinearLayout.LayoutParams(200, 200);
        llParamsBlue.leftMargin = 20;
        llParamsBlue.topMargin = 20;
        llParamsBlue.gravity = Gravity.CENTER;

        TextView boxBlue = new TextView(this);
        boxBlue.setText("张风捷特烈");
        boxBlue.setBackgroundColor(Color.BLUE);//红色背景
        boxBlue.setLayoutParams(llParamsBlue);


        LinearLayout.LayoutParams llParamsYellow = new LinearLayout.LayoutParams(200, 200);
        llParamsYellow.leftMargin = 20;
        llParamsYellow.topMargin = 20;
        llParamsYellow.weight = 1;

        TextView boxYellow = new TextView(this);
        boxYellow.setText("百里巫缨");
        boxYellow.setBackgroundColor(Color.YELLOW);//红色背景
        boxYellow.setLayoutParams(llParamsYellow);


        ll.addView(boxRed);//添加子民
        ll.addView(boxBlue);//添加子民
        ll.addView(boxYellow);//添加子民

        ll.setPadding(50, 50, 50, 50);
//        ll.setOrientation(LinearLayout.VERTICAL);
        return ll;
    }

    @NonNull
    private TextView baseTest() {
        TextView boxRed = new TextView(this);
        boxRed.setText("张风捷特烈");
        boxRed.setBackgroundColor(Color.RED);

//        boxRed.setWidth(200);
//        boxRed.setHeight(200);

        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(200, 200);
        boxRed.setLayoutParams(layoutParams);
        boxRed.setGravity(Gravity.CENTER);

//        ViewGroup.LayoutParams params = boxRed.getLayoutParams();
//        params.
//
//
//        boxRed.setPadding(20, 20, 20, 20);
        return boxRed;
    }
}























