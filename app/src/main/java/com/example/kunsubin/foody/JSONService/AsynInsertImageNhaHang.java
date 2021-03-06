package com.example.kunsubin.foody.JSONService;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

/**
 * Created by kunsubin on 5/19/2017.
 */

public class AsynInsertImageNhaHang extends AsyncTask<Object, Object, JsonObject> {
    JsonObject jsonObject;
    public AsynInsertImageNhaHang(JsonObject jsonObject){
        this.jsonObject=jsonObject;
    }
    //đẩy dữ liệu json hình xuống để lưu xong lấy kết quả trả về là json
    @Override
    protected JsonObject doInBackground(Object... objects) {
        return JsonHTTPHelper.makeHttpResponse(StaticJSON.URL+StaticJSON.INSERT_IMAGE_NHAHANG, true, jsonObject);
    }
}
