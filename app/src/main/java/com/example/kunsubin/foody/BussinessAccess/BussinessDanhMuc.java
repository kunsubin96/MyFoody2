package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.DanhMuc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessDanhMuc extends DataAccess{
    Context context;

    public BussinessDanhMuc(Context context) {
        super(context);
        this.context = context;
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
        this.open();
        List<DanhMuc> listDanhMuc= new ArrayList<>();

        String query = "SELECT * FROM tbl_danhmuc";

        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                DanhMuc item = new DanhMuc();
                item.setId(cursor.getString(0));
                item.setTen(cursor.getString(1));
                listDanhMuc.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }

        return listDanhMuc;
    }
}
