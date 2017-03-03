package com.a3g.lanou.lbaidumusic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.bean.PlaySongListBean;
import com.a3g.lanou.lbaidumusic.myinterface.RecyclerItemClickListener;
import com.a3g.lanou.lbaidumusic.tools.MyViewHolder;

/**
 * Created by liuHao on 17/2/23.
 */
public class PlayRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private RecyclerItemClickListener recyclerItemClickListener;
    private PlaySongListBean playSongListBean;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int LINE_NORMAL = 2;
    private static final int LINE_MAX = Integer.MAX_VALUE;
    private static int lines = LINE_NORMAL;
    private static final String TAG = "PlayRecyclerViewAdapter";
    class HeadHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public HeadHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_head_list_play);
        }
    }

    public PlayRecyclerViewAdapter(RecyclerItemClickListener recyclerItemClickListener, Context context) {
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.context = context;
    }

    public void setPlaySongListBean(PlaySongListBean playSongListBean) {
        this.playSongListBean = playSongListBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder =null;
        switch (viewType){
            case TYPE_HEAD:
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_head_play_list,parent,false);
                viewHolder =  new HeadHolder(itemView);
                break;
            case TYPE_NORMAL:
                viewHolder= MyViewHolder.creatMyViewHolder(context,parent,R.layout.item_play_list);
                break;


        }

        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)return TYPE_HEAD;
        else return TYPE_NORMAL;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            int type = getItemViewType(position);
          switch (type){
              case TYPE_HEAD:
                  final HeadHolder headHolder = (HeadHolder) holder;
                  headHolder.textView.setMaxLines(2);
                  headHolder.textView.setText(playSongListBean.getDesc());
                  headHolder.itemView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                           if (lines ==LINE_NORMAL) {

                               headHolder.textView.setMaxLines(LINE_MAX);
                               lines = LINE_MAX;
                           }
                             else if (lines==LINE_MAX){
                                  headHolder.textView.setMaxLines(LINE_NORMAL);
                               lines =  LINE_NORMAL;
                          }


                          }

                  });
                  break;
              case TYPE_NORMAL:
                  final MyViewHolder normalHolder  = (MyViewHolder) holder;
                  normalHolder.setText(R.id.tv_song_name_item_play,playSongListBean.getContent().get(position-1).getTitle());
                  normalHolder.setText(R.id.tv_singer_item_play,playSongListBean.getContent().get(position-1).getAuthor());
                  normalHolder.itemView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                            recyclerItemClickListener.itemClick(position-1);
                                notifyDataSetChanged();
                                normalHolder.setTextColor(R.id.tv_song_name_item_play, context.getResources().getColor(R.color.colorIncludeBackground));
                      }
                  });

                  break;
          }
    }

    @Override
    public int getItemCount() {
        return playSongListBean==null?0:(playSongListBean.getContent().size()+1);

    }
}
