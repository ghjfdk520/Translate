package com.translate.utils;

/**
 * Created by DongZ on 2015/10/8 0008.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler;
    private Thread.UncaughtExceptionHandler defaultHandler;


    public CrashHandler() {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        defaultHandler.uncaughtException(thread, ex);
    }
}
