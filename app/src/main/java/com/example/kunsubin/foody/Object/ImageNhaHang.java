package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 5/18/2017.
 */

public class ImageNhaHang {
    private String id;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdNhaHang() {
        return idNhaHang;
    }

    public void setIdNhaHang(String idNhaHang) {
        this.idNhaHang = idNhaHang;
    }

    private String idNhaHang;
    private String photo;

    public ImageNhaHang(String id, String idNhaHang, String photo) {
        super();
        this.id = id;
        this.idNhaHang = idNhaHang;
        this.photo = photo;
    }

}
