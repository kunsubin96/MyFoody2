package com.example.kunsubin.foody.WebService;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.kunsubin.foody.MainActivity;
import com.example.kunsubin.foody.ODau;
import com.example.kunsubin.foody.Object.NhaHang;

import java.util.List;

/**
 * Created by kunsubin on 4/27/2017.
 */

public class AsynNhaHang extends AsyncTask<String,String,List<NhaHang>> {
    @Override
    protected List<NhaHang> doInBackground(String... strings) {
        WebService webService=new WebService();
        return webService.getNhaHang(strings[0],strings[1],strings[2],strings[3],strings[4]);
    }
}
