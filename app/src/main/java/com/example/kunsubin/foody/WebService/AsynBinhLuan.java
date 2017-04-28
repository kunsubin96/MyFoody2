package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.BinhLuan;

import java.util.List;

/**
 * Created by kunsubin on 4/27/2017.
 */

public class AsynBinhLuan extends AsyncTask<String,String,List<BinhLuan>> {
    @Override
    protected List<BinhLuan> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getBinhLuan(strings[0]);
    }
}
