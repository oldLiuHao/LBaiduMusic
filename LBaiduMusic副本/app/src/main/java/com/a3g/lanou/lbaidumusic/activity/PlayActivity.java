package com.a3g.lanou.lbaidumusic.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.MusicPagerAdapter;
import com.a3g.lanou.lbaidumusic.bean.OnLineSongBean;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.InformationFragment;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.LyricFragment;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.MiddlePlayFragment;
import com.a3g.lanou.lbaidumusic.service.MediaPlayService;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.qiujuer.genius.blur.StackBlur;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/25.
 */
public class PlayActivity extends BaseActivity {
    private ViewPager viewPager;
    private MusicPagerAdapter musicPagerAdapter;
    private List<Fragment> fragments;
    private ImageView ivBack, ivPlay, ivNext, ivLast, ivMode, ivMenu;
    private ServiceConnection serviceConnection;
    private MediaPlayService.MyBinder myBinder;
    private PlaySongReceiver playSongReceiver;
    private SeekBar sbPlay;
    private boolean isAlive = true;
    private TextView tvTimeNow, tvTimeAll;
    private LinearLayout linearLayout;
    private final int PLAY_ON_LINE = 1;
    private final int PLAY_LOCAL = 2;

    private static final String TAG = "PlayActivity";

    @Override
    protected int bindLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_activity_play);
        ivBack = (ImageView) findViewById(R.id.iv_back_play_activity);
        sbPlay = (AppCompatSeekBar) findViewById(R.id.sb_play);
        tvTimeNow = (TextView) findViewById(R.id.tv_time_now);
        tvTimeAll = (TextView) findViewById(R.id.tv_time_all);
        ivPlay = (ImageView) findViewById(R.id.iv_play_play);
        ivMode = (ImageView) findViewById(R.id.iv_mode_play);
        ivLast = (ImageView) findViewById(R.id.iv_last_play);
        ivNext = (ImageView) findViewById(R.id.iv_next_play);
        ivMenu = (ImageView) findViewById(R.id.iv_menu_play);
        linearLayout = (LinearLayout) findViewById(R.id.lila_play_activity);
    }

    @Override
    protected void initData() {
        musicPagerAdapter = new MusicPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicPagerAdapter);
        fragments = new ArrayList<>();

        viewPager.setCurrentItem(1);


        IntentFilter intentFilter = new IntentFilter(MyBean.MY_SONG);
        playSongReceiver = new PlaySongReceiver();
        registerReceiver(playSongReceiver, intentFilter);

        Intent intent = new Intent(this, MediaPlayService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isAlive) {

                    if (myBinder != null && myBinder.isPlaying()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sbPlay.setMax(myBinder.getDu());
                                sbPlay.setProgress(myBinder.getCurrentProgress());
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                                String time = simpleDateFormat.format(myBinder.getCurrentProgress());
                                tvTimeNow.setText(time);
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



                if (myBinder.isPlaying()) {
                    ivPlay.setImageResource(R.mipmap.bt_playpage_button_pause_normal_new);
                } else ivPlay.setImageResource(R.mipmap.bt_playpage_button_play_normal_new);
                //显示时长
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                String time = simpleDateFormat.format(myBinder.getDu());
                tvTimeAll.setText(time);
                //创建frgment
                Bundle bundle = new Bundle();
                InformationFragment informationFragment = new InformationFragment();
                MiddlePlayFragment middlePlayFragment = new MiddlePlayFragment();
                LyricFragment lyricFragment = new LyricFragment();
                //判断是网络歌曲还是本地歌曲
                if (myBinder.getPlayWhere() == PLAY_LOCAL) {
                    //设置背景
                    Bitmap bitmap = StackBlur.blur(myBinder.getSongBean().getSongImage(),20,false);


                    bundle.putParcelable(MyBean.MY_SONG, myBinder.getSongBean());
                    informationFragment.setArguments(bundle);
                    middlePlayFragment.setArguments(bundle);
                    lyricFragment.setArguments(bundle);

                } else if (myBinder.getPlayWhere() == PLAY_ON_LINE) {
                    //设置Activity背景
                    //转换成Bitmap
                    SimpleTarget target = new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            //转换成模糊图
                            Bitmap bitmap = StackBlur.blur(resource,20,false);

                            linearLayout.setBackground(new BitmapDrawable(bitmap));
                            Log.e(TAG, "onResourceReady: " );
                        }
                    };
                    Glide.with(PlayActivity.this).load(myBinder.getOnLineSong().getSonginfo().getPic_premium()).asBitmap().into( target );
                    bundle.putParcelable(MyBean.MY_ONLINE_SONG, myBinder.getOnLineSong());
                    informationFragment.setArguments(bundle);
                    middlePlayFragment.setArguments(bundle);
                    lyricFragment.setArguments(bundle);
                }


                fragments.add(informationFragment);
                fragments.add(middlePlayFragment);
                fragments.add(lyricFragment);
                musicPagerAdapter.setFragments(fragments);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAlive = false;
        unbindService(serviceConnection);
        unregisterReceiver(playSongReceiver);


    }

    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivMode.setOnClickListener(this);
        ivLast.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        sbPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myBinder.setProgress(seekBar.getProgress());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_play_activity:
                finish();
                break;
            case R.id.iv_play_play:
                if (myBinder.isPlaying()) {
                    myBinder.pause();
                    ivPlay.setImageResource(R.mipmap.bt_playpage_button_play_normal_new);
                } else {
                    myBinder.continuePlay();
                    ivPlay.setImageResource(R.mipmap.bt_playpage_button_pause_normal_new);
                }
                break;
            case R.id.iv_next_play:
                myBinder.playNext();
                break;
            case R.id.iv_last_play:
                myBinder.playLast();
                break;
            case R.id.iv_mode_play:
                myBinder.nextMode();
                switch (myBinder.getMode()) {
                    case 0:
                        Toast.makeText(this, "单曲循环", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(this, "循环播放", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
                        break;
                }

        }
    }

    class PlaySongReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            String time = simpleDateFormat.format(myBinder.getDu());
            tvTimeAll.setText(time);

            if (myBinder.getPlayWhere() == PLAY_LOCAL) {
                SongBean songBean = intent.getParcelableExtra(MyBean.MY_SONG);
                linearLayout.setBackground(new BitmapDrawable(songBean.getSongImage()));
                EventBus.getDefault().post(songBean);

            } else if (myBinder.getPlayWhere() == PLAY_ON_LINE) {
                SimpleTarget target = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        //转换成模糊图
                        Bitmap bitmap = StackBlur.blur(resource,20,false);

                        linearLayout.setBackground(new BitmapDrawable(bitmap));
                        Log.e(TAG, "onResourceReady: " );
                    }
                };

                OnLineSongBean onLineSongBean = intent.getParcelableExtra(MyBean.MY_SONG);
                Glide.with(PlayActivity.this).load(onLineSongBean.getSonginfo().getPic_premium()).asBitmap().into( target );
                EventBus.getDefault().post(onLineSongBean);

            }


        }
    }


}
