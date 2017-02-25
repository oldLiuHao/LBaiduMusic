package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.VideoRecyclerAdapter;
import com.a3g.lanou.lbaidumusic.bean.VideoListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

/**
 * Created by liuHao on 17/2/13.
 */
public class VideoFragment extends BaseFragment implements View.OnClickListener {
    private LRecyclerView lRecyclerView ;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private VideoRecyclerAdapter videoRecyclerAdapter;
    private TextView tvNew,tvHot;
    private LinearLayout lilaAll;
    private PopupWindow popupWindow;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lRecyclerView = (LRecyclerView) view.findViewById(R.id.lrecyler_list_video);
        tvNew = (TextView) view.findViewById(R.id.tv_new_video);
        tvHot = (TextView) view.findViewById(R.id.tv_hot_video);
        lilaAll = (LinearLayout) view.findViewById(R.id.lila_all_video);



    }

    private void showPop() {
        popupWindow = new PopupWindow(LayoutInflater.from(getContext()).inflate(R.layout.popup_video,null)
        , ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_video,null);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
    }

    @Override
    protected void initData() {
        videoRecyclerAdapter = new VideoRecyclerAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(videoRecyclerAdapter);
        lRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.item_foot_song_list_recycler,null);
        lRecyclerViewAdapter.addFooterView(footView);

        newNet();
    }

    private void newNet() {
        NetTool.getInstance().startRequset(MyUrl.VIDEO_NEW_URL, VideoListBean.class, new CallBack<VideoListBean>() {
            @Override
            public void onSucced(VideoListBean response) {
                videoRecyclerAdapter.setVideoListBean(response);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }
    private void hotNet(){
        NetTool.getInstance().startRequset(MyUrl.VIDEO_HOT_URL, VideoListBean.class, new CallBack<VideoListBean>() {
            @Override
            public void onSucced(VideoListBean response) {
                videoRecyclerAdapter.setVideoListBean(response);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {
        tvHot.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        lilaAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hot_video:
                tvHot.setTextColor(getResources().getColor(R.color.colorIncludeBackground));
                tvNew.setTextColor(getResources().getColor(R.color.colorDefaultTextView));
                hotNet();

                break;
            case R.id.tv_new_video:
                tvNew.setTextColor(getResources().getColor(R.color.colorIncludeBackground));
                tvHot.setTextColor(getResources().getColor(R.color.colorDefaultTextView));
                newNet();
                break;
            case R.id.lila_all_video:
                showPop();

                break;


        }
    }
}
