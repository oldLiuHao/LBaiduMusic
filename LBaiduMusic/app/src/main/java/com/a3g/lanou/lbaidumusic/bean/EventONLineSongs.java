package com.a3g.lanou.lbaidumusic.bean;

import java.util.List;

/**
 * Created by liuHao on 17/3/1.
 */
public class EventONLineSongs {
    private List<OnLineSongBean> onLineSongBeanList;
    private int postion;

    public EventONLineSongs() {
    }

    public EventONLineSongs(List<OnLineSongBean> onLineSongBeanList, int postion) {
        this.onLineSongBeanList = onLineSongBeanList;
        this.postion = postion;
    }

    public List<OnLineSongBean> getOnLineSongBeanList() {
        return onLineSongBeanList;
    }

    public void setOnLineSongBeanList(List<OnLineSongBean> onLineSongBeanList) {
        this.onLineSongBeanList = onLineSongBeanList;
    }

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }
}
