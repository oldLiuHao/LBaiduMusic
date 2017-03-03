package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.MyApp;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.SongListAllRvAdtpter;
import com.a3g.lanou.lbaidumusic.adapter.SonglistHomeRvAdapter;
import com.a3g.lanou.lbaidumusic.bean.SongListAllBean;
import com.a3g.lanou.lbaidumusic.bean.SongListHotBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.myinterface.RecyclerItemClickListener;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/13.
 */
public class SongListFragment extends BaseFragment implements View.OnClickListener, RecyclerItemClickListener{

    private RecyclerView rvHomeRecycler;
    private List<SongListHotBean.DiyInfoBean> datas;
    private SonglistHomeRvAdapter songlistHomeRvAdapter;
    private PopupWindow popupWindow;
    private LinearLayout lilaAll, lilaTransparent;
    private SongListAllBean songListAllBean;
    private SongListAllRvAdtpter songListAllRvAdtpter;
    private RecyclerView rvPopupAll;
    private TextView tvHotSongList, tvNewSongList, tvAllSongList;
    private FragmentManager fragmentManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SongListHotBean songListHotBean;
    private int lastVisibleItem = 0;
    private final int HOT_PAGE=0;
    private final int NEW_PAGE=1;
    private int pageType;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_song_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rvHomeRecycler = (RecyclerView) view.findViewById(R.id.rv_home_song_list_fragment);
        lilaAll = (LinearLayout) view.findViewById(R.id.lila_all_song_list);
        tvHotSongList = (TextView) view.findViewById(R.id.tv_hot_song_list);
        tvNewSongList = (TextView) view.findViewById(R.id.tv_new_song_list);
        tvAllSongList = (TextView) view.findViewById(R.id.tv_all_song_list_fragment);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srp_songlist);

    }

    private void popupWindowShow() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_all_song_list, null);
        popupWindow = new PopupWindow(view
                , ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true
        );
        rvPopupAll = (RecyclerView) view.findViewById(R.id.rv_pup_all);
        lilaTransparent = (LinearLayout) view.findViewById(R.id.lila_transparent_song_list_all);
        popupWindow.setTouchable(true);

        popupWindow.setOutsideTouchable(true);

        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        lilaTransparent.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        fragmentManager = getActivity().getSupportFragmentManager();
        pullToRefresh();


        songlistHomeRvAdapter = new SonglistHomeRvAdapter(SongListFragment.this);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHomeRecycler.setLayoutManager(linearLayoutManager);
        rvHomeRecycler.setAdapter(songlistHomeRvAdapter);
        hotNet();


    }

    private void pullToRefresh() {
        //设置SwipeRefreshLayout
        //设置动画颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorIncludeBackground);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    if (pageType==HOT_PAGE){
                        hotNet();

                    }else if (pageType==NEW_PAGE){
                        newNet();
                    }
            }
        });
    }

    private void hotNet() {
       
        datas= new ArrayList<>();
        //拉取网络数据
        NetTool.getInstance().startRequset(MyUrl.SONG_LIST_HOT_URL, SongListHotBean.class, new CallBack<SongListHotBean>() {
            @Override
            public void onSucced(SongListHotBean response) {
                datas = response.getDiyInfo();
                if (songListHotBean!=null){
                    int lastId = Integer.valueOf(songListHotBean.getDiyInfo().get(0).getList_id());
                    int newId  =Integer.valueOf(datas.get(0).getList_id());
                    if(newId==lastId){
                        Toast.makeText(MyApp.getContext(), "已经是最新内容啦，不要在累我了", Toast.LENGTH_SHORT).show();
                        //设置刷新成功之后动画消失

                    }else {
                        songlistHomeRvAdapter.setDatas(datas);
                        songListHotBean = response;
                    }


                }else {

                    songlistHomeRvAdapter.setDatas(datas);
                    songListHotBean = response;
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable e) {
                Toast.makeText(MyApp.getContext(), "网络不好，请稍后重试", Toast.LENGTH_LONG).show();
            }
        });
        pageType = HOT_PAGE;
    }

    private void newNet() {
        datas = new ArrayList<>();
        NetTool.getInstance().startRequset(MyUrl.SONG_LIST_NEW_URL, SongListHotBean.class, new CallBack<SongListHotBean>() {
            @Override
            public void onSucced(SongListHotBean response) {
                datas = response.getDiyInfo();
                if (songListHotBean!=null){
                    int lastId = Integer.valueOf(songListHotBean.getDiyInfo().get(0).getList_id());
                    int newId  =Integer.valueOf(datas.get(0).getList_id());
                    if(newId==lastId){
                        Toast.makeText(MyApp.getContext(), "已经是最新内容啦，不要在累我了", Toast.LENGTH_SHORT).show();
                        //设置刷新成功之后动画消失

                    }else {
                        songlistHomeRvAdapter.setDatas(datas);
                        songListHotBean = response;
                    }


                }else {

                    songlistHomeRvAdapter.setDatas(datas);
                    songListHotBean = response;
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable e) {
                Toast.makeText(MyApp.getContext(), "网络不好，请稍后重试", Toast.LENGTH_LONG).show();
            }
        });
        pageType =NEW_PAGE;

    }

    @Override
    protected void bindEvent() {

        lilaAll.setOnClickListener(this);
        tvHotSongList.setOnClickListener(this);
        tvNewSongList.setOnClickListener(this);
//        rvHomeRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == songlistHomeRvAdapter.getItemCount()){
//                        NetTool.getInstance().startRequset("http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.1.0&channel=875b&operator=1&method=baidu.ting.diy.gedan&page_size=30&page_no=3",
//                                SongListHotBean.class, new CallBack<SongListHotBean>() {
//                                    @Override
//                                    public void onSucced(SongListHotBean response) {
//                                        datas.addAll(response.getDiyInfo());
//                                        songlistHomeRvAdapter.setDatas(datas);
//                                    }
//
//                                    @Override
//                                    public void onFailure(Throwable e) {
//
//                                    }
//                                });
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lila_all_song_list:
                netPop();
                break;
            case R.id.tv_hot_song_list:
                tvHotSongList.setTextColor(getResources().getColor(R.color.colorIncludeBackground));
                tvNewSongList.setTextColor(getResources().getColor(R.color.colorDefaultTextView));
                hotNet();

                break;
            case R.id.tv_new_song_list:
                tvNewSongList.setTextColor(getResources().getColor(R.color.colorIncludeBackground));
                tvHotSongList.setTextColor(getResources().getColor(R.color.colorDefaultTextView));
                newNet();
                break;
            case R.id.lila_transparent_song_list_all:
                popupWindow.dismiss();

        }
    }

    private void netPop() {
        NetTool.getInstance().startRequset(MyUrl.SONG_LIST_ALL_URL, SongListAllBean.class, new CallBack<SongListAllBean>() {
            @Override
            public void onSucced(SongListAllBean response) {
                //加载popup
                popupWindowShow();
                songListAllBean = response;
                songListAllRvAdtpter = new SongListAllRvAdtpter(SongListFragment.this);
                songListAllRvAdtpter.setSongListAllBean(songListAllBean);
                rvPopupAll.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                rvPopupAll.setAdapter(songListAllRvAdtpter);
                View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_song_list, null);
                popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }


    @Override
    public void itemClick(int inPosition) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_main,PlaySongListFragment.newInstance(datas.get(inPosition).getList_id()));
        fragmentTransaction.commit();

    }
}
