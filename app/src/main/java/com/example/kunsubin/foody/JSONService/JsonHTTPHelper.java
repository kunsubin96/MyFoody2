package com.example.kunsubin.foody.JSONService;

import android.support.annotation.Nullable;

import com.example.kunsubin.foody.Object.StaticData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kunsubin on 5/11/2017.
 */

public class JsonHTTPHelper {
    public static JsonObject makeHttpResponse(String url, boolean method, @Nullable JsonObject jsonObjectInput) {

        URLConnection connection = getHTTPConnection(url, method);
        if(connection==null)
            return StaticData.GET_ERROR_OBJECT();
        if (jsonObjectInput != null) {
            postObject(jsonObjectInput, connection);
        }

        return getJsonResponse(connection);

    }

    private static void postObject(JsonObject jsonObjectInput, URLConnection connection) {

        try {
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObjectInput.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static JsonObject getJsonResponse(URLConnection connection) {

        JSONObject jsonObject = getJSONObjectFromConnection(connection);
        return convertJSONObject2JSonObject(jsonObject);

    }

    private static JsonObject convertJSONObject2JSonObject(JSONObject input) {
        if (input != null) {
            return (JsonObject) new JsonParser().parse(input.toString());
        }
        return StaticData.GET_ERROR_OBJECT();

    }

    private static URLConnection getHTTPConnection(String strURL, boolean method) {
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(strURL);
            connection = url.openConnection();
            if (method == true) {
                connection.setDoOutput(true);
            } else if (method == false) {
                connection.setDoOutput(false);
            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static JSONObject getJSONObjectFromConnection(URLConnection connection) {
        JSONObject output = null;
        if(connection!=null){
            InputStream in = null;
            try {
                in = new BufferedInputStream(connection.getInputStream());
                String result = IOUtils.toString(in, "UTF-8");
                output = new JSONObject(result);
            } catch (IOException |JSONException e) {
                e.printStackTrace();
            }
        }
        return output;
    }
}
