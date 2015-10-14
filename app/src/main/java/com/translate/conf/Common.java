package com.translate.conf;

import com.translate.R;
import com.translate.commponents.BaseApplication;
import com.translate.database.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongZ on 2015/10/12 0012.
 */
public class Common {

    private String[] translateType;

    private static List<Integer> iconRids = new ArrayList<>();


    private static int translateId ;

    public Common(){
        translateType = BaseApplication.mContext.getResources().getStringArray(R.array.translate_language);
    }

    private static class SingleInstance{
        public static Common INSTANCE = new Common();

    }
    public static Common getInstance(){
        return SingleInstance.INSTANCE;
    }



    static{
        //默认翻译语言，0 默认翻译成中文
        translateId = SharedPreferenceUtil.getInstance(BaseApplication.mContext).getInt(SharedPreferenceUtil.TRANSLATE_LANGUAGE);

        iconRids.add(R.mipmap.zh); //0
        iconRids.add(R.mipmap.en); //1
        iconRids.add(R.mipmap.fr); //2
        iconRids.add(R.mipmap.ja); //3
        iconRids.add(R.mipmap.kr); //4
        iconRids.add(R.mipmap.ru); //5
    }

    //获取默认显示翻译语言图片
    public int getRapidId(){
        return iconRids.get(translateId);
    }
    public int getRapidId(int position){
        return iconRids.get(position);
    }
    public int getTranslateId(){
        return translateId;
    }
    public void setTranslateId(int id){
        SharedPreferenceUtil.getInstance(BaseApplication.mContext).putInt(SharedPreferenceUtil.TRANSLATE_LANGUAGE, id);
        this.translateId = id;
    }


    public String getTranslateType(){

        int position = SharedPreferenceUtil.getInstance(BaseApplication.mContext).getInt(SharedPreferenceUtil.TRANSLATE_LANGUAGE);
        return translateType[position];
    }
}
