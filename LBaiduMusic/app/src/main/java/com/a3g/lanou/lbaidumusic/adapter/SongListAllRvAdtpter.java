package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongListAllBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.SongListFragment;

import java.util.List;

/**
 * Created by liuHao on 17/2/14.
 */
public class SongListAllRvAdtpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private SongListAllBean songListAllBean;
    private SongListFragment songListFragment;
    private List<SongListAllBean.ResultBean.TagsBean> datas;
    private SongListAllItemRvAdapter songListAllItemRvAdapter;
    private final int HEAD_TYPE=1;
    private final int ELSE_TYPE = 2;



    public void setSongListAllBean(SongListAllBean songListAllBean) {
        this.songListAllBean = songListAllBean;
        datas = songListAllBean.getResult().getTags();
        notifyDataSetChanged();

    }

    public SongListAllRvAdtpter(SongListFragment songListFragment) {
        this.songListFragment = songListFragment;
        context = songListFragment.getContext();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = null;

        switch (viewType){

            case HEAD_TYPE:
                view = LayoutInflater.from(context).inflate(R.layout.item_head_popusong_list_first,parent,false);
                viewHolder = new HeadHolder(view);
                break;
            case ELSE_TYPE:
                view = LayoutInflater.from(context).inflate(R.layout.item_popup_song_list_first,parent,false);
                viewHolder = new ElseHolder(view);
                break;
        }


        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return HEAD_TYPE;
        }
        else {
            return ELSE_TYPE;
        }





    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == ELSE_TYPE) {
            ElseHolder elseHolder = (ElseHolder) holder;
            elseHolder.textView.setText(datas.get(position - 1).getFirst());
            elseHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false));
            songListAllItemRvAdapter = new SongListAllItemRvAdapter(songListFragment);
            songListAllItemRvAdapter.setDatas(datas.get(position - 1).getSecond());
            songListAllItemRvAdapter.setOutPosition(position);
            elseHolder.recyclerView.setAdapter(songListAllItemRvAdapter);

        }
    }
    @Override
    public int getItemCount() {
        return songListAllBean==null?0:(datas.size()+1);
    }



    class ElseHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private RecyclerView recyclerView;
        public ElseHolder(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.tv_titile_popup_all);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_item_popup_song_list_second);
        }
    }
    class HeadHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public HeadHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_head_item_popup);
        }
    }








}
