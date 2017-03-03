package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SearchBean;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/3/2.
 */
public class SearchContentListAdapter extends BaseAdapter {
    private Context context;
    private SearchBean searchBean;

    public SearchContentListAdapter(Context context) {
        this.context = context;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
    }

    @Override
    public int getCount() {

     return searchBean==null?0:3;
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
        MyViewHolder myViewHolder =MyViewHolder.creatListViewHolder(convertView,parent, R.layout.item_search_content);
        if (position==0&&searchBean.getResult().getArtist_info().getArtist_list()!=null){
            myViewHolder.setText(R.id.tv_content_search,StringSplit(searchBean.getResult().getArtist_info().getArtist_list().get(position).getAuthor()));
        }
        if (position==1&&searchBean.getResult().getAlbum_info().getAlbum_list()!=null){
            myViewHolder.setText(R.id.tv_content_search,"<" + StringSplit(searchBean.getResult().getAlbum_info().getAlbum_list().get(position).getTitle()) + "> - " + searchBean.getResult().getAlbum_info().getAlbum_list().get(position).getAuthor());
        }
        if (position==2&&searchBean.getResult().getSong_info().getSong_list()!=null){
            myViewHolder.setText(R.id.tv_content_search,searchBean.getResult().getSong_info().getSong_list().get(position).getTitle() + "-" + searchBean.getResult().getSong_info().getSong_list().get(position).getAuthor());
        }
        return myViewHolder.getView();
    }

    public String StringSplit(String s){

        String str  = s;
        String[] str1 = str.trim().split("<em>");
        String[] strings= new String[str1.length+2];
        int m = 0;
        for (int i = 0; i < str1.length; i++) {
            String[] string = str1[i].split("</em>");
            for (int j = 0; j < string.length; j++) {
                strings[m] = string[j];
                m++;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < strings.length; i++) {
            stringBuffer.append(strings[i]);
        }

        String[] value = stringBuffer.toString().split("null");

        return value[0];
    }

}
