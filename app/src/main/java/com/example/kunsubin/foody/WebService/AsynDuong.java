package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.Duong;

import java.util.List;

/**
 * Created by kunsubin on 4/25/2017.
 */

public class AsynDuong extends AsyncTask<String,String,List<Duong>> {
    @Override
    protected List<Duong> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getDuong(strings[0]);
    }
}
