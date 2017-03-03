package com.a3g.lanou.lbaidumusic.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.myinterface.JumpInter;

/**
 * Created by liuHao on 17/2/21.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvBack;
    private JumpInter jumpInter;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ivBack = (ImageView) view.findViewById(R.id.iv_back_login);
        tvBack = (TextView) view.findViewById(R.id.tv_back_login);
    }

    @Override
    protected void initData() {
        ivBack.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        jumpInter = (JumpInter) getActivity();
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_login:
                jumpInter.LoginDemiss();
                break;
            case R.id.tv_back_login:
                jumpInter.LoginDemiss();
                break;


        }


    }
}
