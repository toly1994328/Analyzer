package com.toly1994.analyzer.event_parser;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/15 0015:8:14<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：移动方向枚举
 */
public enum Orientation {

    NO("无"),//无
    TOP("上"), //上
    BOTTOM("下"),//下
    LEFT("左"),//左
    RIGHT("右"),//右
    LEFT_TOP("左上"),// 左上
    RIGHT_TOP("右上"), // 右上
    LEFT_BOTTOM("左下"),//左下
    RIGHT_BOTTOM("右下")//右下
    ;
    private String or;

    Orientation(String or) {
        this.or = or;

    }

    public String value() {
        return or;
    }
}