package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TopActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        final RadioGroup rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0;i<rg_main.getChildCount();i++){
                    RadioButton rb = (RadioButton)group.getChildAt(i);
                    if(rb.isChecked()){
                        setIndexSelectedTwo(i);
//                        RadioButton rb_main = (RadioButton) findViewById(R.id.rb_main);
//                        rb_main.setTextColor(Color.parseColor("#EEEE00"));
                        break;
                    }
                }
            }

            private void setIndexSelectedTwo(int index) {
                switch (index)
                {
                    case 0:
                        RadioButton rb_main = (RadioButton) findViewById(R.id.rb_main);
                        rb_main.setTextColor(Color.parseColor("#EEEE00"));
                        changeFragment(new MainFragment().getMainFragment());
                        break;
                    case 1:
                        changeFragment(new MessageFragment().getMessageFragment());
                        break;
                    case 2:
                        changeFragment(new FindFragment().getFindFragment());
                        break;
                    case 3:
                        changeFragment(new MyFragment().getMyFragment());
                        break;
                    default:
                        break;
                }
            }

            private void changeFragment(Fragment fragment) {
                FragmentManager fragmentManager = getSupportFragmentManager();//开启事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment , fragment);
                transaction.commit();
            }
        });
    }
}
