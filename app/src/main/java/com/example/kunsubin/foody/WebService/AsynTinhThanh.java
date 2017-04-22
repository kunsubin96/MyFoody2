package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.TinhThanh;

import java.util.List;

/**
 * Created by kunsubin on 4/21/2017.
 */

public class AsynTinhThanh extends AsyncTask<String,String,List<TinhThanh>> {
    @Override
    protected List<TinhThanh> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getAllTinhThanh();
    }
}
