package com.translate.bean;

/**
 * Created by DongZ on 2015/10/14 0014.
 */
public class TranslateBean {
    private String org;
    private long addTime;
    private boolean isTranslate;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public boolean isTranslate() {
        return isTranslate;
    }

    public void setIsTranslate(boolean isTranslate) {
        this.isTranslate = isTranslate;
    }
}
