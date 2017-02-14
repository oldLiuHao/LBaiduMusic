package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongListHotBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by liuHao on 17/2/13.
 */
public class SongListGirdAdapter extends BaseAdapter {

    private Context context;
    private List<SongListHotBean.DiyInfoBean> datas;

    public SongListGirdAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SongListHotBean.DiyInfoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas==null?0:(datas.size());
    }

    @Override
    public Object getItem(int position) {

        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_song_list,parent,false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);

        }else {
            myHolder= (MyHolder) convertView.getTag();
        }
        Glide.with(context).load(datas.get(position).getList_pic_large()).into(myHolder.ivBackground);
        myHolder.tvNumber.setText(" "+datas.get(position).getListen_num());
        myHolder.tvAuthor.setText("by "+datas.get(position).getUsername());
        myHolder.tvName.setText(datas.get(position).getTitle());
        return convertView;
    }
    private class MyHolder{
        private TextView tvNumber,tvName,tvAuthor;
        private ImageView ivBackground;

        public MyHolder(View view) {
            tvNumber = (TextView) view.findViewById(R.id.tv_num_my);
            tvName = (TextView) view.findViewById(R.id.tv_name_gird_song_list);
            tvAuthor = (TextView) view.findViewById(R.id.tv_author_gird_song_list);
            ivBackground = (ImageView) view.findViewById(R.id.iv_gird_song_list);
        }
    }

}
