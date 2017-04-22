package com.example.kunsubin.foody;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kunsubin on 4/7/2017.
 */
//tab 4 trong menu bottom bar
public class Notify extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_notify, container, false);
        return view;
    }
}
