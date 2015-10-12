package com.translate.widget.RapidFloating;

import com.nineoldandroids.animation.AnimatorSet;

/**
 * Created by DongZ on 2015/10/12 0012.
 */
public interface OnRapidFloatingActionListener {

    /**
     * RFAB被点击
     */
    void onRFABClick();

    /**
     * RFAC内容被展开或者收缩
     */
    void toggleContent();

    /**
     * RFAC内容被展开
     */
    void expandContent();

    /**
     * RFAC内容被收缩
     */
    void collapseContent();

    /**
     * 获取当前的RFAL对象
     *
     * @return
     */
    RapidFloatingActionLayout obtainRFALayout();

    /**
     * 获取当前的RFAB对象
     *
     * @return
     */
    RapidFloatingActionButton obtainRFAButton();

    /**
     * 获取当前的RFAC对象
     *
     * @return
     */
    RapidFloatingActionContent obtainRFAContent();

    /**
     * RFAC内容被展开时需要自定义的额外动画
     *
     * @param animatorSet
     */
    void onExpandAnimator(AnimatorSet animatorSet);

    /**
     * RFAC内容被收缩时需要自定义的额外动画
     *
     * @param animatorSet
     */
    void onCollapseAnimator(AnimatorSet animatorSet);
}
