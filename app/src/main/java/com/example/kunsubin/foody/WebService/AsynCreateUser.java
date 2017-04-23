package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 4/23/2017.
 */

public class AsynCreateUser extends AsyncTask<String,String,Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.createUser(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5]);
    }
}
