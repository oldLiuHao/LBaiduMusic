package com.a3g.lanou.lbaidumusic.fragment.localFragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.LocalListAdapter;
import com.a3g.lanou.lbaidumusic.bean.SongBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.MusicInterface;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.util.List;

/**
 * Created by liuHao on 17/2/24.
 */
public class SonglistLocalFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private LocalListAdapter localListAdapter;
    private List<SongBean> songBeen;
    private TextView tvNmae,tvAuthor;
    private MusicInterface musicInterface;
    private LocalPlayIndexReceiver localPlayIndexReceiver;
    private static final String TAG = "SonglistLocalFragment";
    @Override
    protected int bindLayout() {
        return R.layout.fragment_local_songlist;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.list_songlist_local);

    }

    @Override
    protected void initData() {
        //注册广播
        localPlayIndexReceiver = new LocalPlayIndexReceiver();
        IntentFilter intentFilter = new IntentFilter(MyBean.PLAY_LOCAL_INDEX);
        getActivity().registerReceiver(localPlayIndexReceiver,intentFilter);

        localListAdapter = new LocalListAdapter(getContext());

        Bundle bundle = getArguments();
        Log.e(TAG, "initView: "+bundle );
        songBeen = bundle.getParcelableArrayList(MyBean.LOCAL_SONG);
        localListAdapter.setSongBeen(songBeen);
        listView.setAdapter(localListAdapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_head_list_local,null);
        listView.addHeaderView(view);
        listView.setOnItemClickListener(this);

        musicInterface = (MusicInterface) getActivity();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list_songlist_local:
                if (position==0){

                }else {
                    localListAdapter.setSelectItem(position-1);
                    localListAdapter.notifyDataSetInvalidated();
                    musicInterface.playMusic(position-1);
                }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(localPlayIndexReceiver);
    }

    class LocalPlayIndexReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            localListAdapter.setSelectItem(intent.getIntExtra(MyBean.PLAY_LOCAL_INDEX,-1));
            localListAdapter.notifyDataSetInvalidated();
        }
    }

}
