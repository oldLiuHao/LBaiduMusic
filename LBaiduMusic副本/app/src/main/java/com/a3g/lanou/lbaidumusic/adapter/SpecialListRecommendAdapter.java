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
public class SpecialListRecommendAdapter extends BaseAdapter {

    private Context context;
    private RecommendBean recommendBean;
    private RecommendBean.ResultBeanXXXXXXXXXXXXXXX.Mod7Bean mod7Bean;

    public SpecialListRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
        mod7Bean = recommendBean.getResult().getMod_7();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mod7Bean==null?0:mod7Bean.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return mod7Bean.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent, R.layout.item_today_special);
        myViewHolder.setImage(R.id.iv_item_special,mod7Bean.getResult().get(position).getPic());
        myViewHolder.setText(R.id.tv_name_item_special,mod7Bean.getResult().get(position).getTitle());
        myViewHolder.setText(R.id.tv_author_item_special,mod7Bean.getResult().get(position).getDesc());



        return myViewHolder.getView();
    }
}
