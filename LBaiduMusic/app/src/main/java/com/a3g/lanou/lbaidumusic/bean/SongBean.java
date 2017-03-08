package com.a3g.lanou.lbaidumusic.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuHao on 17/2/23.
 */
public class SongBean  implements Parcelable{
    private String songName;
    private  String singerName;
    private String url;
    private Bitmap songImage;
    private Long duringTime;
    private String id;

    public SongBean() {
    }

    public SongBean(String songName, String singerName, String url, Bitmap songImage, Long duringTime) {
        this.songName = songName;
        this.singerName = singerName;
        this.url = url;
        this.songImage = songImage;
        this.duringTime = duringTime;
    }

    public SongBean(String songName, String singerName, String url, Bitmap songImage, Long duringTime, String id) {
        this.songName = songName;
        this.singerName = singerName;
        this.url = url;
        this.songImage = songImage;
        this.duringTime = duringTime;
        this.id = id;
    }

    protected SongBean(Parcel in) {
        songName = in.readString();
        singerName = in.readString();
        url = in.readString();
        songImage = in.readParcelable(Bitmap.class.getClassLoader());
        id = in.readString();
    }

    public static final Creator<SongBean> CREATOR = new Creator<SongBean>() {
        @Override
        public SongBean createFromParcel(Parcel in) {
            return new SongBean(in);
        }

        @Override
        public SongBean[] newArray(int size) {
            return new SongBean[size];
        }
    };

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getSongImage() {
        return songImage;
    }

    public void setSongImage(Bitmap songImage) {
        this.songImage = songImage;
    }

    public Long getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(Long duringTime) {
        this.duringTime = duringTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeString(singerName);
        dest.writeString(url);
        dest.writeParcelable(songImage, flags);
        dest.writeString(id);
    }
}
