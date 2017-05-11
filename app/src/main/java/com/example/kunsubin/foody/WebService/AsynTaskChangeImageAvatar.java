package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 5/9/2017.
 */

public class AsynTaskChangeImageAvatar extends AsyncTask<String,String,Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.uploadImageAvatar(strings[0],strings[1],strings[2]);
    }
}
