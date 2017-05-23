package com.example.kunsubin.foody.JSONService;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.kunsubin.foody.Object.StaticData;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by kunsubin on 5/11/2017.
 */

public class StaticObjectJSON {
    //tạo một đối tượng jsson gồm có 3 đối tượng userid,id,image
    public static JsonObject createImageInputObject(String path) {
        JsonObject outputObject = null;
        //get image từ đường dẫn chuyển thành dạng byte
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();

        //encode chuyển byte[] thành StringBase64
        String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);

        //tạo jsson object
        outputObject = new JsonObject();

        outputObject.addProperty("userid", StaticData.getObjectInfoUser().getUsername().trim());
        outputObject.addProperty("id", UUID.randomUUID().toString());
        outputObject.addProperty("image", str);


        return outputObject;
    }
    //tạo một đối tượng jsson gồm có 3 đối tượng nhahangid,id,image
    public static JsonObject createImageInputNhaHangObject(String path,String nhahangid) {
        JsonObject outputObject = null;
        //get image từ đường dẫn chuyển thành dạng byte
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();

        //encode chuyển byte[] thành StringBase64
        String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);

        //tạo jsson object
        outputObject = new JsonObject();

        outputObject.addProperty("nhahangid", nhahangid);
        outputObject.addProperty("id", UUID.randomUUID().toString());
        outputObject.addProperty("image", str);


        return outputObject;
    }
}
