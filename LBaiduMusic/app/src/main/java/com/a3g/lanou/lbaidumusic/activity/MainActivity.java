package com.a3g.lanou.lbaidumusic.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.EventONLineSongs;
import com.a3g.lanou.lbaidumusic.bean.OnLineSongBean;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.MainFragment;
import com.a3g.lanou.lbaidumusic.myinterface.MusicInterface;
import com.a3g.lanou.lbaidumusic.service.MediaPlayService;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicInterface {
    private LinearLayout lilaPlay;
    private ImageView ivIconMain, ivPlayMain, ivNext, ivMenu;
    private TextView tvSongNameMain, tvSingerMain;
    private FragmentManager fragmentManager;
    private MediaPlayService.MyBinder myBinder;
    //绑定服务用的
    private Intent intent;
    private ServiceConnection serviceConnection;
    private SongReceiver songReceiver;
    private boolean isAlive = true;
    private ArrayList<SongBean> localSongBeen;
    private static final String TAG = "MainActivity";
    private List<OnLineSongBean> onLineSongBeanList;
    private final int PLAY_ON_LINE = 1;
    private final int PLAY_LOCAL = 2;
    private ProgressBar progressBar;
    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private RemoteViews remoteViews;
    private OnLineSongBean onLineSongBean;
    private boolean ontificationPlay = true;

    @Override
    protected int bindLayout() {
        EventBus.getDefault().register(this);
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
        progressBar = (ProgressBar) findViewById(R.id.pb_main);
    }

    @Override
    protected void initData() {
        //加载notification
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(MainActivity.this);
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);



        //加载MainFragment
        fragmentManager = getSupportFragmentManager();
        toMainFragment();
        ivPlayMain.setImageResource(R.mipmap.bt_minibar_play_normal);




        intent = new Intent(this, MediaPlayService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAlive) {

                    if (myBinder != null && myBinder.isPlaying()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setMax(myBinder.getDu());
                                progressBar.setProgress(myBinder.getCurrentProgress());

                            }
                        });

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
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
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyBean.MY_SONG);

        intentFilter.addAction("last");
        intentFilter.addAction("play");
        intentFilter.addAction("next");
        songReceiver = new SongReceiver();
        registerReceiver(songReceiver, intentFilter);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                checkedPlay();

                break;
            case R.id.iv_next_main:
                myBinder.playNext();
                if (myBinder.getPlayWhere()==PLAY_LOCAL){
                    Intent intent = new Intent(MyBean.PLAY_LOCAL_INDEX);
                    intent.putExtra(MyBean.PLAY_LOCAL_INDEX, myBinder.getIndex());
                    sendBroadcast(intent);
                }

                break;
            case R.id.lila_play_main:
                if (!myBinder.isFirst()){
                    Intent toPlay = new Intent(this,PlayActivity.class);
                    startActivity(toPlay);
                }


        }
    }

    private void checkedPlay() {
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
    }

    @Override
    public void playMusic(int position) {
        myBinder.play(position);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOnLineSong(EventONLineSongs eventONLineSongs){

        if (eventONLineSongs.getOnLineSongBeanList()!=null){
            onLineSongBeanList = eventONLineSongs.getOnLineSongBeanList();
            int position = eventONLineSongs.getPostion();
            myBinder.playOnline(onLineSongBeanList,position);
        }else {
            myBinder.playOnline(eventONLineSongs.getPostion());
        }


    }





    class SongReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

          if (intent.getAction().equals(MyBean.MY_SONG)){

            ivPlayMain.setImageResource(R.mipmap.bt_minibar_pause_normal);
            if(myBinder.getPlayWhere()==PLAY_LOCAL){
                SongBean songBean = intent.getParcelableExtra(MyBean.MY_SONG);
                tvSongNameMain.setText(songBean.getSongName());
                tvSingerMain.setText(songBean.getSingerName());
                remoteViews.setTextViewText(R.id.tv_name_notification,songBean.getSongName());
                remoteViews.setTextViewText(R.id.tv_author_notification,songBean.getSingerName());
                if (songBean.getSongImage()==null){
                    ivIconMain.setImageResource(R.mipmap.ic_aboutus_logo);
                    remoteViews.setImageViewResource(R.id.iv_icon_notification,R.mipmap.ic_aboutus_logo);
                }
                else {
                    ivIconMain.setImageBitmap(songBean.getSongImage());
                    remoteViews.setImageViewBitmap(R.id.iv_icon_notification,songBean.getSongImage());
                }
            }else if (myBinder.getPlayWhere()==PLAY_ON_LINE){
                onLineSongBean = intent.getParcelableExtra(MyBean.MY_SONG);
                tvSongNameMain.setText(onLineSongBean.getSonginfo().getTitle());
                tvSingerMain.setText(onLineSongBean.getSonginfo().getAuthor());


                SimpleTarget<Bitmap> target =  new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        remoteViews.setImageViewBitmap(R.id.iv_icon_notification,resource);
                        ivIconMain.setImageBitmap(resource);
                    }
                };
         Glide.with(MainActivity.this).load(onLineSongBean.getSonginfo().getPic_premium()).asBitmap().into(target);
//                Glide.with(MainActivity.this).load(onLineSongBean.getSonginfo().getPic_premium()).into(ivIconMain);


                remoteViews.setTextViewText(R.id.tv_name_notification, onLineSongBean.getSonginfo().getTitle());
                remoteViews.setTextViewText(R.id.tv_author_notification, onLineSongBean.getSonginfo().getAuthor());
            }
            Intent last = new Intent("last");
            PendingIntent lastPandingIntent = PendingIntent.getBroadcast(MainActivity.this,1,last,PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.iv_last_notification,lastPandingIntent);

            Intent playIntent = new Intent("play");
            PendingIntent playPandingIntent = PendingIntent.getBroadcast(MainActivity.this,2,playIntent,PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.iv_play_notification,playPandingIntent);


            Intent nextIntent  = new Intent("next");
            PendingIntent nextPandingIntent = PendingIntent.getBroadcast(MainActivity.this,3,nextIntent,PendingIntent.FLAG_CANCEL_CURRENT);
             remoteViews.setOnClickPendingIntent(R.id.iv_next_notification,nextPandingIntent);
              builder.setSmallIcon(R.mipmap.ic_aboutus_logo).setContent(remoteViews);
              notificationManager.notify(1,builder.build());
          }
            else if (intent.getAction().equals("last")){
              myBinder.playLast();
          }else if (intent.getAction().equals("next")){
              myBinder.playNext();
          }else if (intent.getAction().equals("play")){
              if (myBinder.isPlaying()){
                  remoteViews.setImageViewResource(R.id.iv_play_notification,R.mipmap.bt_playpage_button_play_normal_new);
                  notificationManager.notify(1,builder.build());
              }else {
                  remoteViews.setImageViewResource(R.id.iv_play_notification,R.mipmap.bt_playpage_button_pause_normal_new);
                  notificationManager.notify(1,builder.build());
              }
              checkedPlay();
          }
        }
    }


}
