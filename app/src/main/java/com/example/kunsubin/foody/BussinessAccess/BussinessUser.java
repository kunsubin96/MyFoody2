package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.User;

import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessUser extends DataAccess {
    public BussinessUser(Context context) {
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

    public User getUser(String uid){
        this.open();
        User user=null;
        String  query = "SELECT * FROM tbl_user WHERE user_Id ='"+uid+"'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                user=new User();
                user.setId(cursor.getInt(0));
                user.setTen(cursor.getString(1));
                if(cursor.getBlob(3)!=null)
                    user.setAvatar(cursor.getBlob(3));
            }
        } finally {
            cursor.close();
            this.close();
        }
        return user;
    }
}
