package com.a3g.lanou.lbaidumusic.fragment.musicFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.a3g.lanou.lbaidumusic.InsertItemThread;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.KTVRecyclerAdapter;
import com.a3g.lanou.lbaidumusic.bean.KTVImageBean;
import com.a3g.lanou.lbaidumusic.bean.KTVListBean;
import com.a3g.lanou.lbaidumusic.fragment.BaseFragment;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyThreadPool;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by liuHao on 17/2/13.
 */
public class KTVFragment extends BaseFragment {
    private LRecyclerView lRecyclerView;
    private KTVRecyclerAdapter ktvRecyclerAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private KTVImageBean ktvImageBean;
    private List<KTVListBean.ResultBean.ItemsBean> datas, itemsBeens;
    private Handler handler;
    private final int WHAT = 100;
    private ArrayList<Integer> arr;
    private Random random;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_ktv;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lRecyclerView = (LRecyclerView) view.findViewById(R.id.lrecycler_KTV);
    }

    @Override
    protected void initData() {
        lRecyclerView.setPullRefreshEnabled(false);
        random = new Random();
        getHandler(random);

        ktvRecyclerAdapter = new KTVRecyclerAdapter(getContext());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(ktvRecyclerAdapter);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        final View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_head_ktv, null);
        imgNet(headView);
        lRecyclerViewAdapter.addHeaderView(headView);

        ktvSongNet();
    }

    private void imgNet(final View headView) {
        NetTool.getInstance().startRequset(MyUrl.KTV_IMG_URL, KTVImageBean.class, new CallBack<KTVImageBean>() {
            @Override
            public void onSucced(KTVImageBean response) {
                ktvImageBean = response;

                Glide.with(getContext()).load(ktvImageBean.getResult().get(0).getPicture()).
                        into((ImageView) headView.findViewById(R.id.iv_first_ktv));
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private void ktvSongNet() {
        NetTool.getInstance().startRequset(MyUrl.KTV_SONG_URL, KTVListBean.class, new CallBack<KTVListBean>() {
            @Override
            public void onSucced(final KTVListBean response) {
                itemsBeens = response.getResult().getItems();
                insertItem();

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private void insertItem() {

        int a = 0;
        arr = new ArrayList<Integer>();


        while (a < 10) {
            int num = random.nextInt(itemsBeens.size());
            if (!isHas(num)) {
                arr.add(num);
                a++;
            }
        }
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(itemsBeens.get(arr.get(i)));
        }
        ktvRecyclerAdapter.setDatas(datas);
        getHandler(random);
        handler.sendEmptyMessageDelayed(WHAT,7000);


    }

    private void getHandler(final Random random) {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == WHAT) {

                    List<KTVListBean.ResultBean.ItemsBean> beanList = new ArrayList<KTVListBean.ResultBean.ItemsBean>();
                    int b = 0;
                    int c = 0;
                    ArrayList<Integer> arrayList = new ArrayList<Integer>();
                    while (b < 1) {
                        c = random.nextInt(datas.size());
                        if (!isHas(c)) {
                            b++;
                            arr.remove(datas.size()-1);
                            arrayList.add(c);
                            for (int i = 0; i < 9; i++) {
                                arrayList.add(arr.get(i));
                            }
                        }

                    }
                    arr = arrayList;
                    datas.remove(datas.size() - 1);
                    beanList.add(itemsBeens.get(c));
                    for (int i = 0; i < datas.size(); i++) {
                        beanList.add(datas.get(i));
                    }
                    datas = beanList;
                    ktvRecyclerAdapter.setDatas(datas);
                    handler.sendEmptyMessageDelayed(WHAT,7000);
                }
                return false;
            }
        });
    }

    private boolean isHas(int num){

        for (int i = 0; i <arr.size() ; i++) {
            if (num==arr.get(i)){
                return true;
            }
        }
        return false;
    }
    @Override
    protected void bindEvent() {

    }

    @Override
    public void onDestroyView() {
        handler.removeMessages(WHAT);
        super.onDestroyView();
    }


}
