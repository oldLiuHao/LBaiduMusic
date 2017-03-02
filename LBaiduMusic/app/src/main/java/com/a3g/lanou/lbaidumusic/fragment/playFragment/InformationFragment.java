package com.a3g.lanou.lbaidumusic.fragment.playFragment;

import android.os.Bundle;
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
public class InformationFragment extends BaseFragment {
    private SongBean songBean;
    private ImageView ivIcon;
    private TextView tvName,tvAuthor;
    private OnLineSongBean onLineSongBean;
    @Override
    protected int bindLayout() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_information;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivIcon = (ImageView) view.findViewById(R.id.iv_information_play);
        tvAuthor = (TextView) view.findViewById(R.id.tv_author_information);
        tvName = (TextView) view.findViewById(R.id.tv_name_information);
    }

    @Override
    protected void initData() {
        if (getArguments()!=null){
            Bundle bundle = getArguments();
            songBean = bundle.getParcelable(MyBean.MY_SONG);
            if (songBean!=null){
                if (songBean.getSongImage()!=null){
                    ivIcon.setImageBitmap(songBean.getSongImage());

                }
                tvAuthor.setText("歌手："+songBean.getSingerName());
                tvName.setText("专辑："+songBean.getSongName());
            }
            onLineSongBean = bundle.getParcelable(MyBean.MY_ONLINE_SONG);
            if (onLineSongBean!=null){
                Glide.with(getContext()).load(onLineSongBean.getSonginfo().getPic_premium()).into(ivIcon);
                tvName.setText("专辑："+onLineSongBean.getSonginfo().getTitle());
                tvAuthor.setText("歌手："+onLineSongBean.getSonginfo().getAuthor());
            }

        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSongBean(SongBean songBean){
        this.songBean = songBean;
        if (songBean.getSongImage()!=null){
            ivIcon.setImageBitmap(songBean.getSongImage());
        }
        tvName.setText(songBean.getSongName());
        tvAuthor.setText(songBean.getSingerName());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOnLineSongBean(OnLineSongBean onLineSongBean){
        this.onLineSongBean = onLineSongBean;
        Glide.with(getContext()).load(onLineSongBean.getSonginfo().getPic_premium()).into(ivIcon);
        tvName.setText(onLineSongBean.getSonginfo().getTitle());
        tvAuthor.setText(onLineSongBean.getSonginfo().getAuthor());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void bindEvent() {

    }
}
