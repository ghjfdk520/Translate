package com.translate.actions;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.translate.bean.DictBean;
import com.translate.bean.TranslateBean;
import com.translate.conf.TodoConstants;
import com.translate.connector.HttpCallBack;
import com.translate.connector.protocol.TranslateProtocol;
import com.translate.database.DistWorker;
import com.translate.dispatcher.Dispatcher;
import com.translate.utils.LightTimer;
import com.translate.utils.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DongZ on 15/10/4.
 */
public class TranslateActionCreators extends ActionsCreator{

    private LinkedList<TranslateBean> translateBeanLinkedList;
    private DistWorker distWorker;

    private LightTimer lightTimer;
    private static TranslateActionCreators instance;
    private TranslateBean translateBean;
    private Gson gson;
    private DictBean dictBean;
    TranslateActionCreators() {
        super();
        distWorker = new DistWorker();
        translateBeanLinkedList = new LinkedList<>();
        gson = new Gson();

        lightTimer = new LightTimer() {
            @Override
            public void run(LightTimer timer) {
                TranslateMethod(translateBeanLinkedList.getFirst().getOrg());
            }
        };
    }

    public static TranslateActionCreators getInstance() {
        if (instance == null) {
            synchronized (Dispatcher.class) {
                if (instance == null)
                    instance = new TranslateActionCreators();
            }
        }
        return instance;
    }

    public void localTranslate(final String word){

        if(Utils.isEmpty(word)){
            translateBeanLinkedList.clear();
            lightTimer.stop();
            return;
        }

        if(!Utils.isLetterDigitOrChinese(word)){
            sendEmptyMethod();
            return;
        }
        translateBean = new TranslateBean();
        translateBean.setOrg(word);
        translateBean.setAddTime(System.currentTimeMillis());
        translateBean.setIsTranslate(false);

        if(translateBeanLinkedList.size()>0) {

            TranslateBean oldTranslate = translateBeanLinkedList.getFirst();
            if(translateBean.getAddTime()-oldTranslate.getAddTime() < 300){
                translateBeanLinkedList.addFirst(translateBean);
                lightTimer.reStartTimerDelay(300);
            }else{
                TranslateMethod(translateBean.getOrg());
            }
        }else {
            TranslateMethod(translateBean.getOrg());
        }
    }

    public void TransLate(final String inputWord){

        TranslateProtocol.youdaoTransLate(inputWord, new HttpCallBack() {
            @Override
            public void onGeneralSuccess(String result, long flag) {

                LogUtils.e(result);
                dictBean = gson.fromJson(result, DictBean.class);
                dispatcher.dispatch(TodoConstants.TODO_TRANSLATE, TodoConstants.KEY_TRANSLATE, dictBean
                );
            }

            @Override
            public void onGeneralError(String e, long flag) {

            }
        });
    }

    public void TranslateMethod(String inputWord){

        translateBeanLinkedList.clear();
        translateBeanLinkedList.addFirst(translateBean);

        List<DictBean> dictBeans = distWorker.queryWord(inputWord);
        dispatcher.dispatch(TodoConstants.TODO_LOC_TRANSLATE, TodoConstants.KEY_LOC_TRANSLATE, dictBeans);
    }

    public void sendEmptyMethod(){
        dispatcher.dispatch(TodoConstants.TODO_LOC_TRANSLATE);

    }

    @Override
    public void destory(){
        instance = null;
        System.gc();
    }
}
