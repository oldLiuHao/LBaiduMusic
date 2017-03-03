package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

import java.util.List;

/**
 * Created by liuHao on 17/2/24.
 */
public class LocalListAdapter extends BaseAdapter {

    private List<SongBean> songBeen;
    private Context context;
    private int selectItem = -1;

    public  LocalListAdapter(Context context) {
        this.context = context;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public void setSongBeen(List<SongBean> songBeen) {
        this.songBeen = songBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return songBeen==null?0:songBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return songBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = MyViewHolder.creatMyViewHolder(context,parent, R.layout.item_list_local);
        myViewHolder.setText(R.id.tv_name_list_local,songBeen.get(position).getSongName());
        myViewHolder.setText(R.id.tv_author_list_local,songBeen.get(position).getSingerName());
        if (position==selectItem){
            myViewHolder.setTextColor(R.id.tv_name_list_local, context.getResources().getColor(R.color.colorIncludeBackground));
            myViewHolder.setTextColor(R.id.tv_author_list_local, context.getResources().getColor(R.color.colorIncludeBackground));

        }

        return myViewHolder.getView();
    }

}
