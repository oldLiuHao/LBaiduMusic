package com.a3g.lanou.lbaidumusic.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/23.
 */
public class MediaPlayService extends Service {


    private MediaPlayer mediaPlayer;
    private ArrayList<SongBean> songBeen;
    private int index;
    private MyBinder myBinder;
    private int playMode = 1;
    private int number;
    private boolean isFirst = true;
    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        //初始化MediaPlayer对象
        mediaPlayer = new MediaPlayer();
        //初始化音乐信息集合
        songBeen = new ArrayList<>();
        //设置对mediaPlayer完成播放的监听
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (playMode==0){
                    mediaPlayer.reset();
                    myBinder.play();
                }else if (playMode == 1){
                    index++;
                    mediaPlayer.reset();
                    myBinder.play();
                }else if (playMode==2){
                    //获得一个随机数并且不和index相等
                    do {
                        number = (int) (Math.random()*(songBeen.size()-1));
                    }while (index == number);
                    index = number;
                    mediaPlayer.reset();
                    myBinder.play();
                }
            }
        });
        //获取音乐
        getMusicData();
    }

    private void getMusicData() {
        //根据uri找到所有的音频信息
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null,null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                //获取音乐名字
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //歌手名字
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                //获取歌曲的url
                String url =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                //获取音乐类型0代表不是音乐
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                //获取音乐时长
                Long duringTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                if(isMusic!=0&&duringTime/(60*1000)>1){
                    SongBean songBean = new SongBean(title,singer,url);
                    songBeen.add(songBean);
                }
            }while (cursor.moveToNext());
//            for (SongBean songBean:songBeen){
//
//            }
        }
        cursor.close();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.reset();
        mediaPlayer.release();

        return super.onUnbind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }





    public class MyBinder extends Binder{
        public MyBinder() {
        }
        public  void play(){
            if (songBeen!=null){
                if (index==songBeen.size()){
                    index=0;
                }
            SongBean songBean = songBeen.get(index);
            try {
                //设置要播放的音乐资源，这个地址是内容是从提供者获得的
                mediaPlayer.setDataSource(songBean.getUrl());
                //准备
                mediaPlayer.prepare();
                //播放
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
                isFirst = false;
            Intent intent = new Intent(MyBean.MY_SONG);
            intent.putExtra(MyBean.MY_SONG,songBean);
            sendBroadcast(intent);
            }
        }
        public  void play(int index){
            if (isFirst){


            if (songBeen!=null){
                if (index==songBeen.size()){
                    index=0;
                }
                SongBean songBean = songBeen.get(index);
                try {
                    //设置要播放的音乐资源，这个地址是内容是从提供者获得的
                    mediaPlayer.setDataSource(songBean.getUrl());
                    //准备
                    mediaPlayer.prepare();
                    //播放
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MyBean.MY_SONG);
                intent.putExtra(MyBean.MY_SONG,songBean);
                sendBroadcast(intent);
                isFirst = false;
            }
            }else {
                mediaPlayer.reset();
                SongBean songBean = songBeen.get(index);
                try {
                    //设置要播放的音乐资源，这个地址是内容是从提供者获得的
                    mediaPlayer.setDataSource(songBean.getUrl());
                    //准备
                    mediaPlayer.prepare();
                    //播放
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MyBean.MY_SONG);
                intent.putExtra(MyBean.MY_SONG,songBean);
                sendBroadcast(intent);
            }
        }

        public boolean isPlaying(){
            return mediaPlayer.isPlaying();
        }

        public void pause(){
            mediaPlayer.pause();
        }

        public void continuePlay(){
            mediaPlayer.start();
        }

        public boolean isFirst(){
            return isFirst;
        }

        public void setFirst(){
            isFirst = false;
        }

        public void  playLast(){
            if (playMode == 1 || playMode ==0){
                index--;
                if (index <0){
                    index = songBeen.size()-1;

                }
                mediaPlayer.reset();
                play();

            }else  if (playMode == 2){
                do {
                    number = (int) (Math.random()*(songBeen.size()-1));
                }while (index==number);
                index = number;
                mediaPlayer.reset();
                play();

            }

        }
        public void  playNext(){
            if (playMode == 0 || playMode ==1){
                index++;
                if (index>=songBeen.size()){
                    index=0;
                }
                mediaPlayer.reset();
                play();
            }else if (playMode==2){
                do {
                    number = (int) (Math.random()*(songBeen.size()-1));
                }while (index == number);
                index = number;
                mediaPlayer.reset();
                play();
            }


        }
        //得到当前歌曲时间
        public  int getDu(){
            return mediaPlayer.getDuration();

        }

        public int getCurrentProgress(){
            return  mediaPlayer.getCurrentPosition();

        }

        public void setMediaProgress(int progress){
            mediaPlayer.seekTo(progress);

        }

        public void  randomMode(){
            playMode=2;
        }

        public void  repeatMode(){
            playMode=1;
        }
        public void  singleMode(){
            playMode=0;
        }
        public ArrayList<SongBean>  getSongList(){
            return songBeen;
        }

        public int getIndex(){
            return index;

        }

    }

}
