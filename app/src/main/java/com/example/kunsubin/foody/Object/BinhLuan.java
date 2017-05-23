package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/8/2017.
 */
//biến data lấy bình luận
public class BinhLuan {
    int Id;
    int IdNhaHang;
    double  DanhGia;
    String NoiDung;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    String UserName;
    public ObjectInfoUser getObjectInfoUser() {
        return objectInfoUser;
    }

    public void setObjectInfoUser(ObjectInfoUser objectInfoUser) {
        this.objectInfoUser = objectInfoUser;
    }

    ObjectInfoUser objectInfoUser;
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



}
