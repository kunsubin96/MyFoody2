package com.example.kunsubin.foody;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kunsubin.foody.R;

/**
 * Created by kunsubin on 4/7/2017.
 */
//tab thứ 3 bên bottom bar
public class Search extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_search, container, false);
        return view;
    }
}
