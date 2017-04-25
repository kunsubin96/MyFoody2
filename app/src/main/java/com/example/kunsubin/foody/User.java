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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        thietLapTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }else{
                    Intent intent = new Intent(getContext(), ThietLapTaiKhoan.class);
                    startActivity(intent);
                }
            }
        });
        tienThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        lichSuDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        lichSuDatGiaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        lichSuCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        suDungEcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });
        lichSuEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StaticData.getObjectInfoUser()==null){
                    Intent intent = new Intent(getContext(), LoginUser.class);
                    startActivityForResult(intent, 3);
                }
            }
        });


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                ObjectInfoUser objectInfoUser= StaticData.getObjectInfoUser();
                textHoTen.setText(objectInfoUser.getHoTen());
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
        if(requestCode==4){
            if (resultCode == Activity.RESULT_OK) {
                textHoTen.setText(StaticData.getObjectInfoUser().getHoTen());
            }
            if(requestCode==Activity.RESULT_CANCELED){

            }
        }
    }
}
