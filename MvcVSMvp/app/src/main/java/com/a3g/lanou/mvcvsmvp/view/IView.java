package com.a3g.lanou.mvcvsmvp.view;

/**
 * Created by liuHao on 17/3/9.
 */
public interface IView {
    String getUserName();
    String getUserPwd();
    void loginSuccess();
    void loginFail();
    void clear();
}
