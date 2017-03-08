package com.a3g.lanou.lbaidumusic.tools;

/**
 * Created by liuHao on 17/2/13.
 */
public class MyUrl {

    public static final String BASE_URL = "";

    public static final String SONG_LIST_HOT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=aUeJzjQd0Bxv60bsMl1nzvktSPLjc5EcGxFAt77r3ORvfYOi0G0UiMU15Gu9rmiLXpXaecx%2BVhS3VNWrDDHaz%2FdPLIB52H4GjQR8wkaLFrrkLECGMiGJkF9toxnAK5KX&timestamp=1486632431&sign=39afa305d4a82eb67ac1f191f47f64e6";
    public static final String SONG_LIST_ALL_URL = " http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChannels&param=yJxNHh3bMIYFcEaQxwK83tq5%2F5DBqbVtMHuq1g4fxssjKoOo9wn%2Bn5pjNDi%2FJFLa&timestamp=1486632431&sign=256a5151e9fd6bcbeef0b6f42b5c1919";
    public static final String SONG_LIST_NEW_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=KJ8H1UO3TQ2Et%2FEveTV%2Bt8a%2Br2jCR7fbcqsY9ZTHc%2FgPcxyOAIcHfe4iGZVwp1xZsf4wFqntc9HdlL2sikphWMvxv46i1a0Lv2h0Fsugw3e5dDTIseo7qevOW3Bi5BA3&timestamp=1486633214&sign=3cf9f2490198c06166322d80ee105843";
    public static final String HOT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";

    public static final String VIDEO_NEW_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8 ";
    public static final String VIDEO_HOT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num";
    public static final String KTV_SONG_URL ="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&method=baidu.ting.learn.now&page_size=50";
    public static final String KTV_IMG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&method=baidu.ting.active.showList";

    public static final String FIND_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&method=baidu.ting.plaza.indexFind&param=Th2biet8X4ouwsHOtI05VTEcCJfdrrlSr6dAnOAQaH71sBLg5NeJf38emZojGP0TAnKCgHwEK7NGg8p7oB%2F9kbecRQvV61alK7Xj9WKAog4%3D&timestamp=1487299857&sign=acc4d5575167b9e2d2e264a757874e37";
    public static final String RECOMMEDN_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&method=baidu.ting.plaza.index&cuid=47D66EFD52506DAC7B0C3A27F3F441A2&focu_num=8";


    public static final String PLAY_SONG_LIST_HEAD_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static final String PLAY_SONG_LIST_FOOT_URL = "&version=5.2.3&from=ios&channel=appstore";

    public static final String PLAY_HEAD_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static final String PLAY_FOOT_URL = "&_=1413017198449";


    public static final String SEARCH_HEAD_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.merge&query=";
    public static final String SEARCH_FOOT_URL = "&page_size=50&page_no=1&type=-1&format=json&from=ios&version=5.2.5&from=ios&channel=appstore";

    public static final String SHARE_HEAD_URL = "http://music.baidu.com/song/";
    public static final String SHARE_FOOT_URL = "?share=1&fr=app_android";
    public static final String SHARE_NULL_URL = "http://music.baidu.com/";


    public static final String HOT_SONGLIST_PLAY_HEAD_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    public static final String HOT_SONGLIST_PLAY_FOOT_URL = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";



}