package com.toly1994.analyzer.utils;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/20/020:9:27<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class Positoner {

    /**
     * 点位解析器
     *
     * @param bitmap bitmap
     * @param w 单体宽
     * @param h 单体高
     * @return 解析成的点位数组
     */
    public static List<Point> renderBitmap(Bitmap bitmap, int w, int h) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                if (pixel != -1) {//此处过滤掉白颜色
                    int rX = (i * 2 + 1) * (w + 1);//第(i，j)个点圆心横坐标
                    int rY = (j * 2 + 1) * (h + 1);//第(i，j)个点圆心纵坐标
                    points.add(new Point(rX, rY));
                }
            }
        }
        return points;
    }

    /**
     * 点位解析器
     *
     * @param arr 数组
     * @param w   单体宽
     * @param h   单体高
     * @return 解析成的点位数组
     */
    public static List<Point> renderDigit(int[][] arr, int w, int h) {
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[j].length; j++) {//一行一行遍历，遇到1就画
                if (arr[i][j] == 1) {
                    int rX = (j * 2 + 1) * (w + 1);//第(i，j)个点圆心横坐标
                    int rY = (i * 2 + 1) * (h + 1);//第(i，j)个点圆心纵坐标
                    points.add(new Point(rX, rY));
                }
            }
        }
        return points;
    }
}
