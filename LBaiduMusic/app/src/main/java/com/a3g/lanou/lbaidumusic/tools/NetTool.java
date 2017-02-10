package com.a3g.lanou.lbaidumusic.tools;

import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.myinterface.NetInterface;

/**
 * Created by liuHao on 17/2/10.
 */
public class NetTool implements NetInterface {
    private static NetTool ourInstance;
    private NetInterface netInterface;

    public static NetTool getInstance() {
        if (ourInstance == null) {
            synchronized (NetTool.class) {
                if (ourInstance == null) {
                    ourInstance = new NetTool();
                }
            }


        }


        return ourInstance;
    }

    private NetTool() {
        netInterface = new OKHttpTool();
    }


    @Override
    public void startRequest(String url, CallBack<String> callBack) {
        netInterface.startRequest(url, callBack);
    }

    @Override
    public <T> void startRequset(String url, Class<T> tClass, CallBack<T> callBack) {
        netInterface.startRequset(url, tClass, callBack);
    }
}
