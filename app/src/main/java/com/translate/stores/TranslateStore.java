package com.translate.stores;

import com.squareup.otto.Subscribe;
import com.translate.actions.Action;
import com.translate.bean.DictBean;
import com.translate.conf.TodoConstants;
import com.translate.dispatcher.Dispatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongZ on 15/10/4.
 */
public class TranslateStore extends Store {


    private static TranslateStore instance;
    private List<DictBean> dictBeans;
//    private final List<Todo> todos;
//    private Todo lastDeleted;

    public List<DictBean> getDictBeans(){
        return dictBeans;
    }


    protected TranslateStore(Dispatcher dispatcher) {
        super(dispatcher);
        dictBeans = new ArrayList<>();
    }

    public static TranslateStore get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new TranslateStore(dispatcher);
        }

        return instance;
    }

    @Override
    StoreChangeEvent changeEvent() {
        return new TranslateStoreChangeEvent();
    }

    @Subscribe
    @Override
    public void onAction(Action action) {

        long id;
        switch (action.getType()) {
            case TodoConstants.TODO_TRANSLATE:
                queryOnline((DictBean) action.getData().get((TodoConstants.KEY_TRANSLATE)));
                emitStoreChange();
                break;
            case TodoConstants.TODO_LOC_TRANSLATE:
                queryResult((List<DictBean>) action.getData().get(TodoConstants.KEY_LOC_TRANSLATE));
                emitStoreChange();
                break;
        }
    }

    public void queryOnline(DictBean dictBean){
        this.dictBeans.clear();
        this.dictBeans.add(dictBean);
    }

    public void queryResult(List<DictBean> dictBeans){
        this.dictBeans.clear();
        this.dictBeans.addAll(dictBeans);
    }


   public class TranslateStoreChangeEvent implements StoreChangeEvent{
        public boolean isLocTranslate = true;
    }
}
