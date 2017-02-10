package com.a3g.lanou.lbaidumusic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by liuHao on 17/2/10.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initData();
        bindEvent();
    }

    protected abstract int bindLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void bindEvent();


}
