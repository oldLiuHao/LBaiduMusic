package com.a3g.lanou.lbaidumusic.bean;

import java.util.List;

/**
 * Created by liuHao on 17/2/23.
 */
public class PlaySongListBean {




    private int error_code;
    private String listid;
    private String title;
    private String pic_300;
    private String pic_500;
    private String pic_w700;
    private String width;
    private String height;
    private String listenum;
    private String collectnum;
    private String tag;
    private String desc;
    private String url;
    private List<ContentBean> content;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_300() {
        return pic_300;
    }

    public void setPic_300(String pic_300) {
        this.pic_300 = pic_300;
    }

    public String getPic_500() {
        return pic_500;
    }

    public void setPic_500(String pic_500) {
        this.pic_500 = pic_500;
    }

    public String getPic_w700() {
        return pic_w700;
    }

    public void setPic_w700(String pic_w700) {
        this.pic_w700 = pic_w700;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getListenum() {
        return listenum;
    }

    public void setListenum(String listenum) {
        this.listenum = listenum;
    }

    public String getCollectnum() {
        return collectnum;
    }

    public void setCollectnum(String collectnum) {
        this.collectnum = collectnum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * title : Can You Feel My World
         * song_id : 10510312
         * author : 王力宏
         * album_id : 10404956
         * album_title : 火力全开
         * relate_status : 0
         * is_charge : 1
         * all_rate : 24,64,128,192,256,320,flac
         * high_rate : 320
         * all_artist_id : 141
         * copy_type : 1
         * has_mv : 1
         * toneid : 0
         * resource_type : 0
         * is_ksong : 1
         * resource_type_ext : 0
         * versions :
         * bitrate_fee : {"0":"129|-1","1":"-1|-1"}
         * has_mv_mobile : 1
         * ting_uid : 1107
         * is_first_publish : 0
         * havehigh : 2
         * charge : 0
         * learn : 1
         * song_source : web
         * piao_id : 0
         * korean_bb_song : 0
         * mv_provider : 0100000000
         * share : http://music.baidu.com/song/10510312
         */

        private String title;
        private String song_id;
        private String author;
        private String album_id;
        private String album_title;
        private String relate_status;
        private String is_charge;
        private String all_rate;
        private String high_rate;
        private String all_artist_id;
        private String copy_type;
        private int has_mv;
        private String toneid;
        private String resource_type;
        private String is_ksong;
        private String resource_type_ext;
        private String versions;
        private String bitrate_fee;
        private int has_mv_mobile;
        private String ting_uid;
        private int is_first_publish;
        private int havehigh;
        private int charge;
        private int learn;
        private String song_source;
        private String piao_id;
        private String korean_bb_song;
        private String mv_provider;
        private String share;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public String getIs_charge() {
            return is_charge;
        }

        public void setIs_charge(String is_charge) {
            this.is_charge = is_charge;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public String getHigh_rate() {
            return high_rate;
        }

        public void setHigh_rate(String high_rate) {
            this.high_rate = high_rate;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public int getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(int has_mv) {
            this.has_mv = has_mv;
        }

        public String getToneid() {
            return toneid;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public String getResource_type() {
            return resource_type;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public String getIs_ksong() {
            return is_ksong;
        }

        public void setIs_ksong(String is_ksong) {
            this.is_ksong = is_ksong;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public int getIs_first_publish() {
            return is_first_publish;
        }

        public void setIs_first_publish(int is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public int getLearn() {
            return learn;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public String getPiao_id() {
            return piao_id;
        }

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public String getMv_provider() {
            return mv_provider;
        }

        public void setMv_provider(String mv_provider) {
            this.mv_provider = mv_provider;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }
    }
}
