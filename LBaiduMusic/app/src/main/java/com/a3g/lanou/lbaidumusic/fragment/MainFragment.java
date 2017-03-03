package com.a3g.lanou.lbaidumusic.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.FragmentMainAdapter;
import com.a3g.lanou.lbaidumusic.myinterface.JumpInter;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/11.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager vpFragmentMain;
    private TabLayout tabFragmentMain;
    private FragmentMainAdapter fragmentMainAdapter;
    private List<Fragment> datas;
    private TextView tvBack;
    private ImageView ivLogin,ivBack,ivSearch;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private static final String TAG = "MainFragment";
    @Override
    protected int bindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        vpFragmentMain = (ViewPager) view.findViewById(R.id.vp_fragment_main);
        tabFragmentMain = (TabLayout) view.findViewById(R.id.tab_fragment_main);
        ivLogin = (ImageView) view.findViewById(R.id.iv_login_main);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drla_main);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_main);
        tvBack = (TextView) view.findViewById(R.id.tv_back_main);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search_fragment_main);
    }

    @Override
    protected void initData() {

        fragmentManager = getActivity().getSupportFragmentManager();

        //把Fragment加入集合
        datas = new ArrayList<>();

        datas.add(new MyFragment());
        datas.add(new MusicFragment());
        datas.add(new DynamicFragment());
        datas.add(new FindFragment());
         //设置Fragment适配器
        fragmentMainAdapter = new FragmentMainAdapter(getChildFragmentManager());
        fragmentMainAdapter.setDatas(datas);
        vpFragmentMain.setAdapter(fragmentMainAdapter);
        tabFragmentMain.setupWithViewPager(vpFragmentMain);
        //关闭DrawerLayout滑动
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



    }

    @Override
    protected void bindEvent() {
            ivLogin.setOnClickListener(this);
            ivBack.setOnClickListener(this);
            tvBack.setOnClickListener(this);
            ivSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_main :
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.iv_back_main :
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_back_main :
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_search_fragment_main:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame_layout_main,new SearchFragment());
                fragmentTransaction.commit();
                break;

        }
    }



}
