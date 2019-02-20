package com.toly1994.analyzer.event_parser;

import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/6 0006:20:22<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：事件解析器
 */
public class EventParser {
    private OnEventListener onEventListener;
    private Orientation mOrientation = Orientation.NO;

    private PointF mTagPos;//按下坐标点
    //移动时坐标点---在此创建对象，避免在move中创建大量对象
    private PointF mMovingPos = new PointF(0, 0);

    private float detaY = 0;//下移总量
    private float detaX = 0;//右移总量

    private boolean isDown = false;//是否按下
    private boolean isMove = false;//是否移动

    private PointF mDownPos= new PointF(0, 0);//记录按下时点
    private long lastTimestamp = 0L;//最后一次的时间戳

    public void setOnEventListener(OnEventListener onEventListener) {
        this.onEventListener = onEventListener;
    }

    /**
     * 添加自己的事件解析
     *
     * @param event 事件
     */
    public void parseEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                //按下---为p0赋值
                mTagPos = new PointF(event.getX(), event.getY());
                mDownPos.x = mTagPos.x;
                mDownPos.y = mTagPos.y;//这里注意是值相等
                lastTimestamp = System.currentTimeMillis();
                if (onEventListener != null) {
                    onEventListener.down(mTagPos);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的那刻的坐标(移动中，不断更新)
                mMovingPos.x = event.getX();
                mMovingPos.y = event.getY();
                //处理速度
                detaX = mMovingPos.x - mDownPos.x;
                detaY = mMovingPos.y - mDownPos.y;
                //下移单量
                float dx = mMovingPos.x - mTagPos.x;
                //右移单量
                float dy = mMovingPos.y - mTagPos.y;
                double ds = Math.sqrt(dx * dx + dy * dy);//偏移位移单量
                double dir = deg((float) Math.acos(dx / ds));//角度

                long curTimestamp = System.currentTimeMillis();
                long dt = curTimestamp - lastTimestamp;
                //由于速度是C*px/ms
                double v = ds / dt * 100;
                orientationHandler(dir);//处理方向
                if (onEventListener != null) {
                    onEventListener.move(v,
                            mMovingPos.y - mDownPos.y, mMovingPos.x - mDownPos.x,
                            detaY < 0 ? dir : -dir, mOrientation);
                }
                if (Math.abs(detaY) > 50 / 3.0) {
                    isMove = true;
                }
                mTagPos.x = mMovingPos.x;//更新位置
                mTagPos.y = mMovingPos.y;//更新位置----注意这里不能让两个对象相等
                lastTimestamp = curTimestamp;//更新时间
                break;
            case MotionEvent.ACTION_UP:
                if (onEventListener != null) {
                    onEventListener.up(mTagPos, mOrientation);
                }
                reset();//重置工作
                break;
        }
    }

    /**
     * 重置工作
     */
    private void reset() {
        isDown = false;//重置按下状态
        isMove = false;//重置移动状态
        mDownPos.x = 0;//重置：mDownPos
        mDownPos.y = 0;//重置：mDownPos
        mOrientation = Orientation.NO;//重置方向
    }

    /**
     * 处理方向
     *
     * @param dir 方向
     */
    private void orientationHandler(double dir) {
        if (detaY < 0 && dir > 70 && dir < 110) {
            mOrientation = Orientation.TOP;
        }
        if (detaY > 0 && dir > 70 && dir < 110) {
            mOrientation = Orientation.BOTTOM;
        }
        if (detaX > 0 && dir < 20) {
            mOrientation = Orientation.RIGHT;
        }
        if (detaX < 0 && dir > 160) {
            mOrientation = Orientation.LEFT;
        }
        if (detaY < 0 && dir <= 70 && dir >= 20) {
            mOrientation = Orientation.RIGHT_TOP;
        }
        if (detaY < 0 && dir >= 110 && dir <= 160) {
            mOrientation = Orientation.LEFT_TOP;
        }
        if (detaX > 0 && detaY > 0 && dir >= 20 && dir <= 70) {
            mOrientation = Orientation.RIGHT_BOTTOM;
        }
        if (detaX < 0 && detaY > 0 && dir >= 110 && dir <= 160) {
            mOrientation = Orientation.LEFT_BOTTOM;
        }
    }

    public boolean isDown() {
        return isDown;
    }

    public boolean isMove() {
        return isMove;
    }

    /**
     * 弧度制化为角度制
     *
     * @param rad 弧度
     * @return 角度
     */
    private float deg(float rad) {
        return (float) (rad * 180 / Math.PI);
    }

}
