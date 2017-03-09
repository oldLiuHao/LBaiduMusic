package com.a3g.lanou.mvcvsmvp.presenter;

import com.a3g.lanou.mvcvsmvp.model.IloginLisenter;
import com.a3g.lanou.mvcvsmvp.model.MyModel;
import com.a3g.lanou.mvcvsmvp.model.User;
import com.a3g.lanou.mvcvsmvp.view.IView;

/**
 * Created by liuHao on 17/3/9.
 */
public class MyPresenter {
    private MyModel myModel;
    private IView iView;

    public MyPresenter(IView iView) {
        this.iView = iView;
        myModel = new MyModel();
    }

    public void login(){
        myModel.login(iView.getUserName(), iView.getUserPwd(), new IloginLisenter() {
            @Override
            public void onSuccess(User user) {
                iView.loginSuccess();
            }

            @Override
            public void onError() {
                iView.loginFail();
            }
        });
    }
    public void clear(){
        iView.clear();
    }

}
