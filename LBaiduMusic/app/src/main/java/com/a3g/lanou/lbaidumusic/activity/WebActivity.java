package com.a3g.lanou.lbaidumusic.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.RecommendBean;
import com.a3g.lanou.lbaidumusic.myinterface.ToWebInter;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

/**
 * Created by liuHao on 17/2/21.
 */
public class WebActivity extends BaseActivity {
    private ToWebInter toWebInter;
    private WebView webView;
    private TextView tvBack;
    private ImageView ivBack;
    @Override
    protected int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.wv_web);
        tvBack = (TextView) findViewById(R.id.tv_back_web);
        ivBack = (ImageView) findViewById(R.id.iv_back_web);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getBundleExtra(MyBean.TO_WEB);
        toWebInter = bundle.getParcelable(MyBean.TO_WEB);
        tvBack.setText(toWebInter.getWebTitle());
        webView.loadUrl(toWebInter.getUrl());
        webView.setWebViewClient(new WebViewClient());

    }

    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
          case R.id.iv_back_web:
              finish();
              break;
            case R.id.tv_back_web:
                finish();
                break;
        }
    }
}
