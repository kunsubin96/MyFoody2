package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.WebService.AsynCheckLogin;
import com.example.kunsubin.foody.WebService.AsynGetImage;
import com.example.kunsubin.foody.WebService.AsynGetInfoUser;

import org.kobjects.base64.Base64;

import java.util.concurrent.ExecutionException;

public class LoginUser extends AppCompatActivity {
    EditText username;
    EditText password;
    Button btn_login;
    TextView link_DangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        //ánh xạ các view
        username=(EditText)findViewById(R.id.input_username);
        btn_login=(Button)findViewById(R.id.btn_login);
        password=(EditText)findViewById(R.id.input_password);
        link_DangKy=(TextView) findViewById(R.id.link_DangKy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDangNhap);
        //bắt sự kiện nhấn nút back <-- cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //mở activity đăng ký
        link_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKy.class);
                startActivity(intent);
            }
        });
        //check login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiểm tra đầu vào
                if(username.getText().toString().trim().equals("")||password.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"Username, Password không được để trống!",Toast.LENGTH_SHORT).show();
                }else{
                    int ketQua=-1;
                    AsynCheckLogin asynCheckLogin=new AsynCheckLogin();
                    try {
                        //thực thi và lấy kết quả trả về
                        ketQua= asynCheckLogin.execute(username.getText().toString().trim(),password.getText().toString().trim()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(ketQua==1){
                        ObjectInfoUser user=new ObjectInfoUser();
                        AsynGetInfoUser asynGetInfoUser=new AsynGetInfoUser();
                        try {
                            user=asynGetInfoUser.execute(username.getText().toString().trim()).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        if(user.getAvatar().trim().equals("None")){

                        }else{
                            String image=null;
                            AsynGetImage asynGetImage=new AsynGetImage();
                            try {
                                image=asynGetImage.execute(user.getAvatar()).get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            if(image!=null){
                                byte[] valueDecoded= Base64.decode(image);
                                user.setHinh(valueDecoded);
                            }
                        }

                        StaticData.setObjectInfoUser(user);

                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                      // Toast.makeText(getApplicationContext(),StaticData.getObjectInfoUser().getHinh().toString(),Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"Đăng nhập không thành công!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
