package com.a3g.lanou.lbaidumusic.fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.FragmentMainAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/11.
 */
public class MainFragment extends BaseFragment {

    private ViewPager vpFragmentMain;
    private TabLayout tabFragmentMain;

    private FragmentMainAdapter fragmentMainAdapter;
    private List<Fragment> datas;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        vpFragmentMain = (ViewPager) view.findViewById(R.id.vp_fragment_main);
        tabFragmentMain = (TabLayout) view.findViewById(R.id.tab_fragment_main);
    }

    @Override
    protected void initData() {
        //把Fragment加入集合
        datas = new ArrayList<>();
        datas.add(new MyFragment());
        datas.add(new MusicFragment());
        datas.add(new DynamicFragment());
        datas.add(new FindFragment());
         //设置Fragment适配器
        fragmentMainAdapter = new FragmentMainAdapter(getActivity().getSupportFragmentManager());
        fragmentMainAdapter.setDatas(datas);
        vpFragmentMain.setAdapter(fragmentMainAdapter);
        tabFragmentMain.setupWithViewPager(vpFragmentMain);

    }

    @Override
    protected void bindEvent() {

    }
}
