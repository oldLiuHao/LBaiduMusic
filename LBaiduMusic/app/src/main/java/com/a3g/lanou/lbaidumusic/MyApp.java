package com.a3g.lanou.lbaidumusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuHao on 17/2/23.
 */
public class MyApp extends Application {

    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context  = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"MusicCollect.db",null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoMaster==null){
            if (daoSession==null){
                daoMaster = getDaoMaster();
            }
        }
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
