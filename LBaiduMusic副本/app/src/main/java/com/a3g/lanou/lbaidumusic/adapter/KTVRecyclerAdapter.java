package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.KTVListBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/16.
 */
public class KTVRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private KTVListBean ktvListBean;
    private List<KTVListBean.ResultBean.ItemsBean> datas;

    public void setDatas(List<KTVListBean.ResultBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public KTVRecyclerAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.creatMyViewHolder(context,parent, R.layout.item_recycler_ktv);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setText(R.id.tv_name_ktv,datas.get(position).getSong_title()+"-"+datas.get(position).getArtist_name());
        holder.setText(R.id.tv_num_ktv,datas.get(position).getPlay_num()+"人唱过");
    }

    @Override
    public int getItemCount() {
        return datas==null?0:10;
    }
}
