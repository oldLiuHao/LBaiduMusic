package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.RecommendBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/2/17.
 */
public class SingerGirdApapter extends BaseAdapter {
    private Context context;
    private RecommendBean recommendBean;
    private RecommendBean.ResultBeanXXXXXXXXXXXXXXX.EntryBean entryBean;

    public SingerGirdApapter(Context context) {
        this.context = context;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        entryBean = recommendBean.getResult().getEntry();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return entryBean==null?0:entryBean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return entryBean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent, R.layout.item_singer_recommend);
        myViewHolder.setImage(R.id.iv_singer_item_recommend,entryBean.getResult().get(position).getIcon());
        myViewHolder.setText(R.id.tv_singer_item_recommend,entryBean.getResult().get(position).getTitle());

        return myViewHolder.getView();
    }
}
