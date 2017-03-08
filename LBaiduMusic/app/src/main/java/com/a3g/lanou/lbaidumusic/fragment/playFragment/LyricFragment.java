package com.a3g.lanou.lbaidumusic.fragment.playFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.LyricTime;
import com.a3g.lanou.lbaidumusic.bean.OnLineSongBean;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.tools.LrcView;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by liuHao on 17/2/25.
 */
public class LyricFragment extends BaseFragment {
    private LrcView lrcView;
    private SongBean songBean;
    private OnLineSongBean onLineSongBean;
    private static final String TAG = "LyricFragment";
    @Override
    protected int bindLayout() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_lyric;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lrcView = (LrcView) view.findViewById(R.id.lv_lyric);
    }

    @Override
    protected void initData() {
        if (getArguments()!=null){
            Bundle bundle = getArguments();
            songBean = bundle.getParcelable(MyBean.MY_SONG);
            if (songBean!=null){
                if (songBean.getSongImage()!=null){

                }

            }
            onLineSongBean = bundle.getParcelable(MyBean.MY_ONLINE_SONG);
            if (onLineSongBean!=null){
                lrcView.loadLrc(onLineSongBean.getSonginfo().getLrclink(),1);
            }

        }
    }

    @Override
    protected void bindEvent() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongBean(SongBean songBean){
        this.songBean = songBean;
        if (songBean.getSongImage()!=null){

        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOnLineSongBean(OnLineSongBean onLineSongBean){
        this.onLineSongBean =onLineSongBean;
        lrcView.loadLrc(onLineSongBean.getSonginfo().getLrclink(),1);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPegress(LyricTime lyricTime){

        lrcView.updateTime(lyricTime.getTime());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);

    }
}
