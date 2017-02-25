package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.VideoListBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/16.
 */
public class VideoRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private VideoListBean videoListBean;
    private Context context;
    private List<VideoListBean.ResultBean.MvListBean> datas;

    public VideoRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setVideoListBean(VideoListBean videoListBean) {
        this.videoListBean = videoListBean;
        datas = videoListBean.getResult().getMv_list();
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return  MyViewHolder.creatMyViewHolder(context,parent, R.layout.item_recycler_head_video);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setImage(R.id.iv_item_gird_video,datas.get(position).getThumbnail());
        holder.setText(R.id.tv_item_gird_name,datas.get(position).getTitle());
        holder.setText(R.id.tv_item_gird_author,datas.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return videoListBean==null?0:videoListBean.getResult().getMv_list().size();
    }
}
