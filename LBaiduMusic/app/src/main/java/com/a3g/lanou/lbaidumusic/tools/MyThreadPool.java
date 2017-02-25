package com.a3g.lanou.lbaidumusic.tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuHao on 17/2/17.
 */
public class MyThreadPool {
    private static ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolExecutor getInstence(){

        if (threadPoolExecutor==null){
            synchronized (MyThreadPool.class){
                if (threadPoolExecutor==null){
                    threadPoolExecutor = new ThreadPoolExecutor(10,Integer.MAX_VALUE,30, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
                }

            }

        }
        return threadPoolExecutor;






}
}