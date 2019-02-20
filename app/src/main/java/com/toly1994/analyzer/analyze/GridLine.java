package com.toly1994.analyzer.analyze;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.view.View;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/16/016:15:54<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class GridLine {

    public static Picture getGrid_50X50(Paint paint, View view) {
        Picture picture = new Picture();
        int width = view.getWidth();
        int height = view.getHeight();
        Canvas recording = picture.beginRecording(width, height);
        recording.drawPath(gridPath(50, width, height), paint);
        return picture;
    }


    public static Picture getGrid(Paint paint, int step, View view) {
        Picture picture = new Picture();
        int width = view.getWidth();
        int height = view.getHeight();
        Canvas recording = picture.beginRecording(width, height);
        recording.drawPath(gridPath(step, width, height), paint);
        return picture;
    }


    public static Picture getGrid(Paint paint, int step, int width, int height) {
        Picture picture = new Picture();
        Canvas recording = picture.beginRecording(width, height);
        recording.drawPath(gridPath(step, width, height), paint);
        return picture;
    }


    /**
     * 绘制网格:注意只有用path才能绘制虚线
     *
     * @param step 小正方形边长
     */
    public static Path gridPath(int step, int width, int height) {

        Path path = new Path();

        for (int i = 0; i < height / step + 1; i++) {
            path.moveTo(0, step * i);
            path.lineTo(width, step * i);
        }

        for (int i = 0; i < width / step + 1; i++) {
            path.moveTo(step * i, 0);
            path.lineTo(step * i, height);
        }
        return path;
    }
}
