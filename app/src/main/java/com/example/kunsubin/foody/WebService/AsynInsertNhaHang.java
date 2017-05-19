package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;

/**
 * Created by kunsubin on 5/18/2017.
 */

public class AsynInsertNhaHang extends AsyncTask<String,String,Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.insertNhaHang(strings[0],strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
    }
}
