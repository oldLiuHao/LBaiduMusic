package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.HotPlayListBean;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.HotPlaySongListFragment;
import com.a3g.lanou.lbaidumusic.myinterface.RecyclerItemClickListener;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/3/6.
 */
public class HotPlayRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private RecyclerItemClickListener recyclerItemClickListener;
    private HotPlaySongListFragment hotPlaySongListFragment;
    private HotPlayListBean hotPlayListBean;

    public HotPlayRecyclerAdapter(HotPlaySongListFragment hotPlaySongListFragment) {
        this.hotPlaySongListFragment = hotPlaySongListFragment;
        recyclerItemClickListener = hotPlaySongListFragment;
    }

    public void setHotPlayListBean(HotPlayListBean hotPlayListBean) {
        this.hotPlayListBean = hotPlayListBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.creatMyViewHolder(hotPlaySongListFragment.getContext(),parent,R.layout.item_hot_play);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.setText(R.id.tv_name_item_hot_play,hotPlayListBean.getSong_list().get(position).getTitle());
        holder.setText(R.id.tv_author_item_hot_play,hotPlayListBean.getSong_list().get(position).getAuthor());
        holder.setImage(R.id.iv_icon_item_hot_play,hotPlayListBean.getSong_list().get(position).getPic_big());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.itemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotPlayListBean==null?0:hotPlayListBean.getSong_list().size();
    }
}
