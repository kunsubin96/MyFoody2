package com.example.kunsubin.foody.WebService;

import android.os.AsyncTask;


import com.example.kunsubin.foody.Object.NhaHang;

import java.util.List;

/**
 * Created by kunsubin on 5/1/2017.
 */

public class AsynNhaHangAnGi extends AsyncTask<String,String,List<NhaHang>> {
    @Override
    protected List<NhaHang> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getNhaHangAnGi(strings[0],strings[1],strings[2],strings[3],strings[4]);
    }
}
