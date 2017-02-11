package com.a3g.lanou.lbaidumusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liuHao on 17/2/11.
 */
public class FragmentMainAdapter extends FragmentPagerAdapter {
    private String[] titles = {"我的","音乐","动态","发现"};
    private List<Fragment> datas;
    public FragmentMainAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDatas(List<Fragment> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
