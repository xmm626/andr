package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.utils.MD5Utils;

public class ChangeActivity extends AppCompatActivity {
    private EditText etPassword,newPassword,newRePassword;
    private Button btSave,mimaSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        initToolbar();
        initControl();

        final Intent intent = getIntent();
        Boolean isVis = intent.getBooleanExtra("isPwd",false);
        if (isVis.equals(true)){
            etPassword.setVisibility(View.GONE);
            btSave.setVisibility(View.GONE);
            mimaSave.setVisibility(View.VISIBLE);
        }

        mimaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpwd = newPassword.getText().toString();
                String repwd = newRePassword.getText().toString();

                final Intent intent1 = getIntent();
                String username = intent1.getStringExtra("username");
                System.out.println(username);

                if (TextUtils.isEmpty(newpwd) && TextUtils.isEmpty(repwd)){
                    Toast.makeText(ChangeActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }else if (!newpwd.equals(repwd)){
                    Toast.makeText(ChangeActivity.this,"密码前后不一样",Toast.LENGTH_LONG).show();
                }else if (newpwd.equals(repwd)){
                    savePref(username, MD5Utils.md5(newpwd));
                    Intent intent = new Intent(ChangeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd = etPassword.getText().toString();
                String newpwd = newPassword.getText().toString();
                String repwd = newRePassword.getText().toString();

                SharedPreferences pref = getSharedPreferences("userInfo",MODE_PRIVATE);
                String username = pref.getString("loginUser","");
                String password = pref.getString(username,"");
                System.out.println("password: " + password);
                if (TextUtils.isEmpty(pwd) && TextUtils.isEmpty(newpwd) && TextUtils.isEmpty(repwd)){
                    Toast.makeText(ChangeActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }else if (!newpwd.equals(repwd)){
                    Toast.makeText(ChangeActivity.this,"请重新确认密码",Toast.LENGTH_LONG).show();
                }else if (!MD5Utils.md5(pwd).equals(password)){
                    Toast.makeText(ChangeActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                }else if(newpwd.equals(repwd)){
                    savePref(username, MD5Utils.md5(newpwd));
                    Intent intent = new Intent(ChangeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initControl() {
        etPassword = findViewById(R.id.et_password);
        newPassword = findViewById(R.id.new_password);
        newRePassword = findViewById(R.id.new_repassword);
        btSave = findViewById(R.id.bt_save);
        mimaSave = findViewById(R.id.mima_save);
    }

    private void savePref(String username, String password) {
        SharedPreferences.Editor editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit();
        editor.putString(username, password);
        editor.apply();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("修改密码");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity.this.finish();
            }
        });
    }
}
