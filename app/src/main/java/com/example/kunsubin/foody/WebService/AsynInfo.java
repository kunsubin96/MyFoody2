package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.Info;

import java.util.List;

/**
 * Created by kunsubin on 5/1/2017.
 */

public class AsynInfo extends AsyncTask<String,String,Info>{
    @Override
    protected Info doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getInfo(strings[0]);
    }
}
