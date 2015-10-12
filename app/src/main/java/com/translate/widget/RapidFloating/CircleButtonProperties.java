package com.translate.widget.RapidFloating;

import android.content.Context;

import com.translate.utils.Utils;

import java.io.Serializable;

/**
 * Created by DongZ on 2015/10/12 0012.
 */
public class CircleButtonProperties implements Serializable {
    /**
     * 标准大小（normal或mini）
     */
    private RFABSize standardSize;

    /**
     * 阴影颜色
     */
    private int shadowColor;
    /**
     * 阴影半径
     */
    private int shadowRadius;
    /**
     * 阴影x偏移
     */
    private int shadowDx;
    /**
     * 阴影y偏移
     */
    private int shadowDy;

    public RFABSize getStandardSize() {
        return standardSize;
    }

    public CircleButtonProperties setStandardSize(RFABSize standardSize) {
        this.standardSize = standardSize;
        return this;
    }

    /**
     * 真实的大小，加上阴影的大小
     *
     * @param context
     * @return
     */
    public int getRealSizePx(Context context) {
        return getStandardSizePx(context) + getShadowOffsetHalf() * 2;
    }

    public int getShadowOffsetHalf(){
        return 0 >= shadowRadius ? 0 : Math.max(shadowDx, shadowDy) + shadowRadius;
    }

    public int getStandardSizePx(Context context) {
        return Utils.dip2px(context, standardSize.getDpSize());
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public CircleButtonProperties setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public float getShadowRadius() {
        return shadowRadius;
    }

    public CircleButtonProperties setShadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
        return this;
    }

    public int getShadowDx() {
        return shadowDx;
    }

    public CircleButtonProperties setShadowDx(int shadowDx) {
        this.shadowDx = shadowDx;
        return this;
    }

    public int getShadowDy() {
        return shadowDy;
    }

    public CircleButtonProperties setShadowDy(int shadowDy) {
        this.shadowDy = shadowDy;
        return this;
    }
}