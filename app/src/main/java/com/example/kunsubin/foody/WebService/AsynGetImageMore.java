package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by kunsubin on 4/27/2017.
 */

public class AsynGetImageMore extends AsyncTask<String,String,List<String>> {

    @Override
    protected List<String> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getImageMore(strings[0]);
    }
}
