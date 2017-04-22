package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/22/2017.
 */

public class ObjectInfoUser {
    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static String getAvatar() {
        return Avatar;
    }

    public static void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public static String getSDT() {
        return SDT;
    }

    public static void setSDT(String SDT) {
        ObjectInfoUser.SDT = SDT;
    }

    public static String getDiaChi() {
        return DiaChi;
    }

    public static void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public static String getHoTen() {
        return HoTen;
    }

    public static void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public static byte[] getHinh() {
        return hinh;
    }

    public static void setHinh(byte[] hinh) {
        ObjectInfoUser.hinh = hinh;
    }

    public static byte[] hinh;
    public static String Username;
    public static String HoTen;
    public static String DiaChi;
    public static String SDT;
    public static String Avatar;
}
