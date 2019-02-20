package com.toly1994.analyzer.event_parser;

import android.graphics.PointF;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/15 0015:8:13<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：事件监听回调
 */

public interface OnEventListener {
    /**
     * 点击
     *
     * @param pointF 落点
     */
    void down(PointF pointF);

    /**
     * 抬起
     *
     * @param pointF      抬起点
     * @param orientation 方向
     */
    void up(PointF pointF, Orientation orientation);

    /**
     * 移动
     *
     * @param v           速度
     * @param dy          y 位移
     * @param dx          x位移
     * @param dir         角度
     * @param orientation 方向
     */
    void move(double v, float dy, float dx, double dir, Orientation orientation);
}