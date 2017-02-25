package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.FindListBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/17.
 */
public class FindRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<FindListBean.ResultBean.LiveInfoBean.LiveListBean> liveListBean;

    public FindRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setLiveListBean(List<FindListBean.ResultBean.LiveInfoBean.LiveListBean> liveListBean) {
        this.liveListBean = liveListBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return  MyViewHolder.creatMyViewHolder(context,parent, R.layout.item_live_gird);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setText(R.id.tv_num_live_find," "+liveListBean.get(position).getUsercount());
        holder.setText(R.id.tv_name_live_find,liveListBean.get(position).getNickname());
        holder.setImage(R.id.iv_live_find,liveListBean.get(position).getLiveimg());
    }

    @Override
    public int getItemCount() {
        return liveListBean==null?0:liveListBean.size();
    }
}
