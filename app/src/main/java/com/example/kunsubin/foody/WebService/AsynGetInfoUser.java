package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

import com.example.kunsubin.foody.Object.ObjectInfoUser;

/**
 * Created by kunsubin on 4/22/2017.
 */

public class AsynGetInfoUser extends AsyncTask<String,String,ObjectInfoUser>{

    @Override
    protected ObjectInfoUser doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getInfoUser(strings[0]);
    }
}
