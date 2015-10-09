package com.translate.actions;

import com.apkfuns.logutils.LogUtils;
import com.translate.conf.TodoConstants;
import com.translate.connector.HttpCallBack;
import com.translate.connector.protocol.TranslateProtocol;
import com.translate.dispatcher.Dispatcher;

/**
 * Created by DongZ on 15/10/4.
 */
public class TranslateActionCreators extends ActionsCreator{


    private static TranslateActionCreators instance;

    TranslateActionCreators() {
        super();
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

    public void TransLate(final String eng){

        TranslateProtocol.youdaoTransLate(eng, new HttpCallBack() {
            @Override
            public void onGeneralSuccess(String result, long flag) {

                LogUtils.e(result);

                dispatcher.dispatch(TodoConstants.TODO_TRANSLATE,TodoConstants.KEY_TRANSLATE,eng

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
