package com.a3g.lanou.mvcvsmvp.model;

/**
 * Created by liuHao on 17/3/9.
 */
public class MyModel implements Ilogin{


    @Override
    public void login(String name, String password, IloginLisenter iloginLisenter) {
        User user = new User(name,password);
        if (name.equals("111")&&password.equals("111")){
            iloginLisenter.onSuccess(user);
        }else {
            iloginLisenter.onError();
        }
    }
}
