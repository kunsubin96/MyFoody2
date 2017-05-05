package com.example.kunsubin.foody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GalleryFolderActivity extends AppCompatActivity {
    public static final int SINGLE_SELECT = 0;
    public static final int MULTI_SELECT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_folder);
    }
}
