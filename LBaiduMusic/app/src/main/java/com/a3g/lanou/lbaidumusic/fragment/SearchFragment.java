package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.adapter.SearchContentListAdapter;
import com.a3g.lanou.lbaidumusic.bean.SearchBean;
import com.a3g.lanou.lbaidumusic.myinterface.CallBack;
import com.a3g.lanou.lbaidumusic.tools.MyUrl;
import com.a3g.lanou.lbaidumusic.tools.NetTool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by liuHao on 17/3/2.
 */
public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private EditText editText;
    private ListView contentList, saveList;
    private LinearLayout lilaContent;
    private TextView tvNot;
    private ImageView ivBack, ivSearch;
    private FragmentManager fragmentManager;
    private String edGetStr;
    private SearchContentListAdapter searchContentListAdapter;
    private static final String TAG = "SearchFragment";

    @Override
    protected int bindLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        editText = (EditText) view.findViewById(R.id.et_search);
        contentList = (ListView) view.findViewById(R.id.list_conent_search);
        saveList = (ListView) view.findViewById(R.id.search_list_search);
        tvNot = (TextView) view.findViewById(R.id.tv_not_have_search);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_search);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search_search);
        lilaContent = (LinearLayout) view.findViewById(R.id.lila_content_search);
    }

    @Override
    protected void initData() {
        fragmentManager = getActivity().getSupportFragmentManager();
        searchContentListAdapter = new SearchContentListAdapter(getContext());


    }

    @Override
    protected void bindEvent() {
        ivBack.setOnClickListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                edGetStr = editText.getText().toString();
                if (!edGetStr.isEmpty()) {


                    String mStrGBK = null;
                    try {
                        mStrGBK = URLEncoder.encode(editText.getText().toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    NetTool.getInstance().startRequset(MyUrl.SEARCH_HEAD_URL + mStrGBK + MyUrl.SEARCH_FOOT_URL, SearchBean.class, new CallBack<SearchBean>() {
                        @Override
                        public void onSucced(SearchBean response) {
                            lilaContent.setVisibility(View.GONE);
                            contentList.setVisibility(View.VISIBLE);
                            searchContentListAdapter.setSearchBean(response);
                            contentList.setAdapter(searchContentListAdapter);
                        }

                        @Override
                        public void onFailure(Throwable e) {

                        }
                    });
                }else {
                    lilaContent.setVisibility(View.VISIBLE);
                    contentList.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_search:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(this);
                fragmentTransaction.commit();
                break;
        }
    }

    public static String getUTf(String string) {

        try {
            string = new String(string.getBytes("gbk"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return string;
    }

}
