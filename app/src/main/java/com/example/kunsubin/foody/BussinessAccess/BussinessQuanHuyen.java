package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.BinhLuan;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.TinhThanh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessQuanHuyen extends DataAccess{
    Context context;

    public BussinessQuanHuyen(Context context) {
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
        List<QuanHuyen> listTinhThanh = new ArrayList<>();

        String query = "SELECT * FROM tbl_quanhuyen";

        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String maQuan = cursor.getString(0);
                String tenQuan = cursor.getString(1);
                String maTinh=cursor.getString(2);
                QuanHuyen item = new QuanHuyen();
                item.setMaQuanHuyen(maQuan);
                item.setTenQuanHuyen(tenQuan);
                item.setMaTinhThanh(maTinh);
                listTinhThanh.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }

        return listTinhThanh;
    }
    public List<QuanHuyen> getQuanHuyenTheoTinh(String MaTinh){
        List<QuanHuyen> quanHuyens=new ArrayList<>();
        String query = "SELECT * FROM tbl_quanhuyen where ma_tinhthanh='"+MaTinh+"'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                QuanHuyen item = new QuanHuyen();
                item.setMaQuanHuyen(cursor.getString(0));
                item.setTenQuanHuyen(cursor.getString(1));
                item.setMaTinhThanh(cursor.getString(2));
                quanHuyens.add(item);

            }
        } finally {
            cursor.close();
            this.close();
        }
        return quanHuyens;
    }
}
