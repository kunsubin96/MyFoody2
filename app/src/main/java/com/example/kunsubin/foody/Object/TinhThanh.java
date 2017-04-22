package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/6/2017.
 */

public class TinhThanh {
    String MaTinhThanh;
    String TenTinhThanh;
    public TinhThanh(String maTinhThanh, String tenTinhThanh){
        MaTinhThanh=maTinhThanh;
        TenTinhThanh=tenTinhThanh;
    }
    public TinhThanh(){

    }
    public String getTenTinhThanh() {
        return TenTinhThanh;
    }

    public void setTenTinhThanh(String tenTinhThanh) {
        TenTinhThanh = tenTinhThanh;
    }

    public String getMaTinhThanh() {
        return MaTinhThanh;
    }

    public void setMaTinhThanh(String maTinhThanh) {
        MaTinhThanh = maTinhThanh;
    }





}
