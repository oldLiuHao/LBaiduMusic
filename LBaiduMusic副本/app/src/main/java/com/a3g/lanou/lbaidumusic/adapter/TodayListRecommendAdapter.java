package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.RecommendBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/2/18.
 */
public class TodayListRecommendAdapter extends BaseAdapter {

    private Context context;
    private RecommendBean recommendBean;
    private RecommendBean.ResultBeanXXXXXXXXXXXXXXX.RecsongBean recsongBean;

    public TodayListRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        recsongBean = recommendBean.getResult().getRecsong();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recsongBean==null?0:recsongBean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return recsongBean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent, R.layout.item_today_recommend);
        myViewHolder.setCircleImage(R.id.iv_play_item_today,recsongBean.getResult().get(position).getPic_premium());
        myViewHolder.setText(R.id.tv_name_item_today,recsongBean.getResult().get(position).getTitle());
        myViewHolder.setText(R.id.tv_author_item_today,recsongBean.getResult().get(position).getTitle());



        return myViewHolder.getView();
    }
}
