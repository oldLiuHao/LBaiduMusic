package com.a3g.lanou.lbaidumusic.myinterface;

/**
 * Created by liuHao on 17/2/10.
 */
public interface NetInterface {

    void startRequest(String url,CallBack<String> callBack);
    <T> void startRequset(String url,Class<T> tClass,CallBack<T> callBack);

}
