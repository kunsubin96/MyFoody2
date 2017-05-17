package com.example.kunsubin.foody.Object;

/**
 * Created by kunsubin on 5/17/2017.
 */

public class MenuBarItemBean {
    private String title;
    private String id;
    private String image;
    private boolean isSelected;

    //Hàm khởi tạo item cho TabMenu
    public MenuBarItemBean(String id, String title, String image, boolean isSelected) {
        this.title = title;
        this.image = image;
        this.isSelected = isSelected;
        this.id = id;
    }

    //Hàm get ID
    public String getId() {
        return id;
    }

    //Hàm set ID
    public void setId(String id) {
        this.id = id;
    }

    //Hàm get tiêu đề
    public String getTittle() {
        return title;
    }

    //Hàm set tiêu đề
    public void setTittle(String tittle) {
        this.title = tittle;
    }

    //Hàm get ảnh (trả về id trong Drawable)
    public String getImage() {
        return image;
    }

    //Hàm set ảnh (trả về id trong Drawable)
    public void setImage(String image) {
        this.image = image;
    }

    //Hàm kiểm tra  xem item có được chọn không
    public boolean isSelected() {
        return isSelected;
    }

    //Hàm đặt trạng thái được chọn cho item
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
