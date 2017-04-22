package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 4/22/2017.
 */

public class AsynGetImage extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getImage(strings[0]);
    }
}
