package com.toly1994.analyzer.analyze;

import android.view.View;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/17/017:8:27<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ViewData {
    private int width;
    private int height;

    private String clazz;

    private int pl;
    private int pr;
    private int pb;
    private int pt;

    private int ml;
    private int mr;
    private int mb;
    private int mt;

    public void parse(View view) {
        clazz = view.getClass().getName();

        width = view.getWidth();
        height = view.getHeight();
        pl = view.getPaddingLeft();
        pr = view.getPaddingRight();
        pb = view.getPaddingBottom();
        pt = view.getPaddingTop();
    }



}
