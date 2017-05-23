package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.BaseSlideActivity.BaseSlideActivity;
import com.example.kunsubin.foody.JSONService.AsynChangeAvatar;
import com.example.kunsubin.foody.JSONService.StaticObjectJSON;
import com.example.kunsubin.foody.Object.ImageGalleryBean;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Permission.Permission;
import com.example.kunsubin.foody.WebService.AsynGetImage;
import com.example.kunsubin.foody.WebService.AsynGetInfoUser;
import com.google.gson.JsonObject;

import org.kobjects.base64.Base64;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;




public class ChangeAvatar extends BaseSlideActivity implements View.OnClickListener{

    LinearLayout linear_layout_change_avatar;
    LinearLayout linear_layout_change_cover;
    TextView text_view_save_change;
    LinearLayout back_button_change_avatar;
    CircleImageView profile_image;
    ImageView cover_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);

        init();
        //set sự kiện click cho các view
        linear_layout_change_avatar.setOnClickListener(this);
        linear_layout_change_cover.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);
        back_button_change_avatar.setOnClickListener(this);

        //đưa hình user lên CircleImageView
        if(StaticData.getObjectInfoUser().getHinh()!=null){
            Glide.with(getApplicationContext()).load(StaticData.getObjectInfoUser().getHinh()).into(profile_image);
        }

    }
    //ánh xạ các view
    public void init(){
        back_button_change_avatar=(LinearLayout)findViewById(R.id.back_button_change_avatar);
        linear_layout_change_avatar=(LinearLayout)findViewById(R.id.linear_layout_change_avatar);
        linear_layout_change_cover=(LinearLayout)findViewById(R.id.linear_layout_change_cover);
        text_view_save_change=(TextView)findViewById(R.id.text_view_save_change);
        profile_image=(CircleImageView)findViewById(R.id.profile_image);
        cover_image=(ImageView)findViewById(R.id.cover_image);
    }
    //show menu chọn hình hoặc chụp ảnh
    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.select_photo_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(onMenuItemClickListener);
        popup.show();
    }
    //sự kiện chọn menu chọn hình từ thư viện hoặc chụp hình
    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.select_from_gallery:
                    //nếu là chọn hình thì tiến hành mở activity tiếp theo cho việc chọn hình
                    if(Permission.isReadWritePermission(ChangeAvatar.this.getApplicationContext())){
                        Intent intent=new Intent(ChangeAvatar.this, GalleryFolderActivity.class);
                        intent.putExtra("mode",GalleryFolderActivity.SINGLE_SELECT);
                        startActivityForResult(intent,17);
                        return true;
                    }
                    Permission.marshmallowReadWritePermissionCheck(ChangeAvatar.this);
                    return true;
                case R.id.select_from_camera:
                    break;
            }
            return false;
        }
    };
    ImageGalleryBean uploadAvatar = null;
    ImageGalleryBean uploadCover = null;
    //nhận kết quả trả về là hình
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 17) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<ImageGalleryBean> dataReponse = new ArrayList<>();
                dataReponse = data.getParcelableArrayListExtra("images");
                if(dataReponse!=null && dataReponse.size()>0){
                    updateImageView(dataReponse.get(0));
                }
            }
        }
    }
    boolean changeavatar = false;
    boolean changecover = false;
    //cập nhật trạng thái image view avatar hoặc ảnh bìa
    public void updateImageView(ImageGalleryBean image) {
        if (this.changeavatar) {
            this.changeavatar = false;
            this.uploadAvatar = image;
            Glide.with(this.getApplicationContext()).load("file://" + uploadAvatar.getPath()).into(profile_image);
        }
        if (this.changecover) {
            this.changecover = false;
            this.uploadCover = image;
            Glide.with(this.getApplicationContext()).load("file://" + uploadCover.getPath()).into(cover_image);
        }
    }
    //sự kiện cho các view
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //trở về
            case R.id.back_button_change_avatar:
                finish();
                break;
            //chọn layout avarta rùi show menu
            case R.id.linear_layout_change_avatar:
                this.changeavatar = true;
                this.changecover = false;
                showPopup(view);
                break;
            //chọn layout ảnh bìa rùi show menu
            case R.id.linear_layout_change_cover:
                this.changecover = true;
                this.changeavatar = false;
                showPopup(view);
                break;
            //lưu hình xuống
            case R.id.text_view_save_change:

                if(uploadAvatar!=null){
                   uploadAvatar(uploadAvatar);
                }else {
                    this.finish();
                }

                break;
        }
    }
    //upload hình đại diện cho user
    public void uploadAvatar(ImageGalleryBean uploadAvatar){


        /*Bitmap myBitmap=BitmapFactory.decodeFile(uploadAvatar.getPath());
        
        //Bitmap sacledBitmap=Bitmap.createScaledBitmap(myBitmap,500,500,true);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG,0,bos);
        byte[] bytes=bos.toByteArray();

        final String encImage = android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP);

        AsynTaskChangeImageAvatar asynTaskChangeImageAvatar=new AsynTaskChangeImageAvatar();
        try {
            boolean f=asynTaskChangeImageAvatar.execute(encImage,"guid",
                    StaticData.getObjectInfoUser().getUsername().toString().trim()).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        //Toast.makeText(getApplicationContext(),encImage,Toast.LENGTH_LONG).show();
        //tạo đối tượng jsonInputImage
        JsonObject input= StaticObjectJSON.createImageInputObject(uploadAvatar.getPath());
        AsynChangeAvatar asynChangeAvatar=new AsynChangeAvatar(input);
        try {
            //thực thi thao tác cập nhật ảnh
            JsonObject object= asynChangeAvatar.execute().get();
            Log.d("hạhfds",object.toString());
            //lấy kết quả
            String bool=object.get("success").toString();
            Boolean f=Boolean.parseBoolean(bool);

            if(f){
                //kết quả thành công
                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeAvatar.this);
                builder.setMessage("Thay đổi avatar thành công!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        AsynGetInfoUser user=new AsynGetInfoUser();
                        try {
                            //cập nhật lại user và avatar cho user
                            ObjectInfoUser user1=user.execute(StaticData.getObjectInfoUser().getUsername()).get();
                            AsynGetImage asynGetImage=new AsynGetImage();
                            String stringImage=asynGetImage.execute(user1.getAvatar()).get();
                            byte[] valueDecoded = Base64.decode(stringImage);
                            if(valueDecoded!=null)
                                    user1.setHinh(valueDecoded);
                            if(user1!=null)
                                StaticData.setObjectInfoUser(user1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        //trả kết quả về activity khi thành công
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }else{
                //thất bại
                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeAvatar.this);
                builder.setMessage("Thay đổi avatar thất bại!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
