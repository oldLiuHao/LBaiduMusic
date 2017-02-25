package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.SongListHotBean;

import java.util.List;

/**
 * Created by liuHao on 17/2/15.
 */
public class SonglistHomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEAD_TYPE =0;
    private final int FOOT_TYPE = 1;
    private Context context;
    private List<SongListHotBean.DiyInfoBean> datas;
    class HeadHoler extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        public HeadHoler(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_head_song_list_fragment);
        }
    }
    class FootHolder extends RecyclerView.ViewHolder{

        public FootHolder(View itemView) {
            super(itemView);
        }
    }

    public SonglistHomeRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SongListHotBean.DiyInfoBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View itemView =null;
        switch (viewType){
            case HEAD_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_song_list_recycler,parent,false);
                viewHolder = new HeadHoler(itemView);
                break;
            case FOOT_TYPE:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_foot_song_list_recycler,parent,false);
                viewHolder = new FootHolder(itemView);
        }




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == HEAD_TYPE){
            HeadHoler headHoler = (HeadHoler) holder;
            SongListGirdAdapter adapter = new SongListGirdAdapter(context);
            headHoler.recyclerView.setLayoutManager(new GridLayoutManager(context,2, LinearLayoutManager.VERTICAL,false));

            headHoler.recyclerView.setAdapter(adapter);

            adapter.setDatas(datas);
        }

    }

    @Override
    public int getItemViewType(int position) {
       if (position==0){
           return HEAD_TYPE;
       }else return FOOT_TYPE;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
