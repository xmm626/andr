package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

public class MySetActivity extends AppCompatActivity {
    private SparseArray<Fragment> fragmnets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        initView();
        initTitles();
        initFragment();
    }

    private void initTitles() {
        //创建fragment的列表
        fragmnets = new SparseArray<>();
        fragmnets.put(R.id.btn_me,MyInfoFragment.newInstance());

        //加载默认的Fragment
        replaceFragment(fragmnets.get(R.id.btn_me));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main_body,fragment);
        ft.commit();
    }

    private void initView() {
    }

    private void initFragment() {
    }

}
