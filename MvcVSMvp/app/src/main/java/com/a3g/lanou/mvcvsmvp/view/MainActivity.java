package com.a3g.lanou.mvcvsmvp.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a3g.lanou.mvcvsmvp.R;
import com.a3g.lanou.mvcvsmvp.model.User;
import com.a3g.lanou.mvcvsmvp.presenter.MyPresenter;

public class MainActivity extends AppCompatActivity implements IView{
    private EditText etName,etPassword;
    private Button btnLogin,btnClear;
    private MyPresenter myPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPresenter = new MyPresenter(this);

        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnClear = (Button) findViewById(R.id.btn_clear);
//        final User user = new User("111","111");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = etName.getText().toString();
//                String password = etPassword.getText().toString();
//                if (name.equals(user.getName())&&password.equals(user.getPwd())){
//                    Snackbar.make(btnLogin,"登录成功",3000).show();
//                }else {
//                    Snackbar.make(btnLogin,"登录失败",3000).show();
//                }
                myPresenter.login();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPresenter.clear();
            }
        });

    }

    @Override
    public String getUserName() {
        return etName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return etPassword.getText().toString();
    }

    @Override
    public void loginSuccess() {
        Snackbar.make(btnLogin,"登录成功",3000).show();
    }

    @Override
    public void loginFail() {
        Snackbar.make(btnLogin,"登录失败",3000).show();
    }

    @Override
    public void clear() {
        etName.setText("");
        etPassword.setText("");
    }
}
