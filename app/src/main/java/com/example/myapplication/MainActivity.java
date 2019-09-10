package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.utils.MD5Utils;

public class MainActivity extends AppCompatActivity {
    /*private EditText userName,password;
    private Button btnLogin;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initData();
    }

    private void initData() {
        String username=readPref();
        if (!TextUtils.isEmpty(username)){
            userName.setText(username);
        }
    }

    private String readPref() {
        SharedPreferences sp=getSharedPreferences("userInfo",MODE_PRIVATE);
        return sp.getString("username","");
    }

    private void initView() {
        userName=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);

        TextView tvRegister=findViewById(R.id.bt_bos);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveLoginStatus(String username, boolean isLogin) {
        getSharedPreferences("userinfo",MODE_PRIVATE)
                .edit().putString("loginUser",username)
                .putBoolean("isLogin",isLogin)
                .apply();

    }

    private void initToolbar() {
        Toolbar toolbar=findViewById(R.id.title_toolbar);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }*/
    private Toolbar toolbar;
    private EditText etUsername, etPassword;
    private Button buttonRegister, buttonLogin, buttonForget;
    private TextView tUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initView();
        getUserName();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                //3.2 检查控件的有效性
                SharedPreferences pref = getSharedPreferences("userInfo", MODE_PRIVATE);
                String name = pref.getString("username", "");
                String pwd = pref.getString("password", "");
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!username.equals(name)) {
                    Toast.makeText(MainActivity.this, "用户名错误", Toast.LENGTH_SHORT).show();
                } else if (!MD5Utils.md5(password).equals(pwd)) {
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(username,true);
                    // 给bnt1添加点击响应事件
                    Intent intent = new Intent(MainActivity.this,  MainActivity.class);
                    intent.putExtra("isLogin",true);
                    //启动
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 给bnt1添加点击响应事件
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //启动
                startActivity(intent);
            }
        });
    }

    private void saveLoginStatus(String username, boolean isLogin) {
        getSharedPreferences("userInfo",MODE_PRIVATE)
                .edit().putString("loginUser",username)
                .putBoolean("isLogin",isLogin)
                .apply();

    }

    private void initToolBar() {
        toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // actionBar.setDisplayHomeAsUpEnabled(true);//设置返回键
            actionBar.setHomeButtonEnabled(true);//设置是否是首页
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }


    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        TextView tvRegister=findViewById(R.id.bt_bos);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
       // buttonRegister = findViewById(R.id.bt_bos);
        buttonLogin = findViewById(R.id.bt_log);
        buttonForget = findViewById(R.id.bt_save);
        tUsername = findViewById(R.id.et_username);
    }

    private void getUserName() {
        Intent intent = getIntent();
        String username_register = intent.getStringExtra("username");
        if (username_register == null || username_register == "") {
            tUsername.setText("");
        } else {
            tUsername.setText(username_register);
        }
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            String username=data.getStringExtra("username");
            etUsername.setText(username);
            /*if(!TextUtils.isEmpty(username)){
                etUsername.setText(username);
                etPassword.setSelection(username.length());
            }*/
        }
    }
}
