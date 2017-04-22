package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.TinhThanh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessTinhThanh extends DataAccess {
    Context context;

    public BussinessTinhThanh(Context context) {
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
        List<TinhThanh> listTinhThanh = new ArrayList<>();

        String query = "SELECT * FROM tbl_tinhthanh";

        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String maTinh = cursor.getString(0);
                String tenTinh = cursor.getString(1);
                TinhThanh item = new TinhThanh(maTinh, tenTinh);

                listTinhThanh.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }

        return listTinhThanh;
    }

}
