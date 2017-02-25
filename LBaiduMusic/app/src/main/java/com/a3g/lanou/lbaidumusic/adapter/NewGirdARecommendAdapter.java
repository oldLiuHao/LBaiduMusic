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
public class NewGirdARecommendAdapter extends BaseAdapter {
    private Context context;
    private RecommendBean recommendBean;
    private RecommendBean.ResultBeanXXXXXXXXXXXXXXX.Mix1Bean mix1Bean;

    public NewGirdARecommendAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        mix1Bean =recommendBean.getResult().getMix_1();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mix1Bean==null?0:mix1Bean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return mix1Bean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent, R.layout.item_new_recommend);
        myViewHolder.setImage(R.id.iv_new_item_recommend,mix1Bean.getResult().get(position).getPic());
        myViewHolder.setText(R.id.tv_name_new_item_recommend,mix1Bean.getResult().get(position).getTitle());
        myViewHolder.setText(R.id.tv_author_new_item_recommend,mix1Bean.getResult().get(position).getAuthor());



        return myViewHolder.getView();
    }
}
