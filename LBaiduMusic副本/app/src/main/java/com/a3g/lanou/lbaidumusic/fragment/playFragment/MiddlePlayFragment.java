package com.a3g.lanou.lbaidumusic.fragment.playFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.OnLineSongBean;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by liuHao on 17/2/25.
 */
public class MiddlePlayFragment extends BaseFragment {
    private ImageView ivMiddle;
    private TextView tvName,tvAhthor;
    private SongBean songBean;
    private OnLineSongBean onLineSongBean;
    @Override
    protected int bindLayout() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_middle_play;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivMiddle = (ImageView) view.findViewById(R.id.iv_middle_play);
        tvName = (TextView) view.findViewById(R.id.tv_name_middle_play);
        tvAhthor = (TextView) view.findViewById(R.id.tv_author_middle_play);
    }

    @Override
    protected void initData() {
        if (getArguments()!=null){
            Bundle bundle = getArguments();
            songBean = bundle.getParcelable(MyBean.MY_SONG);
            if (songBean!=null){
                if (songBean.getSongImage()!=null){
                    ivMiddle.setImageBitmap(songBean.getSongImage());
                }
                tvName.setText(songBean.getSongName());
                tvAhthor.setText(songBean.getSingerName());
            }
            onLineSongBean = bundle.getParcelable(MyBean.MY_ONLINE_SONG);
            if (onLineSongBean!=null){
                Glide.with(getContext()).load(onLineSongBean.getSonginfo().getPic_premium()).into(ivMiddle);
                tvName.setText(onLineSongBean.getSonginfo().getTitle());
                tvAhthor.setText(onLineSongBean.getSonginfo().getAuthor());
            }

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongBean(SongBean songBean){
        this.songBean = songBean;
        if (songBean.getSongImage()!=null){
            ivMiddle.setImageBitmap(songBean.getSongImage());
        }
        tvName.setText(songBean.getSongName());
        tvAhthor.setText(songBean.getSingerName());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOnLineSongBean(OnLineSongBean onLineSongBean){
        this.onLineSongBean =onLineSongBean;
        Glide.with(getContext()).load(onLineSongBean.getSonginfo().getPic_premium()).into(ivMiddle);
        tvName.setText(onLineSongBean.getSonginfo().getTitle());
        tvAhthor.setText(onLineSongBean.getSonginfo().getAuthor());

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


}
