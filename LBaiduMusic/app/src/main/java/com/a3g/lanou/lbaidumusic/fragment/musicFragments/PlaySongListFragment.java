package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.MyApp;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.PlayRecyclerViewAdapter;
import com.a3g.lanou.lbaidumusic.bean.EventONLineSongs;
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
public class PlaySongListFragment extends BaseFragment implements View.OnClickListener, RecyclerItemClickListener {
    private String listID;
    private ImageView ivPlayBackground, ivCenter, ivBack;
    private TextView tvInclude, tvTitle, tvNumber;
    private PlayRecyclerViewAdapter playRecyclerViewAdapter;
    private RecyclerView recyclerView;
    private PlaySongListBean playSongListBean;
    private boolean isClick = false;
    private static final String TAG = "PlaySongListFragment";
    private List<OnLineSongBean> onLineSongBeanList;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_play_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivPlayBackground = (ImageView) view.findViewById(R.id.iv_background_play);
        ivCenter = (ImageView) view.findViewById(R.id.iv_center_play_list);
        tvInclude = (TextView) view.findViewById(R.id.tv_include_play_list);
        tvTitle = (TextView) view.findViewById(R.id.tv_title_play_song_list);
        tvNumber = (TextView) view.findViewById(R.id.tv_song_number_play);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_play);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_play_list);

    }

    @Override
    protected void initData() {


        Bundle bundle = getArguments();
        listID = bundle.getString(MyBean.TO_PLAY_SONG_lIST);

        NetTool.getInstance().startRequset(MyUrl.PLAY_SONG_LIST_HEAD_URL + listID + MyUrl.PLAY_SONG_LIST_FOOT_URL,
                PlaySongListBean.class, new CallBack<PlaySongListBean>() {
                    @Override
                    public void onSucced(PlaySongListBean response) {
                        playSongListBean = response;
                        //转换成Bitmap
                        SimpleTarget target = new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                //转换成模糊图
                                Bitmap bitmap = StackBlur.blur(resource, 20, false);
                                ivPlayBackground.setImageBitmap(bitmap);
                            }
                        };
                        Glide.with(MyApp.getContext()).load(response.getPic_w700()).asBitmap().into(target);
                        Glide.with(MyApp.getContext()).load(response.getPic_300()).into(ivCenter);
                        tvInclude.setText("标签：" + response.getTag());
                        tvTitle.setText(response.getTitle());
                        tvNumber.setText("共" + response.getContent().size() + "首歌");

                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        playRecyclerViewAdapter = new PlayRecyclerViewAdapter(PlaySongListFragment.this, getContext());
                        recyclerView.setAdapter(playRecyclerViewAdapter);
                        playRecyclerViewAdapter.setPlaySongListBean(response);
                    }

                    @Override
                    public void onFailure(Throwable e) {

                    }
                });
    }



    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public static PlaySongListFragment newInstance(String listId) {

        Bundle args = new Bundle();
        args.putString(MyBean.TO_PLAY_SONG_lIST, listId);
        PlaySongListFragment fragment = new PlaySongListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_play_list:
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
            for (int i = 0; i < playSongListBean.getContent().size(); i++) {
                String songId = playSongListBean.getContent().get(i).getSong_id();
                NetTool.getInstance().startRequest(MyUrl.PLAY_HEAD_URL + songId + MyUrl.PLAY_FOOT_URL, new CallBack<String>() {
                    @Override
                    public void onSucced(String response) {
                        String song = response.substring(1, response.length() - 2);

                        Gson gson = new Gson();
                        OnLineSongBean onLineSongBean = gson.fromJson(song, OnLineSongBean.class);

                        onLineSongBeanList.add(onLineSongBean);
                        if (onLineSongBeanList.size() == playSongListBean.getContent().size()) {
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
