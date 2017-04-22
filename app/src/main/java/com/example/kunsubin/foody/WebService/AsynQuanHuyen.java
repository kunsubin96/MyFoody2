package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.QuanHuyen;

import java.util.List;

/**
 * Created by kunsubin on 4/21/2017.
 */

public class AsynQuanHuyen extends AsyncTask<String,String,List<QuanHuyen>> {
    @Override
    protected List<QuanHuyen> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getAllQuanHuyen();
    }
}
