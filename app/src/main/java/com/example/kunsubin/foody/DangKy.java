package com.example.kunsubin.foody;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kunsubin.foody.WebService.AsynCreateUser;

import java.util.concurrent.ExecutionException;

public class DangKy extends AppCompatActivity {
    EditText input_name;
    EditText input_email;
    EditText input_password;
    EditText input_again_password;
    EditText input_HoTen;
    EditText input_DiaChi;
    EditText input_SDT;
    AppCompatButton btn_signup;
    TextView link_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        init();
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_name.getText().toString().trim().equals("")||input_email.getText().toString().trim().equals("")||input_password.getText().toString().trim().equals("")||
                        input_again_password.getText().toString().trim().equals("")||input_HoTen.getText().toString().trim().equals("")||input_DiaChi.getText().toString().trim().equals("")
                        ||input_SDT.getText().toString().trim().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                    builder.setMessage("Hãy điền đầy đủ thông tin vào!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    if(input_password.getText().toString().trim().equals(input_again_password.getText().toString().trim())){
                        if(input_SDT.getText().toString().trim().length()<=11){

                            boolean f=false;
                            AsynCreateUser asynCreateUser=new AsynCreateUser();
                            try {
                                f=asynCreateUser.execute(input_name.getText().toString().trim(),input_password.getText().toString().trim(),
                                        input_HoTen.getText().toString().trim(),input_DiaChi.getText().toString().trim(),
                                        input_SDT.getText().toString().trim(),input_email.getText().toString().trim()).get();
                                if(f){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                                    builder.setMessage("Tạo tài khoản thành công!");
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                            finish();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                                    builder.setMessage("Không tạo được tài khoản!");
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

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                            builder.setMessage("Số điện thoại vượt quá 11 số!");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things

                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(DangKy.this);
                        builder.setMessage("Gõ lại mật khẩu không khớp với mật khẩu!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        });
    }
    public void init(){
        input_name=(EditText)findViewById(R.id.input_name);
        input_email=(EditText)findViewById(R.id.input_email);
        input_password=(EditText)findViewById(R.id.input_password);
        input_again_password=(EditText)findViewById(R.id.input_again_password);
        input_HoTen=(EditText)findViewById(R.id.input_HoTen);
        input_DiaChi=(EditText)findViewById(R.id.input_DiaChi);
        input_SDT=(EditText)findViewById(R.id.input_SDT);
        btn_signup=(AppCompatButton)findViewById(R.id.btn_signup);
        link_login=(TextView)findViewById(R.id.link_login);
    }
}
