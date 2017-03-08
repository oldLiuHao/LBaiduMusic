package com.a3g.lanou.lbaidumusic;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liuHao on 17/3/4.
 */
@Entity
public class Collect implements Parcelable{
  @Id
    private Long id;
    private String songName,author,image,songId;
    @Generated(hash = 1773655478)
    public Collect(Long id, String songName, String author, String image,
            String songId) {
        this.id = id;
        this.songName = songName;
        this.author = author;
        this.image = image;
        this.songId = songId;
    }
    @Generated(hash = 1726975718)
    public Collect() {
    }

    protected Collect(Parcel in) {
        songName = in.readString();
        author = in.readString();
        image = in.readString();
        songId = in.readString();
    }

    public static final Creator<Collect> CREATOR = new Creator<Collect>() {
        @Override
        public Collect createFromParcel(Parcel in) {
            return new Collect(in);
        }

        @Override
        public Collect[] newArray(int size) {
            return new Collect[size];
        }
    };

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSongName() {
        return this.songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getSongId() {
        return this.songId;
    }
    public void setSongId(String songId) {
        this.songId = songId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(songName);
        dest.writeString(author);
        dest.writeString(image);
        dest.writeString(songId);
    }
}
