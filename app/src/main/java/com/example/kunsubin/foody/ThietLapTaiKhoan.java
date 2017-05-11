package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.WebService.AsynChangePassword;

import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThietLapTaiKhoan extends AppCompatActivity {
    LinearLayout layOutThietLap;
    LinearLayout layOutMatKhau;
    RelativeLayout doiMatKhau;
    EditText matKhauHienTai;
    EditText matKhauMoi;
    EditText nhapLaiMatKhau;
    Toolbar toolbarTaiKhoan;
    Toolbar toolbarMatKhau;
    Button btnLuuThayDoi;
    TextView textViewEmailDangKy;
    CircleImageView avataredit;
    RelativeLayout changeAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_tai_khoan);
        init();
        textViewEmailDangKy.setText("Email đăng ký: "+StaticData.getObjectInfoUser().getEmail());
        if(StaticData.getObjectInfoUser().getHinh()!=null){
            Glide.with(this).load(StaticData.getObjectInfoUser().getHinh()).into(avataredit);
        }
        toolbarTaiKhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        toolbarMatKhau.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layOutThietLap.setVisibility(View.VISIBLE);
                layOutMatKhau.setVisibility(View.GONE);
            }
        });
        doiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layOutThietLap.setVisibility(View.GONE);
                layOutMatKhau.setVisibility(View.VISIBLE);
                matKhauHienTai.setText("");
                matKhauMoi.setText("");
                nhapLaiMatKhau.setText("");
            }
        });
        changeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangeAvatar.class);
                startActivityForResult(intent,18);
            }
        });
        btnLuuThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (matKhauHienTai.getText().toString().trim().equals("") || matKhauMoi.getText().toString().trim().equals("") || nhapLaiMatKhau.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin vào!", Toast.LENGTH_SHORT).show();
                } else {
                    if (matKhauHienTai.getText().toString().trim().equals(StaticData.getObjectInfoUser().getPassword().trim())) {
                        if (matKhauMoi.getText().toString().trim().equals(nhapLaiMatKhau.getText().toString().trim())) {
                            boolean f = false;
                            AsynChangePassword asynChangePassword = new AsynChangePassword();
                            try {
                                f = asynChangePassword.execute(StaticData.getObjectInfoUser().getUsername(), matKhauMoi.getText().toString().trim()).get();
                                if (f) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ThietLapTaiKhoan.this);
                                    builder.setMessage("Đổi mật khẩu thành công");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                            layOutThietLap.setVisibility(View.VISIBLE);
                                            layOutMatKhau.setVisibility(View.GONE);
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Thay đổi không thành công!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu mới và nhập lại mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu hiện tại chưa chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void init() {
        layOutThietLap = (LinearLayout) findViewById(R.id.layOutThietLap);
        layOutMatKhau = (LinearLayout) findViewById(R.id.layOutMatKhau);
        doiMatKhau = (RelativeLayout) findViewById(R.id.doiMatKhau);
        matKhauHienTai = (EditText) findViewById(R.id.matKhauHienTai);
        matKhauMoi = (EditText) findViewById(R.id.matKhauMoi);
        nhapLaiMatKhau = (EditText) findViewById(R.id.nhapLaiMatKhau);
        toolbarTaiKhoan = (Toolbar) findViewById(R.id.toolbarTaiKhoan);
        toolbarMatKhau = (Toolbar) findViewById(R.id.toolbarMatKhau);
        btnLuuThayDoi = (Button) findViewById(R.id.btnLuuThayDoi);
        textViewEmailDangKy=(TextView)findViewById(R.id.textViewEmailDangKy);
        avataredit=(CircleImageView)findViewById(R.id.avataredit);
        changeAvatar=(RelativeLayout)findViewById(R.id.changeAvatar);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 18) {
            if (resultCode == Activity.RESULT_OK) {
                ObjectInfoUser objectInfoUser= StaticData.getObjectInfoUser();
                if(objectInfoUser.getHinh()!=null){
                    Glide.with(getApplicationContext()).load(objectInfoUser.getHinh()).into(avataredit);
                }

            }
            if(requestCode==Activity.RESULT_CANCELED){

            }
        }
    }
}
