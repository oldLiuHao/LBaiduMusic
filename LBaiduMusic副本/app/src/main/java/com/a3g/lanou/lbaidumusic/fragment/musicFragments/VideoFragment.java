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
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.MyApp;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.VideoRecyclerAdapter;
import com.a3g.lanou.lbaidumusic.bean.VideoListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
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
    private VideoListBean videoListBean;
    private final int HOT_PAGE=0;
    private final int NEW_PAGE=1;
    private int pageType = 1;

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

                if (videoListBean!=null){
                    int lastId = Integer.valueOf(videoListBean.getResult().getMv_list().get(0).getMv_id());
                    int newId  =Integer.valueOf(response.getResult().getMv_list().get(0).getMv_id());
                    if(newId==lastId){
                        Toast.makeText(MyApp.getContext(), "已经是最新内容啦，不要在累我了", Toast.LENGTH_SHORT).show();
                        //设置刷新成功之后动画消失

                    }else {
                        videoRecyclerAdapter.setVideoListBean(response);
                        videoListBean = response;
                    }


                }else {

                    videoRecyclerAdapter.setVideoListBean(response);
                    videoListBean = response;
                }
                lRecyclerView.refreshComplete(response.getResult().getMv_list().size());

            }

            @Override
            public void onFailure(Throwable e) {
                Toast.makeText(MyApp.getContext(), "网络不好，请稍后重试", Toast.LENGTH_LONG).show();
            }
        });

        pageType = NEW_PAGE;
    }
    private void hotNet(){
        NetTool.getInstance().startRequset(MyUrl.VIDEO_HOT_URL, VideoListBean.class, new CallBack<VideoListBean>() {
            @Override
            public void onSucced(VideoListBean response) {

                if (videoListBean!=null){
                    int lastId = Integer.valueOf(videoListBean.getResult().getMv_list().get(0).getMv_id());
                    int newId  =Integer.valueOf(response.getResult().getMv_list().get(0).getMv_id());
                    if(newId==lastId){
                        Toast.makeText(MyApp.getContext(), "已经是最新内容啦，不要在累我了", Toast.LENGTH_SHORT).show();
                        //设置刷新成功之后动画消失

                    }else {
                        videoRecyclerAdapter.setVideoListBean(response);
                        videoListBean = response;
                    }


                }else {

                    videoRecyclerAdapter.setVideoListBean(response);
                    videoListBean = response;
                }
                lRecyclerView.refreshComplete(response.getResult().getMv_list().size());
            }

            @Override
            public void onFailure(Throwable e) {
                Toast.makeText(MyApp.getContext(), "网络不好，请稍后重试", Toast.LENGTH_LONG).show();
            }
        });

        pageType = HOT_PAGE;
    }

    @Override
    protected void bindEvent() {
        tvHot.setOnClickListener(this);
        tvNew.setOnClickListener(this);
        lilaAll.setOnClickListener(this);
        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (pageType==NEW_PAGE){
                    newNet();
                }
                else if (pageType==HOT_PAGE){
                    hotNet();
                }

            }
        });
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
