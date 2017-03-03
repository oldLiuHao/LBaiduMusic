package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.MusicPagerAdapter;
import com.a3g.lanou.lbaidumusic.fragment.localFragments.FileLocalFragment;
import com.a3g.lanou.lbaidumusic.fragment.localFragments.SingerLocalFragment;
import com.a3g.lanou.lbaidumusic.fragment.localFragments.SonglistLocalFragment;
import com.a3g.lanou.lbaidumusic.fragment.localFragments.SpecalLocalFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.SongListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/24.
 */
public class LocalFragment extends BaseFragment implements View.OnClickListener {

    private MusicPagerAdapter musicPagerAdapter;
    private String[] titles = {"歌曲","文件夹","歌手","专辑"};
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView ivBack;
    private TextView tvBack;
    private static final String TAG = "LocalFragment";
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_local;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_local_fragment);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_local_fragment);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_local);
        tvBack = (TextView) view.findViewById(R.id.tv_back_local);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        SonglistLocalFragment songlistLocalFragment = new SonglistLocalFragment();
        songlistLocalFragment.setArguments(getArguments());
        Log.e(TAG, "initData: "+getArguments() );
        fragments.add(songlistLocalFragment);
        fragments.add(new FileLocalFragment());
        fragments.add(new SingerLocalFragment());
        fragments.add(new SpecalLocalFragment());
        musicPagerAdapter = new MusicPagerAdapter(getChildFragmentManager());
        musicPagerAdapter.setTitles(titles);
        musicPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(musicPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

    }

    @Override
    protected void bindEvent() {
            ivBack.setOnClickListener(this);
            tvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_local:
                fragmentTransaction.remove(this);
                fragmentTransaction.commit();
                break;
            case R.id.tv_back_local:
                fragmentTransaction.remove(this);
                fragmentTransaction.commit();
                break;
        }
    }
}
