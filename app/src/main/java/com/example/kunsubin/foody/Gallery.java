package com.example.kunsubin.foody;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kunsubin on 4/7/2017.
 */
//tab 2 bên menu bottom
public class Gallery extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gallery, container, false);
        return view;
    }
}
