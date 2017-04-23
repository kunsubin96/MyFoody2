package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.WebService.AsynChangeProfile;

import java.util.concurrent.ExecutionException;

public class ThongTinLienHe extends AppCompatActivity {
    LinearLayout icon_back;
    TextView textViewHoTen;
    LinearLayout save;
    EditText tenDangNhap;
    EditText hoTen;
    EditText diaChi;
    EditText SDT;
    EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_lien_he);
        init();

        textViewHoTen.setText(StaticData.getObjectInfoUser().getHoTen().toString());
        tenDangNhap.setText(StaticData.getObjectInfoUser().getUsername().toString());
        hoTen.setText(StaticData.getObjectInfoUser().getHoTen().toString());
        diaChi.setText(StaticData.getObjectInfoUser().getDiaChi().toString());
        SDT.setText(StaticData.getObjectInfoUser().getSDT().toString());
        Email.setText(StaticData.getObjectInfoUser().getEmail().toString());

    }
    public  void init(){
        icon_back=(LinearLayout)findViewById(R.id.icon_back);
        textViewHoTen=(TextView)findViewById(R.id.textViewHoTen);
        save=(LinearLayout)findViewById(R.id.save);
        tenDangNhap=(EditText)findViewById(R.id.tenDangNhap);
        hoTen=(EditText)findViewById(R.id.hoTen);
        diaChi=(EditText)findViewById(R.id.diaChi);
        SDT=(EditText)findViewById(R.id.SDT);
        Email=(EditText)findViewById(R.id.Email);
    }
    public void onClickBackThongTinLienHe(View view){
        this.finish();
    }
    public void onClickSavaThongTinLienHe(View view){
        if(hoTen.getText().toString().trim().equals("")||diaChi.getText().toString().trim().equals("")||SDT.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Hãy nhập đầy đủ thông tin vào!",Toast.LENGTH_SHORT).show();
        }else{
            if(SDT.getText().toString().length()<=11){
                boolean f=false;
                AsynChangeProfile asynChangeProfile=new AsynChangeProfile();
                try {
                    f=asynChangeProfile.execute(tenDangNhap.getText().toString().trim(),hoTen.getText().toString().trim(),diaChi.getText().toString().trim(),
                            SDT.getText().toString().trim()).get();
                    if(f){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinLienHe.this);
                        builder.setMessage("Thay đổi thông tin thành công!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                StaticData.getObjectInfoUser().setHoTen(hoTen.getText().toString().trim());
                                StaticData.getObjectInfoUser().setDiaChi(diaChi.getText().toString().trim());
                                StaticData.getObjectInfoUser().setSDT(SDT.getText().toString().trim());
                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else{

                        AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinLienHe.this);
                        builder.setMessage("Thay đổi không thành công!");
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
                Toast.makeText(getApplicationContext(),"Độ dài số điện thoại không thỏa!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
