package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.MyApp;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.HotPlayRecyclerAdapter;
import com.a3g.lanou.lbaidumusic.adapter.PlayRecyclerViewAdapter;
import com.a3g.lanou.lbaidumusic.bean.EventONLineSongs;
import com.a3g.lanou.lbaidumusic.bean.HotListBean;
import com.a3g.lanou.lbaidumusic.bean.HotPlayListBean;
import com.a3g.lanou.lbaidumusic.bean.OnLineSongBean;
import com.a3g.lanou.lbaidumusic.bean.PlaySongListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.myinterface.RecyclerItemClickListener;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;

import net.qiujuer.genius.blur.StackBlur;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/22.
 */
public class HotPlaySongListFragment extends BaseFragment implements View.OnClickListener, RecyclerItemClickListener {

    private String type;
    private HotListBean.ContentBeanX contentBeanX;
    private ImageView ivBack,ivBackground;
    private TextView tvSongNumber;
    private RecyclerView recyclerView;
    private HotPlayRecyclerAdapter hotPlayRecyclerAdapter;
    private HotPlayListBean hotPlayListBean;
    private boolean isClick=false;
    private List<OnLineSongBean> onLineSongBeanList;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_hot_play_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivBack = (ImageView) view.findViewById(R.id.iv_back_hot_play_list);
        ivBackground = (ImageView) view.findViewById(R.id.iv_background_hot_playlist);
        tvSongNumber = (TextView) view.findViewById(R.id.tv_song_number_hot_play_list);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_hot_playlist);

    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        hotPlayRecyclerAdapter = new HotPlayRecyclerAdapter(this);
        recyclerView.setAdapter(hotPlayRecyclerAdapter);
        Bundle bundle = getArguments();
        contentBeanX  = bundle.getParcelable(MyBean.TO_PLAY_SONG_lIST);
        Glide.with(getContext()).load(contentBeanX.getPic_s210()).into(ivBackground);
        type = String.valueOf(contentBeanX.getType());
        NetTool.getInstance().startRequset(MyUrl.HOT_SONGLIST_PLAY_HEAD_URL + type + MyUrl.HOT_SONGLIST_PLAY_FOOT_URL, HotPlayListBean.class, new CallBack<HotPlayListBean>() {
            @Override
            public void onSucced(HotPlayListBean response) {
                tvSongNumber.setText(response.getSong_list().size()+"首歌");
                hotPlayRecyclerAdapter.setHotPlayListBean(response);
                hotPlayListBean = response;
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }



    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);


    }

    public static HotPlaySongListFragment newInstance(HotListBean.ContentBeanX contentBeanX) {

        Bundle args = new Bundle();
        args.putParcelable(MyBean.TO_PLAY_SONG_lIST, contentBeanX);
        HotPlaySongListFragment fragment = new HotPlaySongListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_hot_play_list:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(this);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void itemClick(final int inPosition) {
        final EventONLineSongs eventONLineSongs = new EventONLineSongs();
        if (!isClick) {
            onLineSongBeanList = new ArrayList<>();
            for (int i = 0; i < hotPlayListBean.getSong_list().size(); i++) {
                String songId = hotPlayListBean.getSong_list().get(i).getSong_id();
                NetTool.getInstance().startRequest(MyUrl.PLAY_HEAD_URL + songId + MyUrl.PLAY_FOOT_URL, new CallBack<String>() {
                    @Override
                    public void onSucced(String response) {
                        String song = response.substring(1, response.length() - 2);

                        Gson gson = new Gson();
                        OnLineSongBean onLineSongBean = gson.fromJson(song, OnLineSongBean.class);

                        onLineSongBeanList.add(onLineSongBean);
                        if (onLineSongBeanList.size() == hotPlayListBean.getSong_list().size()) {
                            eventONLineSongs.setOnLineSongBeanList(onLineSongBeanList);

                            eventONLineSongs.setPostion(inPosition);
                            EventBus.getDefault().post(eventONLineSongs);
                            isClick = true;
                        }

                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Toast.makeText(getContext(), "网络不好，请确定联网后播放", Toast.LENGTH_SHORT).show();
                    }
                });

            }


        } else {
            eventONLineSongs.setPostion(inPosition);
            EventBus.getDefault().post(eventONLineSongs);
        }
    }
}
