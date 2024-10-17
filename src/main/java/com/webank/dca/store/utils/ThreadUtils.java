package com.webank.dca.store.utils;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,50,1000L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>()
    );

    private ThreadUtils(){}

    public static ThreadPoolExecutor getThreadExecutor(){
        return executor;
    }
}
