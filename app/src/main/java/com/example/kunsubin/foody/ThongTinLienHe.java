package com.example.kunsubin.foody;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.WebService.AsynChangeProfile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThongTinLienHe extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    LinearLayout icon_back;
    TextView textViewHoTen;
    LinearLayout save;
    EditText tenDangNhap;
    EditText hoTen;
    EditText diaChi;
    EditText SDT;
    EditText Email;
    TextView text_view_date_of_birth;
    int year;
    int month;
    int dayofmonth;
    DatePickerDialog datePicker;
    EditText edit_text_sex;
    GenderAdapter genderAdapter;
    AlertDialog.Builder genderBuilder;
    EditText edit_text_marry_status;
    MarryAdapter marryAdapter;
    AlertDialog.Builder marryBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_lien_he);
        init();

        StaticData.setPossionGioiTinh(1);
        StaticData.setPossionGioiTinh(-1);
        GenderData();
        MarryStatusData();
        textViewHoTen.setText(StaticData.getObjectInfoUser().getHoTen().toString());
        tenDangNhap.setText(StaticData.getObjectInfoUser().getUsername().toString());
        hoTen.setText(StaticData.getObjectInfoUser().getHoTen().toString());
        diaChi.setText(StaticData.getObjectInfoUser().getDiaChi().toString());
        SDT.setText(StaticData.getObjectInfoUser().getSDT().toString());
        Email.setText(StaticData.getObjectInfoUser().getEmail().toString());

        text_view_date_of_birth.setOnClickListener(this);
        edit_text_sex.setOnClickListener(this);
        edit_text_marry_status.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();

        String[] birth = text_view_date_of_birth.getText().toString().trim().split("/");
        this.year = Integer.parseInt(birth[0]);
        this.month = Integer.parseInt(birth[1]);
        this.dayofmonth = Integer.parseInt(birth[2]);
        datePicker = new DatePickerDialog(this, this, this.year, this.month, this.dayofmonth);

    }

    public void init() {
        icon_back = (LinearLayout) findViewById(R.id.icon_back);
        textViewHoTen = (TextView) findViewById(R.id.textViewHoTen);
        save = (LinearLayout) findViewById(R.id.save);
        tenDangNhap = (EditText) findViewById(R.id.tenDangNhap);
        hoTen = (EditText) findViewById(R.id.hoTen);
        diaChi = (EditText) findViewById(R.id.diaChi);
        SDT = (EditText) findViewById(R.id.SDT);
        Email = (EditText) findViewById(R.id.Email);
        text_view_date_of_birth = (TextView) findViewById(R.id.text_view_date_of_birth);
        edit_text_sex=(EditText)findViewById(R.id.edit_text_sex);
        edit_text_marry_status=(EditText)findViewById(R.id.edit_text_marry_status);
    }

    public void onClickBackThongTinLienHe(View view) {
        this.finish();
    }

    public void onClickSavaThongTinLienHe(View view) {
        if (hoTen.getText().toString().trim().equals("") || diaChi.getText().toString().trim().equals("") || SDT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin vào!", Toast.LENGTH_SHORT).show();
        } else {
            if (SDT.getText().toString().length() <= 11) {
                boolean f = false;
                AsynChangeProfile asynChangeProfile = new AsynChangeProfile();
                try {
                    f = asynChangeProfile.execute(tenDangNhap.getText().toString().trim(), hoTen.getText().toString().trim(), diaChi.getText().toString().trim(),
                            SDT.getText().toString().trim()).get();
                    if (f) {
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
                    } else {

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
            } else {
                Toast.makeText(getApplicationContext(), "Độ dài số điện thoại không thỏa!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        if (checkDateValid(year, Calendar.YEAR) == 1) {
            this.year = i;
            this.month = i1 + 1;
            this.dayofmonth = i2;
            this.text_view_date_of_birth.setText(String.format("%d/%d/%d",
                    new Object[]{Integer.valueOf(this.dayofmonth), Integer.valueOf(this.month), Integer.valueOf(this.year)}));
        } else {
            Toast.makeText(this, "Ngày sinh không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    private int checkDateValid(int value, int type) {
        if (value > Calendar.getInstance().get(type)) {
            return -1;
        }
        return 1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_date_of_birth:
                this.datePicker.updateDate(this.year, this.month - 1, this.dayofmonth);
                this.datePicker.show();
                break;
            case R.id.edit_text_sex:
                genderAdapter.notifyDataSetChanged();
                genderBuilder.show();
                break;
            case R.id.edit_text_marry_status:
                marryAdapter.notifyDataSetChanged();
                marryBuilder.show();
                break;
            default:
                break;
        }
    }
    private void MarryStatusData() {
        final List<String> marry=new ArrayList<>();
        marry.add("Độc thân");
        marry.add("Đã cưới");
        marry.add("Phức tạp");
        marry.add("Đang hẹn hò");
        marry.add("Đã đính hôn");
        marry.add("Quan hệ mở");
        marry.add("Góa");
        marry.add("Ly dị");
        marry.add("Li thân");


        marryAdapter = new MarryAdapter(getApplicationContext(), R.layout.list_item_marry_status);
        for (int i = 0; i < marry.size(); i++) {
            marryAdapter.add(marry.get(i).toString());
        }

        marryBuilder = new AlertDialog.Builder(this);
        marryBuilder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        marryBuilder.setAdapter(marryAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StaticData.setPossionMarry(which);
                edit_text_marry_status.setText(marry.get(which));
                dialog.dismiss();
            }
        });
    }
    public void GenderData() {
        final List<String> gioiTinh=new ArrayList<>();
        gioiTinh.add("Nam");
        gioiTinh.add("Nữ");
        genderAdapter = new GenderAdapter(getApplicationContext(), R.layout.list_item_gender);
        genderAdapter.add("Nam");
        genderAdapter.add("Nữ");

        genderBuilder = new AlertDialog.Builder(this);
        genderBuilder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        genderBuilder.setAdapter(genderAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StaticData.setPossionGioiTinh(which);
                edit_text_sex.setText(gioiTinh.get(which).toString());
                dialog.dismiss();
            }
        });
    }
    public class GenderAdapter extends ArrayAdapter<String> {
        public GenderAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            if (StaticData.getPossionGioiTinh() == position) {
                v.setBackgroundColor(Color.parseColor("#D0D0D0"));
            } else {
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return v;
        }
    }
    class MarryAdapter extends ArrayAdapter<String> {
        public MarryAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            if (StaticData.getPossionMarry() == position) {
                v.setBackgroundColor(Color.parseColor("#D0D0D0"));
            } else {
                v.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return v;
        }
    }

}
