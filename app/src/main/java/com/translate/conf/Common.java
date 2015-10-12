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
    private static List<Integer> iconRids = new ArrayList<>();
    private static int translateId ;
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
}