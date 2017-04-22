package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 4/22/2017.
 */

public class AsynCheckLogin extends AsyncTask<String,String,Integer>{
    @Override
    protected Integer doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.CheckLogin(strings[0],strings[1]);
    }
}
