package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongListHotBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/13.
 */
public class SongListGirdAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<SongListHotBean.DiyInfoBean> datas;

    public SongListGirdAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SongListHotBean.DiyInfoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return  MyViewHolder.creatMyViewHolder(context,parent,R.layout.item_recycler_head_song_list);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setImage(R.id.iv_gird_song_list,datas.get(position).getList_pic_large());
        holder.setText(R.id.tv_num_my," "+datas.get(position).getListen_num());
        holder.setText(R.id.tv_name_gird_song_list,datas.get(position).getTitle());
        holder.setText(R.id.tv_author_gird_song_list,datas.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }




}
