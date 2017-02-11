package com.a3g.lanou.lbaidumusic.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.fragment.MainFragment;

public class MainActivity extends BaseActivity {
    private FrameLayout frameLayoutMain;
    private ImageView ivIconMain, ivPlayMain;
    private TextView tvSongNameMain, tvSingerMain;
    private FragmentManager fragmentManager;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        frameLayoutMain = bindView(R.id.frame_layout_main);
        ivIconMain = bindView(R.id.iv_icon_main);
        ivPlayMain = bindView(R.id.iv_play_main);
        tvSongNameMain = bindView(R.id.tv_song_name_main);
        tvSingerMain = bindView(R.id.tv_singer_name_main);

    }

    @Override
    protected void initData() {
        ivPlayMain.setImageResource(R.mipmap.bt_minibar_play_normal);

        //加载MainFragment
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.frame_layout_main,mainFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {

    }

}
