package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FindFragment extends Fragment {
    public static FindFragment findf;

    public static Fragment getFindFragment(){
        if (findf == null){
            findf = new FindFragment();
        }
        return findf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        return view;
    }
}
