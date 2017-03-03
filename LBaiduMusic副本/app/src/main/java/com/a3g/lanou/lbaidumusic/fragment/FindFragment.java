package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.FindRecyclerAdapter;
import com.a3g.lanou.lbaidumusic.bean.FindListBean;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

/**
 * Created by liuHao on 17/2/11.
 */
public class FindFragment extends BaseFragment{
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private FindRecyclerAdapter findRecyclerAdapter;
    private FindListBean findListBean;
    private FindListBean.ResultBean.ShopInfoBean shopInfoBean;
    private ImageView imgShopFirst,imgShopSecond,imgShopThird;
    private TextView tvShopFirst,tvShopSecond,tvShopThird;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_find ;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lRecyclerView = (LRecyclerView) view.findViewById(R.id.lrecycler_find);
    }

    @Override
    protected void initData() {
        findRecyclerAdapter = new FindRecyclerAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(findRecyclerAdapter);
        lRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        final View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_head_find,null);
        imgShopFirst = (ImageView) headView.findViewById(R.id.iv_shop_first_find);
        imgShopSecond = (ImageView) headView.findViewById(R.id.iv_shop_second_find);
        imgShopThird = (ImageView) headView.findViewById(R.id.iv_shop_third_find);
        tvShopFirst = (TextView) headView.findViewById(R.id.tv_shop_first_find);
        tvShopSecond = (TextView) headView.findViewById(R.id.tv_shop_second_find);
        tvShopThird = (TextView) headView.findViewById(R.id.tv_shop_third_find);
        lRecyclerViewAdapter.addHeaderView(headView);
        NetTool.getInstance().startRequset(MyUrl.FIND_URL, FindListBean.class, new CallBack<FindListBean>() {
            @Override
            public void onSucced(FindListBean response) {
                findListBean = response;
                shopInfoBean = response.getResult().getShopInfo();
                findRecyclerAdapter.setLiveListBean(findListBean.getResult().getLiveInfo().getLiveList());
                Glide.with(getContext()).load(shopInfoBean.getShopList().get(0).getPicurl()).into(imgShopFirst);
                Glide.with(getContext()).load(shopInfoBean.getShopList().get(1).getPicurl()).into(imgShopSecond);
                Glide.with(getContext()).load(shopInfoBean.getShopList().get(2).getPicurl()).into(imgShopThird);
                tvShopFirst.setText(shopInfoBean.getShopList().get(0).getTitle());
                tvShopSecond.setText(shopInfoBean.getShopList().get(1).getTitle());
                tvShopThird.setText(shopInfoBean.getShopList().get(2).getTitle());

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
