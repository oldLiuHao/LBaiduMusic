package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liuHao on 17/2/10.
 */
public abstract class BaseFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(bindLayout(),container,false);
        initView(view,savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        bindEvent();

    }
    protected abstract int bindLayout();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void bindEvent();



}
