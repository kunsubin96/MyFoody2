package com.example.kunsubin.foody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Permission.PermissionUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChangeAvatar extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linear_layout_change_avatar;
    LinearLayout linear_layout_change_cover;
    TextView text_view_save_change;
    LinearLayout back_button_change_avatar;
    CircleImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);

        init();
        linear_layout_change_avatar.setOnClickListener(this);
        linear_layout_change_cover.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);
        back_button_change_avatar.setOnClickListener(this);

        //
        if(StaticData.getObjectInfoUser().getHinh()!=null){
            Glide.with(getApplicationContext()).load(StaticData.getObjectInfoUser().getHinh()).into(profile_image);
        }

    }
    public void init(){
        back_button_change_avatar=(LinearLayout)findViewById(R.id.back_button_change_avatar);
        linear_layout_change_avatar=(LinearLayout)findViewById(R.id.linear_layout_change_avatar);
        linear_layout_change_cover=(LinearLayout)findViewById(R.id.linear_layout_change_cover);
        text_view_save_change=(TextView)findViewById(R.id.text_view_save_change);
        profile_image=(CircleImageView)findViewById(R.id.profile_image);
    }
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.select_photo_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(onMenuItemClickListener);
        popup.show();
    }

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.select_from_gallery:
                    if(PermissionUtil.isReadWritePermission(ChangeAvatar.this.getApplicationContext())){
                        Intent intent=new Intent(ChangeAvatar.this, GalleryFolderActivity.class);
                        intent.putExtra("mode",GalleryFolderActivity.SINGLE_SELECT);
                        startActivity(intent);
                        return true;
                    }
                    PermissionUtil.marshmallowReadWritePermissionCheck(ChangeAvatar.this);
                    return true;
                case R.id.select_from_camera:
                    break;
            }
            return false;
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_button_change_avatar:
                finish();
                break;
            case R.id.linear_layout_change_avatar:
                showPopup(view);
                break;
            case R.id.linear_layout_change_cover:
                showPopup(view);
                break;
            case R.id.text_view_save_change:
                break;
        }
    }
}
