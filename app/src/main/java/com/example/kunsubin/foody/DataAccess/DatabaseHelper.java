package com.example.kunsubin.foody.DataAccess;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class DatabaseHelper extends SQLiteAssetHelper{
    private static final String DATABASE_NAME = "foody.sqlite";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }
}
