package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.MonAn;

import java.util.List;

/**
 * Created by kunsubin on 5/1/2017.
 */

public class AsynMonAn extends AsyncTask<String,String,List<MonAn>> {
    @Override
    protected List<MonAn> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getAllMonAn();
    }
}
