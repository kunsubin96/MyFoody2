package com.example.kunsubin.foody;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.Object.StaticData;

public class ChiTietNhaHang extends AppCompatActivity {

    ImageView info_imv;
    TextView text_view_num_of_comment;
    TextView text_view_num_of_photo;
    TextView info_tvaddress;
    LinearLayout back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nha_hang);
        init();
        //lấy thông tin nha hàng
        NhaHang nhaHang= StaticData.getNhaHang();
        //show lên nội dung chi tiết của nhà hàng
        if(nhaHang!=null){
            if(nhaHang.getHinh()!=null)
                Glide.with(getApplicationContext()).load(nhaHang.getHinh()).into(info_imv);
            if(nhaHang.getListBinhLuan()!=null)
                text_view_num_of_comment.setText(String.valueOf(nhaHang.getListBinhLuan().size()));
            if(nhaHang.getListHinh()!=null){
                text_view_num_of_photo.setText(String.valueOf(nhaHang.getListHinh().size()+1));
            }else {
                text_view_num_of_photo.setText("1");
            }
            info_tvaddress.setText(nhaHang.getDiaChi());
        }
        //trở về
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //ánh xạ các view
    private void init(){
        info_imv=(ImageView)findViewById(R.id.info_imv);
        text_view_num_of_comment=(TextView)findViewById(R.id.text_view_num_of_comment);
        text_view_num_of_photo=(TextView)findViewById(R.id.text_view_num_of_photo);
        info_tvaddress=(TextView)findViewById(R.id.info_tvaddress);
        back_button=(LinearLayout)findViewById(R.id.back_button);
    }
}
