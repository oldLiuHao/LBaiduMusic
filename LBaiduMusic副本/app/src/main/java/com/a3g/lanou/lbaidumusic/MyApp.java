package com.a3g.lanou.lbaidumusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuHao on 17/2/23.
 */
public class MyApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context  = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
