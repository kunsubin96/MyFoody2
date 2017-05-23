package com.example.kunsubin.foody.Object;

import java.util.ArrayList;

/**
 * Created by kunsubin on 5/6/2017.
 */
//biến object data FolderGalleryBean chứ các foder chứa hình get được trong bộ nhớ máy
public class FolderGalleryBean {
    String folder;
    ArrayList<ImageGalleryBean> imageInFolder;

    public FolderGalleryBean(){}

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<ImageGalleryBean> getImageInFolder() {
        return imageInFolder;
    }

    public void setImageInFolder(ArrayList<ImageGalleryBean> imageInFolder) {
        this.imageInFolder = imageInFolder;
    }
}
