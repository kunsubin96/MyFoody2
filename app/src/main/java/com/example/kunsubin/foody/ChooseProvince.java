package com.example.kunsubin.foody;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Object.TinhThanh;
import com.example.kunsubin.foody.WebService.AsynTinhThanh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChooseProvince extends AppCompatActivity {

    List<TinhThanh> listTinhThanh;
    ListView listViewThanhPho;
    String temp;
    CustomAdapterTinhThanh adapterTinhThanh;
    EditText search_choose_province;
    ImageView delete_choose_province;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_province_activity_layout);

        AsynTinhThanh asynTinhThanh=new AsynTinhThanh();
        try {
            listTinhThanh=asynTinhThanh.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Lấy giá trị được gửi tới bên class ODau hoặc AnGi
        Intent intent2 = getIntent();
        String tinhThanh = intent2.getStringExtra("BussinessTinhThanh");
        temp=tinhThanh;
        //lấy vị trí đang được chọn mặc định ban đầu của tỉnh thành
        int index=0;
        for (int i=0;i<listTinhThanh.size();i++)
        {
            if(listTinhThanh.get(i).getTenTinhThanh().equals(tinhThanh)){
                index=i;
                break;
            }
        }
        //trạng thái vị trí chọn
        StaticData.setSelected(index);

        listViewThanhPho = (ListView) findViewById(R.id.list_view_choose_province);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.choose_province_header_activity_layout, listViewThanhPho, false);
        //add header cho listViewThanhPho
        listViewThanhPho.addHeaderView(header, null, false);
        search_choose_province=(EditText)header.findViewById(R.id.search_choose_province);
        delete_choose_province=(ImageView)header.findViewById(R.id.delete_choose_province);
        //listViewThanhPho.setAdapter(null);
        adapterTinhThanh = new CustomAdapterTinhThanh(this, listTinhThanh, index);
        listViewThanhPho.setAdapter(adapterTinhThanh);
        //sự kiện khi click chọn các item trong tỉnh thành
        listViewThanhPho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                ImageView iconCheck;
                TextView macDinh;
                TextView textItem;
                for (int i=1;i<adapterView.getChildCount();i++){
                    iconCheck = (ImageView) adapterView.getChildAt(i).findViewById(R.id.image_view_check_status);
                    macDinh = (TextView) adapterView.getChildAt(i).findViewById(R.id.text_view_set_default);
                    iconCheck.setImageResource(0);
                    macDinh.setVisibility(View.GONE);
                }
                StaticData.setSelected(postion-1);
                iconCheck = (ImageView) view.findViewById(R.id.image_view_check_status);
                macDinh = (TextView) view.findViewById(R.id.text_view_set_default);
                textItem = (TextView) view.findViewById(R.id.text_view_province_name);
                final String result = textItem.getText().toString();
                temp=textItem.getText().toString();
                iconCheck.setImageResource(R.drawable.icon_stick);
                macDinh.setVisibility(View.VISIBLE);
                //trả về tỉnh thành khi nhấn nút mặc định
                macDinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", result);
                        returnIntent.putExtra("idTinh", getId(result));
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
                //cuộn lên khi chọn một tỉnh
                listViewThanhPho.setSelection(postion-1);
                listViewThanhPho.smoothScrollToPositionFromTop(postion-1,0);
                adapterTinhThanh.notifyDataSetChanged();
            }
        });

    }
    //kết thúc activity khi nhấn back
    public void onClickBack(View view) {
        this.finish();
    }
    //kết thúc activity khi nhấn xong và gửi kết quả về
    public void onClickXong(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", temp);
        returnIntent.putExtra("idTinh",getId(temp));
        setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }
    //lấy mã tỉnh theo tên tỉnh
    public String getId(String tenTinh){
        String id="";
        for (int i=0;i<listTinhThanh.size();i++){
            if(listTinhThanh.get(i).getTenTinhThanh().equals(tenTinh)){
                id=listTinhThanh.get(i).getMaTinhThanh();
                break;
            }
        }
        return id;
    }

}
