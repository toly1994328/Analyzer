package com.toly1994.analyzer.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.toly1994.analyzer.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/20/020:10:09<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class EventTestActivity extends AppCompatActivity {

    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mView = findViewById(R.id.view);
    }
}
