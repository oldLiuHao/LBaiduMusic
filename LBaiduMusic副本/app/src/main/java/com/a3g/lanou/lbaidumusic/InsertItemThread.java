package com.a3g.lanou.lbaidumusic;

import android.os.Handler;
import android.util.Log;

/**
 * Created by liuHao on 17/2/17.
 */
public class InsertItemThread extends Thread{


    private Handler handler;
    private final int WHAT=100;
    private static final String TAG = "InsertItemThread";
    private boolean isStart;

    public void setStart(boolean start) {
        isStart = start;
    }

    public InsertItemThread(Handler handler) {
        this.handler = handler;
        isStart = true;
    }


    @Override
    public void run() {
        super.run();
        while (isStart){


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.sendEmptyMessage(WHAT);
            Log.e(TAG, "run: "+1231321313);
        }
    }
    }





