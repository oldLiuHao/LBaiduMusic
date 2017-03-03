package com.a3g.lanou.lbaidumusic.myinterface;

import okhttp3.Callback;

/**
 * Created by liuHao on 17/2/10.
 */
public interface CallBack<T> {

    void onSucced(T response);

    void onFailure(Throwable e);


}
