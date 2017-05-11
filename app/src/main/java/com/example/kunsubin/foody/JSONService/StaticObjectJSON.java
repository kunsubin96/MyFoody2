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
    public static JsonObject createImageInputObject(String path) {
        JsonObject outputObject = null;

        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();


        String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);


        outputObject = new JsonObject();

        outputObject.addProperty("userid", StaticData.getObjectInfoUser().getUsername().trim());
        outputObject.addProperty("id", UUID.randomUUID().toString());
        outputObject.addProperty("image", str);


        return outputObject;
    }
}
