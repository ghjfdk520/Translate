package com.translate.actions;

import com.apkfuns.logutils.LogUtils;
import com.translate.bean.DictBean;
import com.translate.conf.TodoConstants;
import com.translate.connector.HttpCallBack;
import com.translate.connector.protocol.TranslateProtocol;
import com.translate.database.DistWorker;
import com.translate.dispatcher.Dispatcher;

import java.util.List;

/**
 * Created by DongZ on 15/10/4.
 */
public class TranslateActionCreators extends ActionsCreator{


    private DistWorker distWorker;

     private static TranslateActionCreators instance;

    TranslateActionCreators() {
        super();
        distWorker = new DistWorker();
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
        List<DictBean> dictBeans = distWorker.queryWord(word);
        dispatcher.dispatch(TodoConstants.TODO_LOC_TRANSLATE,TodoConstants.KEY_LOC_TRANSLATE,dictBeans);
    }

    public void TransLate(final String inputWord){

        TranslateProtocol.youdaoTransLate(inputWord, new HttpCallBack() {
            @Override
            public void onGeneralSuccess(String result, long flag) {

                LogUtils.e(result);

                dispatcher.dispatch(TodoConstants.TODO_TRANSLATE,TodoConstants.KEY_TRANSLATE,inputWord

                );
            }

            @Override
            public void onGeneralError(String e, long flag) {

            }
        });
    }

    @Override
    public void destory(){
        instance = null;
        System.gc();
    }
}
