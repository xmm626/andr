package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.utils.MD5Utils;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername,etPassword,etPadAgain;
    private Button btnBos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        initView();
        btnBos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();
                String padagain=etPadAgain.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(padagain)){
                    Toast.makeText(RegisterActivity.this,"两次密码必须一致",Toast.LENGTH_SHORT).show();
                }else{
                    savePref(username,MD5Utils.md5(password));
                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });
    }

    private void savePref(String username,String password) {
        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.apply();
    }

    private void initView() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPadAgain = findViewById(R.id.et_repassword);
        btnBos = findViewById(R.id.bt_bos);
    }
}
