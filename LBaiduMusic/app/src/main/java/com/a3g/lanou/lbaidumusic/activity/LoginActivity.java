package com.a3g.lanou.lbaidumusic.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.a3g.lanou.lbaidumusic.R;
import com.a3g.lanou.lbaidumusic.tools.MyBean;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by liuHao on 17/3/3.
 */
public class LoginActivity extends BaseActivity {
    private ImageView ivWeibo,ivQQ,ivBack;
    private static final String TAG = "LoginActivity";

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ivWeibo = (ImageView) findViewById(R.id.iv_weibo);
        ivQQ = (ImageView) findViewById(R.id.iv_qq);
        ivBack = (ImageView) findViewById(R.id.iv_back_login_activity);
    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(this);


    }

    @Override
    protected void bindEvent() {
        ivWeibo.setOnClickListener(this);
        ivQQ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_weibo:
                weiboSign();
                break;
            case R.id.iv_qq:
                qqSign();
                break;
        }
    }

    private void weiboSign() {
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        weibo.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
                Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                finish();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        //authorize与showUser单独调用一个即可
        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
        weibo.showUser(null);//授权并获取用户信息
    //移除授权
    //weibo.removeAccount(true);
    }
    private void qqSign() {
        Platform qq = ShareSDK.getPlatform(cn.sharesdk.tencent.qq.QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                arg0.getDb().exportData();
                if (arg1 == Platform.ACTION_USER_INFOR) {

                    PlatformDb platDB = arg0.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    Log.e(TAG, "onComplete: "+platDB.getUserName() );
                    SharedPreferences mSp = getSharedPreferences(MyBean.MY_SP,MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSp.edit();
                    editor.putString("name",platDB.getUserName());
                    editor.putString("icon",platDB.getUserIcon());
                    editor.putBoolean("isLogin",true);
                    editor.commit();
                    Intent intent = new Intent(MyBean.LOGIN);
                    sendBroadcast(intent);
                    finish();
                }


            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        //authorize与showUser单独调用一个即可
        qq.authorize();//单独授权,OnComplete返回的hashmap是空的
        qq.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }
}
