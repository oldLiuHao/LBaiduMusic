package com.a3g.lanou.lbaidumusic.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.LoginFragment;
import com.a3g.lanou.lbaidumusic.fragment.MainFragment;
import com.a3g.lanou.lbaidumusic.myinterface.JumpInter;
import com.a3g.lanou.lbaidumusic.myinterface.MusicInterface;
import com.a3g.lanou.lbaidumusic.service.MediaPlayService;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicInterface {
    private LinearLayout lilaPlay;
    private ImageView ivIconMain, ivPlayMain, ivNext, ivMenu;
    private TextView tvSongNameMain, tvSingerMain;
    private FragmentManager fragmentManager;
    private LoginFragment loginFragment;
    private MediaPlayService.MyBinder myBinder;
    //绑定服务用的
    private Intent intent;
    private ServiceConnection serviceConnection;
    private SongReceiver songReceiver;
    private boolean isAlive = true;
    private ArrayList<SongBean> localSongBeen;
    private static final String TAG = "MainActivity";

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ivIconMain = bindView(R.id.iv_icon_main);
        ivPlayMain = bindView(R.id.iv_play_main);
        tvSongNameMain = bindView(R.id.tv_song_name_main);
        tvSingerMain = bindView(R.id.tv_singer_name_main);
        ivNext = bindView(R.id.iv_next_main);
        ivMenu = (ImageView) findViewById(R.id.iv_menu_main);
        lilaPlay = (LinearLayout) findViewById(R.id.lila_play_main);
    }

    @Override
    protected void initData() {
        //加载MainFragment
        fragmentManager = getSupportFragmentManager();
        toMainFragment();
        ivPlayMain.setImageResource(R.mipmap.bt_minibar_play_normal);


        loginFragment = new LoginFragment();

        intent = new Intent(this, MediaPlayService.class);
        //开辟一个子线程，在子线程中无限次循环，循环中要做的就是获得MediaPlayer播放的歌曲的总长度以及当前歌曲的播放进度
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (isAlive){
//                    if (myBinder!=null&&myBinder.isPlaying()){
//                        //获取值的过程可以在子线程操作
//                        //但是更改UI的操作需要在主线程执行
//                        //RunOnui方法，就可以调到主线程中执行run方法里面的内容
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//
//
//                    }
//                }
//            }
//        })
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MediaPlayService.MyBinder) service;
                localSongBeen = myBinder.getSongList();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        //绑定服务
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        //注册广播接收器
        IntentFilter intentFilter = new IntentFilter(MyBean.MY_SONG);
        songReceiver = new SongReceiver();
        registerReceiver(songReceiver, intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(songReceiver);
        unbindService(serviceConnection);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAlive = false;
    }

    private void toMainFragment() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.frame_layout_main, mainFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void bindEvent() {
        ivPlayMain.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        lilaPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_main:
                if (myBinder.isFirst()) {
                    Toast.makeText(this, "无可播放歌曲", Toast.LENGTH_SHORT).show();
                } else {
                    if (myBinder.isPlaying()) {
                        myBinder.pause();
                        ivPlayMain.setImageResource(R.mipmap.bt_minibar_play_normal);
                    } else {

                        myBinder.continuePlay();
                        ivPlayMain.setImageResource(R.mipmap.bt_minibar_pause_normal);
                    }
                }

                break;
            case R.id.iv_next_main:
                myBinder.playNext();
                myBinder.getIndex();
                Intent intent = new Intent(MyBean.PLAY_LOCAL_INDEX);
                intent.putExtra(MyBean.PLAY_LOCAL_INDEX, myBinder.getIndex());
                sendBroadcast(intent);
                break;
            case R.id.lila_play_main:
                if (!myBinder.isFirst()){
                    Intent toPlay = new Intent(this,PlayActivity.class);
                    myBinder.getIndex();
                    myBinder.getSongList();
                    startActivity(toPlay);
                }


        }
    }

    @Override
    public void playMusic(int position) {
        myBinder.play(position);
    }


    class SongReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            SongBean songBean = intent.getParcelableExtra(MyBean.MY_SONG);
            tvSongNameMain.setText(songBean.getSongName());
            tvSingerMain.setText(songBean.getSingerName());
            ivPlayMain.setImageResource(R.mipmap.bt_minibar_pause_normal);

        }
    }


}
