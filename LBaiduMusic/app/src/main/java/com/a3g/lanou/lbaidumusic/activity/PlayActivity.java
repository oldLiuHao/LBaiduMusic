package com.a3g.lanou.lbaidumusic.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.MusicPagerAdapter;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.InformationFragment;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.LyricFragment;
import com.a3g.lanou.lbaidumusic.fragment.playFragment.MiddlePlayFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/25.
 */
public class PlayActivity extends BaseActivity {
    private ViewPager viewPager;
    private MusicPagerAdapter musicPagerAdapter;
    private List<Fragment> fragments;
    private ImageView ivBack;
    @Override
    protected int bindLayout() {
        return R.layout.activity_play;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_activity_play);
        ivBack = (ImageView) findViewById(R.id.iv_back_play_activity);
    }

    @Override
    protected void initData() {
        musicPagerAdapter = new MusicPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicPagerAdapter);
        fragments = new ArrayList<>();
        fragments.add(new InformationFragment());
        fragments.add(new MiddlePlayFragment());
        fragments.add(new LyricFragment());
        musicPagerAdapter.setFragments(fragments);

    }

    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_play_activity:
                finish();
                break;
        }
    }
}
