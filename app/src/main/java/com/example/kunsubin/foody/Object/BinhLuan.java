package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BinhLuan {
    int Id;
    User UserId;
    int IdNhaHang;
    double  DanhGia;
    String NoiDung;
    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public double getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(double danhGia) {
        DanhGia = danhGia;
    }

    public int getIdNhaHang() {
        return IdNhaHang;
    }

    public void setIdNhaHang(int idNhaHang) {
        IdNhaHang = idNhaHang;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User userId) {
        UserId = userId;
    }


}
