package com.a3g.lanou.lbaidumusic.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.activity.LoginActivity;
import com.a3g.lanou.lbaidumusic.adapter.FragmentMainAdapter;
import com.a3g.lanou.lbaidumusic.tools.MyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by liuHao on 17/2/11.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager vpFragmentMain;
    private TabLayout tabFragmentMain;
    private FragmentMainAdapter fragmentMainAdapter;
    private List<Fragment> datas;
    private TextView tvBack,tvName,tvLevel,tvRelogin;
    private ImageView ivLogin,ivBack,ivSearch,ivIcon;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private MainFragmentReceiver mainFragmentReceiver;
    private static final String TAG = "MainFragment";
    private SharedPreferences mSp;
    private RelativeLayout relaLogin;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        vpFragmentMain = (ViewPager) view.findViewById(R.id.vp_fragment_main);
        tabFragmentMain = (TabLayout) view.findViewById(R.id.tab_fragment_main);
        ivLogin = (ImageView) view.findViewById(R.id.iv_login_main);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drla_main);
        ivBack = (ImageView) view.findViewById(R.id.iv_back_main);
        tvBack = (TextView) view.findViewById(R.id.tv_back_main);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search_fragment_main);
        tvName = (TextView) view.findViewById(R.id.tv_name_login_main);
        tvLevel = (TextView) view.findViewById(R.id.tv_level_login_main);
        ivIcon = (ImageView) view.findViewById(R.id.iv_user_login_main);
        tvRelogin = (TextView) view.findViewById(R.id.tv_relogin_main);
        relaLogin = (RelativeLayout) view.findViewById(R.id.rela_login_main);


    }

    @Override
    protected void initData() {
        mSp = getActivity().getSharedPreferences(MyBean.MY_SP, Context.MODE_PRIVATE);

        IntentFilter intentFilter = new IntentFilter(MyBean.LOGIN);
        mainFragmentReceiver = new MainFragmentReceiver();
        getActivity().registerReceiver(mainFragmentReceiver,intentFilter);

        //如果登录了改变状态
        if (mSp.getString("name",null)!=null){
            tvName.setText(mSp.getString("name",null));
            tvLevel.setVisibility(View.GONE);
            tvRelogin.setVisibility(View.VISIBLE);
        }
        if (mSp.getString("icon",null)!=null){
            Glide.with(getContext()).load(mSp.getString("icon",null)).
                    bitmapTransform(new CropCircleTransformation(getContext())).into(ivIcon);
        }

        fragmentManager = getActivity().getSupportFragmentManager();

        //把Fragment加入集合
        datas = new ArrayList<>();

        datas.add(new MyFragment());
        datas.add(new MusicFragment());
        datas.add(new DynamicFragment());
        datas.add(new FindFragment());
         //设置Fragment适配器
        fragmentMainAdapter = new FragmentMainAdapter(getChildFragmentManager());
        fragmentMainAdapter.setDatas(datas);
        vpFragmentMain.setAdapter(fragmentMainAdapter);
        tabFragmentMain.setupWithViewPager(vpFragmentMain);
        //关闭DrawerLayout滑动
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



    }

    @Override
    protected void bindEvent() {
            ivLogin.setOnClickListener(this);
            ivBack.setOnClickListener(this);
            tvBack.setOnClickListener(this);
            ivSearch.setOnClickListener(this);
            tvRelogin.setOnClickListener(this);
            relaLogin.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mainFragmentReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_main :
                Log.e(TAG, "onClick: "+"aaaaa" );
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case R.id.iv_back_main :
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_back_main :
                drawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_search_fragment_main:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame_layout_main,new SearchFragment());
                fragmentTransaction.commit();
                break;
            case R.id.tv_relogin_main:
                clearInformation();
                ivIcon.setImageResource(R.mipmap.img_option_setting_user);
                tvRelogin.setVisibility(View.GONE);
                tvName.setText("立即登录");
                tvLevel.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MyBean.RELOGIN);
                getActivity().sendBroadcast(intent);
                break;
            case R.id.rela_login_main:
                if (!mSp.getBoolean("isLogin",false)){
                    Intent toLogin  = new Intent(getActivity(), LoginActivity.class);
                    startActivity(toLogin);
                }

        }
    }

    private void clearInformation() {
        ShareSDK.initSDK(getContext());
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        if (qq.isAuthValid()) {
        qq.removeAccount(true);
    }
        SharedPreferences.Editor editor = mSp.edit();
        editor.clear();
    }

    class MainFragmentReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: "+mSp.getString("name",null) );
            if (mSp.getString("name",null)!=null){
                tvName.setText(mSp.getString("name",null));
                tvRelogin.setVisibility(View.VISIBLE);
                tvLevel.setVisibility(View.GONE);
            }
            if (mSp.getString("icon",null)!=null){
                Glide.with(getContext()).load(mSp.getString("icon",null)).
                        bitmapTransform(new CropCircleTransformation(getContext())).into(ivIcon);
            }

        }
    }


}
