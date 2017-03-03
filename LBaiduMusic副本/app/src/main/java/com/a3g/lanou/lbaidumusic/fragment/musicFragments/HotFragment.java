package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.HotListAdapter;
import com.a3g.lanou.lbaidumusic.bean.HotListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;

/**
 * Created by liuHao on 17/2/13.
 */
public class HotFragment extends BaseFragment {
    private ListView listHot;
    private HotListAdapter hotListAdapter;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        listHot = (ListView) view.findViewById(R.id.list_hot_fragment);
    }

    @Override
    protected void initData() {
        hotListAdapter = new HotListAdapter(getContext());
        listHot.setAdapter(hotListAdapter);
        HotNet();
    }

    private void HotNet() {
        NetTool.getInstance().startRequset(MyUrl.HOT_URL, HotListBean.class, new CallBack<HotListBean>() {
            @Override
            public void onSucced(HotListBean response) {
                hotListAdapter.setHotListBean(response);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {

    }


}
