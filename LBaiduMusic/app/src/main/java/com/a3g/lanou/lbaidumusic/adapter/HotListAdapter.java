package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.HotListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/15.
 */
public class HotListAdapter extends BaseAdapter {


    private HotListBean hotListBean;

    private List<HotListBean.ContentBeanX> contentBeanXs;
    private Context context;

    public HotListAdapter(Context context) {
        this.context = context;

    }

    public void setHotListBean(HotListBean hotListBean) {
        this.hotListBean = hotListBean;
        contentBeanXs = hotListBean.getContent();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contentBeanXs==null?0:contentBeanXs.size();
    }

    @Override
    public Object getItem(int position) {
        return contentBeanXs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       List<HotListBean.ContentBeanX.ContentBean> contentBeens = contentBeanXs.get(position).getContent();
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView, parent, R.layout.item_hot_fragment);
        myViewHolder.setImage(R.id.iv_item_hot_fragment, contentBeanXs.get(position).getPic_s210());
        myViewHolder.setText(R.id.tv_name_hot_list,contentBeanXs.get(position).getName());
        myViewHolder.setText(R.id.tv_first_content_hot_list, "1" + contentBeens.get(0).getTitle() + "-" + contentBeens.get(0).getAuthor());
        myViewHolder.setText(R.id.tv_second_content_hot_list,"2"+contentBeens.get(1).getTitle() + "-" + contentBeens.get(1).getAuthor());
        myViewHolder.setText(R.id.tv_third_content_hot_list, "3" + contentBeens.get(2).getTitle() + "-" + contentBeens.get(2).getAuthor());
        return myViewHolder.getView();
    }


}
