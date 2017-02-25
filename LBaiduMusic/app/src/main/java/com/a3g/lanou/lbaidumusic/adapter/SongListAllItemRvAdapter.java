package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.fragment.musicFragments.SongListFragment;
import com.a3g.lanou.lbaidumusic.myinterface.RecyclerItemClickListener;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;
import java.util.List;

/**
 * Created by liuHao on 17/2/14.
 */
public class SongListAllItemRvAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<String> datas;
    private Context context;
    private int outPosition;
    private SongListFragment songListFragment;




    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();


    }

    public SongListAllItemRvAdapter(SongListFragment songListFragment) {
        this.songListFragment = songListFragment;
        context = songListFragment.getContext();
    }



    public void setOutPosition(int outPosition) {
        this.outPosition = outPosition;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return MyViewHolder.creatMyViewHolder(context, parent, R.layout.item_popup_song_list_item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setText(R.id.tv_item_popup_kind, datas.get(position));


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
