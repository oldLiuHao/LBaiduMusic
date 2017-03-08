package com.a3g.lanou.lbaidumusic.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.MusicPagerAdapter;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.HotFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.KTVFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.RecommendFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.SongListFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.VideoFragment;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/11.
 */
public class MusicFragment extends BaseFragment{

    private TabLayout tabMusic;
    private ViewPager vpMusic;
    private List<Fragment> fragments;
    private MusicPagerAdapter musicPagerAdapter;
    private String[] tabTitles = {"推荐","歌单","榜单","视频","K歌"};
    private MainReceiver mainReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册广播
        mainReceiver = new MainReceiver();
        IntentFilter intentFilter = new IntentFilter(MyBean.TO_SONG_LIST);
        getActivity().registerReceiver(mainReceiver, intentFilter);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_music ;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tabMusic = (TabLayout) view.findViewById(R.id.tab_music_fragment);
        vpMusic = (ViewPager) view.findViewById(R.id.vp_music_fragment);
    }

    @Override
    protected void initData() {
        //设置Fragment
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new SongListFragment());
        fragments.add(new HotFragment());
        fragments.add(new VideoFragment());
        fragments.add(new KTVFragment());

        musicPagerAdapter = new MusicPagerAdapter(getChildFragmentManager());
        musicPagerAdapter.setFragments(fragments);

        vpMusic.setAdapter(musicPagerAdapter);

        musicPagerAdapter.setTitles(tabTitles);
        tabMusic.setupWithViewPager(vpMusic);




    }

    @Override
    protected void bindEvent() {

    }
    class MainReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            vpMusic.setCurrentItem(1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mainReceiver);
    }
}
