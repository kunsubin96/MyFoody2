package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.StaticData;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by kunsubin on 4/7/2017.
 */
//tab 5 bên bottom bar
public class User extends Fragment {
    LinearLayout layOutDangNhap;
    CircleImageView avatarUser;
    TextView textHoTen;
    TextView textXemHoatDong;
    RelativeLayout thongTinLienHe;
    RelativeLayout thietLapTaiKhoan;
    RelativeLayout tienThuong;
    RelativeLayout thanhToan;
    RelativeLayout lichSuDatCho;
    RelativeLayout lichSuDatGiaoHang;
    RelativeLayout lichSuCoupon;
    RelativeLayout suDungEcard;
    RelativeLayout lichSuEat;
    LinearLayout dangXuat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_user, container, false);

        //ánh xạ các view
        layOutDangNhap=(LinearLayout)view.findViewById(R.id.layOutDangNhap);
        avatarUser=(CircleImageView)view.findViewById(R.id.avatarUser);
        textHoTen=(TextView) view.findViewById(R.id.textHoTen);
        textXemHoatDong=(TextView)view.findViewById(R.id.textXemHoatDong);
        thongTinLienHe=(RelativeLayout)view.findViewById(R.id.thongTinLienHe);
        thietLapTaiKhoan=(RelativeLayout)view.findViewById(R.id.thietLapTaiKhoan);
        tienThuong=(RelativeLayout)view.findViewById(R.id.tienThuong);
        thanhToan=(RelativeLayout)view.findViewById(R.id.thanhToan);
        lichSuDatCho=(RelativeLayout)view.findViewById(R.id.lichSuDatCho);
        lichSuDatGiaoHang=(RelativeLayout)view.findViewById(R.id.lichSuDatGiaoHang);
        lichSuCoupon=(RelativeLayout)view.findViewById(R.id.lichSuCoupon);
        suDungEcard=(RelativeLayout)view.findViewById(R.id.suDungEcard);
        lichSuEat=(RelativeLayout)view.findViewById(R.id.lichSuEat);
        dangXuat=(LinearLayout)view.findViewById(R.id.dangXuat);

        //trạng thái đăng xuất ban đầu
        if(StaticData.getObjectInfoUser()==null){
            dangXuat.setVisibility(View.GONE);
        }else {
            dangXuat.setVisibility(View.VISIBLE);
        }
        //dang nhap
        layOutDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }else {

                }

            }
        });
        //thông tin liên hệ
        thongTinLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }else{
                    Intent intent = new Intent(getContext(), ThongTinLienHe.class);
                    startActivityForResult(intent, 4);
                   // textHoTen.setText(StaticData.getObjectInfoUser().getHoTen());

                }
            }
        });
        //thiết lập tài khoản
        thietLapTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }else{
                    Intent intent = new Intent(getContext(), ThietLapTaiKhoan.class);
                    startActivityForResult(intent,19);
                }
            }
        });
        //tiền thưởng
        tienThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //thanh toán
        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //lịch sử đặt chỗ
        lichSuDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //lịch sử giao hàng
        lichSuDatGiaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //lịch sử Coupon
        lichSuCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //sử dụng Ecard
        suDungEcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //lịch sử Eat
        lichSuEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        //đăng xuất
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất khỏi Foody?");


                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
                builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        textHoTen.setText("Đăng nhập");
                        avatarUser.setImageResource(R.drawable.icon_user_not_login);
                        textXemHoatDong.setVisibility(View.GONE);
                        //set user null
                        StaticData.setObjectInfoUser(null);
                        //
                        dangXuat.setVisibility(View.GONE);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        return view;
    }
    //nhận kết quả trả về
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //kết quả trả về của form đăng nhập
        if (requestCode == 3) {
            //đăng nhập thành công
            if (resultCode == Activity.RESULT_OK) {
                //set lại các giá trị cho user
                ObjectInfoUser objectInfoUser= StaticData.getObjectInfoUser();
                textHoTen.setText(objectInfoUser.getHoTen());
                //set avatar cho user
                if(objectInfoUser.getHinh()!=null){
                    Glide.with(getContext()).load(objectInfoUser.getHinh()).into(avatarUser);
                }
                textXemHoatDong.setVisibility(View.VISIBLE);
                dangXuat.setVisibility(View.VISIBLE);
               // Toast.makeText(getContext(),objectInfoUser.getHoTen(),Toast.LENGTH_LONG).show();

            }
            if(requestCode==Activity.RESULT_CANCELED){

            }
        }
        //kết quả trả về của chỉnh sữa thông tin cá nhân
        if(requestCode==4){
            if (resultCode == Activity.RESULT_OK) {
                textHoTen.setText(StaticData.getObjectInfoUser().getHoTen());
            }
            if(requestCode==Activity.RESULT_CANCELED){

            }
        }
        //kết quả trả về của thiết lập tài khoản
        if(requestCode==19){
            if (resultCode == Activity.RESULT_OK) {
                //cập nhật lại hình avarta khi có sự chỉnh sửa hình
                if(StaticData.getObjectInfoUser().getHinh()!=null){
                    Glide.with(getContext()).load(StaticData.getObjectInfoUser().getHinh()).into(avatarUser);
                }
            }
            if(requestCode==Activity.RESULT_CANCELED){

            }
        }
    }
}
