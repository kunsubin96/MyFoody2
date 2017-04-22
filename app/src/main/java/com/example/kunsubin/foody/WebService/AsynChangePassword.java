package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 4/22/2017.
 */

public class AsynChangePassword extends AsyncTask<String,String,Boolean>{

    @Override
    protected Boolean doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.changePassword(strings[0],strings[1]);
    }
}
