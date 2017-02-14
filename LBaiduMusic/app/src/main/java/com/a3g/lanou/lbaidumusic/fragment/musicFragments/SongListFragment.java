package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.SongListGirdAdapter;
import com.a3g.lanou.lbaidumusic.bean.SongListHotBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuHao on 17/2/13.
 */
public class SongListFragment extends BaseFragment {

    private GridView gridView ;
    private List<SongListHotBean.DiyInfoBean> datas ;
    private SongListGirdAdapter songListGirdAdapter;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_song_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        gridView = (GridView) view.findViewById(R.id.grid_song_list_fragment);

    }

    @Override
    protected void initData() {
        songListGirdAdapter = new SongListGirdAdapter(getContext());
        gridView.setAdapter(songListGirdAdapter);
        datas = new ArrayList<>();
        NetTool.getInstance().startRequset(MyUrl.SONG_LIST_HOT_URL, SongListHotBean.class, new CallBack<SongListHotBean>() {
            @Override
            public void onSucced(SongListHotBean response) {
                datas = response.getDiyInfo();
                songListGirdAdapter.setDatas(datas);
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
