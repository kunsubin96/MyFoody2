package com.example.kunsubin.foody.Object;

import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class NhaHang {
    String Id;
    String Name;
    String DiaChi;
    double DanhGia;
    String  SDT;
    byte[] Hinh;
    int LuotXem;
    String DanhMucODau;
    String DanhMucAnGi;
    List<BinhLuan> ListBinhLuan;
    List<byte[]> ListHinh;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
    public List<String> getListImage() {
        return listImage;
    }
    public void setListImage(List<String> listImage) {
        this.listImage = listImage;
    }

    List<String> listImage;
    public List<byte[]> getListHinh() {
        return ListHinh;
    }

    public void setListHinh(List<byte[]> listHinh) {
        ListHinh = listHinh;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public double getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(double danhGia) {
        DanhGia = danhGia;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public String getDanhMucODau() {
        return DanhMucODau;
    }

    public void setDanhMucODau(String danhMucODau) {
        DanhMucODau = danhMucODau;
    }

    public String getDanhMucAnGi() {
        return DanhMucAnGi;
    }

    public void setDanhMucAnGi(String danhMucAnGi) {
        DanhMucAnGi = danhMucAnGi;
    }

    public List<BinhLuan> getListBinhLuan() {
        return ListBinhLuan;
    }

    public void setListBinhLuan(List<BinhLuan> listBinhLuan) {
        ListBinhLuan = listBinhLuan;
    }


    public String getMonChinh() {
        return MonChinh;
    }

    public void setMonChinh(String monChinh) {
        MonChinh = monChinh;
    }

    String MonChinh;

    public com.example.kunsubin.foody.Object.Info getInfo() {
        return Info;
    }

    public void setInfo(com.example.kunsubin.foody.Object.Info info) {
        Info = info;
    }

    Info Info;

    public String TinhThanh;

    public String getQuanHuyen() {
        return QuanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        QuanHuyen = quanHuyen;
    }

    public String getTinhThanh() {
        return TinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        TinhThanh = tinhThanh;
    }

    public String QuanHuyen;

    public List<ImageNhaHang> getImageMoreNhaHang() {
        return ImageMoreNhaHang;
    }

    public void setImageMoreNhaHang(List<ImageNhaHang> imageMoreNhaHang) {
        ImageMoreNhaHang = imageMoreNhaHang;
    }

    public List<ImageNhaHang> ImageMoreNhaHang;

}
