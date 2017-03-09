package com.a3g.lanou.mvcvsmvp.model;

/**
 * Created by liuHao on 17/3/9.
 */
public class User {
    String name,pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
