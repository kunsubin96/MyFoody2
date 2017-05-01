package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/10/2017.
 */

public class Info {
    String Id;
    byte[] Photo;

    public String getID_NhaHang() {
        return ID_NhaHang;
    }

    public void setID_NhaHang(String ID_NhaHang) {
        this.ID_NhaHang = ID_NhaHang;
    }

    String ID_NhaHang;
    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    String Avatar;
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public byte[] getPhoto() {
        return Photo;
    }

    public void setPhoto(byte[] photo) {
        Photo = photo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Date;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    String Name;
}
