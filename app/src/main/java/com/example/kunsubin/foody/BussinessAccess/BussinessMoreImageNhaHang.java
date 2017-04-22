package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessMoreImageNhaHang extends DataAccess {
    public BussinessMoreImageNhaHang(Context context) {
        super(context);
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    public List<byte[]> getListMoreImage(String id){
        List<byte[]> list = new ArrayList<>();
        this.open();
        String query = "SELECT * FROM tbl_moreImageNhaHang WHERE id ='" + id + "'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                byte[] img=cursor.getBlob(1);
                list.add(img);
            }
        } finally {
            cursor.close();
            this.close();
        }
        return list;
    }
}
