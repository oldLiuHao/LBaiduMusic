package com.a3g.lanou.lbaidumusic.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuHao on 17/2/23.
 */
public class SongBean  implements Parcelable{
    private String songName;
    private  String singerName;
    private String url;

    public SongBean(String songName, String singerName, String url) {
        this.songName = songName;
        this.singerName = singerName;
        this.url = url;
    }

    public SongBean() {
    }

    protected SongBean(Parcel in) {
        songName = in.readString();
        singerName = in.readString();
        url = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeString(singerName);
        dest.writeString(url);
    }
}
