package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 4/23/2017.
 */

public class AsynChangeProfile extends AsyncTask<String,String,Boolean>{

    @Override
    protected Boolean doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.changeProfile(strings[0],strings[1],strings[2],strings[3]);
    }
}
