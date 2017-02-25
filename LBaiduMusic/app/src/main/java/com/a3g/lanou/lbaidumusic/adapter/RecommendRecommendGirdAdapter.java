package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.BuildConfig;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.RecommendBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/2/17.
 */
public class RecommendRecommendGirdAdapter extends BaseAdapter {
    private Context context;
    private RecommendBean recommendBean;
    private RecommendBean.ResultBeanXXXXXXXXXXXXXXX.DiyBean diyBean;

    public RecommendRecommendGirdAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        diyBean = recommendBean.getResult().getDiy();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return diyBean==null?0:diyBean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return diyBean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent,R.layout.item_recommend_recommend);
        myViewHolder.setImage(R.id.iv_recommend_gird_recommend,diyBean.getResult().get(position).getPic());
        myViewHolder.setText(R.id.tv_num_recommend_recommend," "+diyBean.getResult().get(position).getListenum());

        myViewHolder.setText(R.id.tv_title_recommend_recommend,diyBean.getResult().get(position).getTitle());
        return myViewHolder.getView();
    }
}
