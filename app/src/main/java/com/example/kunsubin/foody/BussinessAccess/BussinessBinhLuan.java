package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.BinhLuan;
import com.example.kunsubin.foody.Object.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessBinhLuan extends DataAccess{
    BussinessUser bussinessUser;
    public BussinessBinhLuan(Context context) {
        super(context);
        bussinessUser=new BussinessUser(context);
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

    public List<BinhLuan> getBinhLuanList(String res_id){
        List<BinhLuan> list=new ArrayList<>();
        this.open();
        String  query = "SELECT * FROM tbl_binhluan WHERE id_nhahang ='"+res_id+"'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                User user=bussinessUser.getUser(cursor.getString(2));
                if(user==null){
                    user=new User();
                    user.setId(0);
                    user.setTen("Anonymous");
                }
                BinhLuan item = new BinhLuan();
                item.setId(cursor.getInt(0));
                item.setIdNhaHang(cursor.getInt(1));
                item.setUserId(user);
                item.setNoiDung(cursor.getString(3));
                item.setDanhGia(cursor.getDouble(4));
                list.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }
        return list;
    }
}
