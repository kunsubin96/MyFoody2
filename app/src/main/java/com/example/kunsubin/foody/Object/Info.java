package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 4/10/2017.
 */

public class Info {
    String Id;
    byte[] Photo;

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
