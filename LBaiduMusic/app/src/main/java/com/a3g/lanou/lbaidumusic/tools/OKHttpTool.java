package com.a3g.lanou.lbaidumusic.tools;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.myinterface.NetInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuHao on 17/2/10.
 */
public class OKHttpTool implements NetInterface{
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Gson gson;

    public OKHttpTool() {
        gson = new Gson();
        okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true)
        .cache(new Cache(Environment.getExternalStorageDirectory(),10*1024*1024)).build();
    }


    @Override
    public void startRequest(String url, final CallBack<String> callBack) {
        final Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSucced(str);
                    }
                });
            }
        });
    }

    @Override
    public <T> void startRequset(String url, final Class<T> tClass, final CallBack<T> callBack) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       T result =  gson.fromJson(str,tClass);
                        callBack.onSucced(result);
                    }
                });
            }
        });

    }
}
