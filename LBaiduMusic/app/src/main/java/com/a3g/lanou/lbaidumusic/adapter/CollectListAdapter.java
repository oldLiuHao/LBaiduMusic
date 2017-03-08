package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.Collect;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.ArrayList;

/**
 * Created by liuHao on 17/3/4.
 */
public class CollectListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Collect> collects;
    private int selectItem = -1;
    public void setCollects(ArrayList<Collect> collects) {
        this.collects = collects;
        notifyDataSetChanged();
    }
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public int getCount() {
        return collects==null?0:collects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatListViewHolder(convertView,parent,R.layout.item_collect_list);
        myViewHolder.setText(R.id.tv_name_item_collect,collects.get(position).getSongName());
        myViewHolder.setText(R.id.tv_author_item_collect,collects.get(position).getAuthor());
        if (selectItem==position){
            myViewHolder.setTextColor(R.id.tv_name_item_collect, context.getResources().getColor(R.color.colorIncludeBackground));
            myViewHolder.setTextColor(R.id.tv_author_item_collect, context.getResources().getColor(R.color.colorIncludeBackground));

        }

        return myViewHolder.getView();
    }
}
