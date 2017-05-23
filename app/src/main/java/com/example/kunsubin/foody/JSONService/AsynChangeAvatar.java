package com.example.kunsubin.foody.JSONService;

import android.os.AsyncTask;


import com.google.gson.JsonObject;

/**
 * Created by kunsubin on 5/11/2017.
 */

public class AsynChangeAvatar extends AsyncTask<Object, Object, JsonObject>{
    JsonObject jsonObject;
    public AsynChangeAvatar(JsonObject jsonObject){
        this.jsonObject=jsonObject;
    }
    //đẩy dữ liệu json hình xuống để lưu xong lấy kết quả trả về là json
    @Override
    protected JsonObject doInBackground(Object... objects) {
        return JsonHTTPHelper.makeHttpResponse(StaticJSON.URL+StaticJSON.CHANGE_AVATAR, true, jsonObject);
    }
}
