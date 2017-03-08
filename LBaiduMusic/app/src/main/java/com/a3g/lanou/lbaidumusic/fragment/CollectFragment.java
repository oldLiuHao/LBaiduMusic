package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.a3g.lanou.lbaidumusic.Collect;
import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.CollectListAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by liuHao on 17/3/4.
 */
public class CollectFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<Collect> collects;
    private ImageView ivOne,ivTwo,ivThree,ivBack;
    private CollectListAdapter collectListAdapter;
    private ListView listView;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivOne = (ImageView) view.findViewById(R.id.iv_one_collect);
        ivTwo = (ImageView) view.findViewById(R.id.iv_two_collect);
        ivThree = (ImageView) view.findViewById(R.id.iv_three_collect);
        listView = (ListView) view.findViewById(R.id.list_collect);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_collect);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        collects = bundle.getParcelableArrayList("collectFragment") ;
        if (collects!=null){
            if (collects.size()==1){
                Glide.with(getContext()).load(collects.get(0).getImage()).into(ivThree);
            }
            if (collects.size()==2){
                Glide.with(getContext()).load(collects.get(0).getImage()).into(ivThree);
                Glide.with(getContext()).load(collects.get(1).getImage()).into(ivTwo);
            }
            if (collects.size()>=3){
                Glide.with(getContext()).load(collects.get(collects.size()-1).getImage()).into(ivThree);
                Glide.with(getContext()).load(collects.get(collects.size()-2).getImage()).into(ivTwo);
                Glide.with(getContext()).load(collects.get(collects.size()-3).getImage()).into(ivOne);
            }
            collectListAdapter = new CollectListAdapter();
            listView.setAdapter(collectListAdapter);
            collectListAdapter.setCollects(collects);

        }
    }

    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_collect:
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(this).commit();
                break;
        }
    }
}
