package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.DanhMuc;

import java.util.List;

/**
 * Created by kunsubin on 5/1/2017.
 */

public class AsynDanhMuc extends AsyncTask<String,String,List<DanhMuc>> {
    @Override
    protected List<DanhMuc> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getAllDanhMuc();
    }
}
