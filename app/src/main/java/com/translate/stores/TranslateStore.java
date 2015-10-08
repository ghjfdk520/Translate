package com.translate.stores;

import com.translate.actions.Action;
import com.translate.dispatcher.Dispatcher;

/**
 * Created by DongZ on 15/10/4.
 */
public class TranslateStore extends Store {


    private static TranslateStore instance;
//    private final List<Todo> todos;
//    private Todo lastDeleted;

    protected TranslateStore(Dispatcher dispatcher) {
        super(dispatcher);
       // todos = new ArrayList<>();
            }

    public static TranslateStore get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new TranslateStore(dispatcher);
        }

        return instance
                ;
    }

    @Override
    StoreChangeEvent changeEvent() {
        return null;
    }

    @Override
    public void onAction(Action action) {

    }
}
