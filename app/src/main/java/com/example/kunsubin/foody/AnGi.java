package com.example.kunsubin.foody;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunsubin.foody.AutoScrollViewPager.AutoScrollViewPager;
import com.example.kunsubin.foody.BussinessAccess.BussinessNhaHang;
import com.example.kunsubin.foody.BussinessAccess.BussinessQuanHuyen;
import com.example.kunsubin.foody.BussinessAccess.BussinessTinhThanh;
import com.example.kunsubin.foody.GridViewAnGi.AdapterGridViewAnGi;
import com.example.kunsubin.foody.GridViewAnGi.HeaderGridView;
import com.example.kunsubin.foody.Object.Duong;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Object.TinhThanh;
import com.example.kunsubin.foody.RecyclerView.MoreItemView;
import com.example.kunsubin.foody.WebService.AsynDuong;
import com.example.kunsubin.foody.WebService.AsynQuanHuyen;
import com.example.kunsubin.foody.WebService.AsynTinhThanh;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by kunsubin on 3/29/2017.
 */

public class AnGi extends android.support.v4.app.Fragment {
    ListView listViewMoiNhatAnGi;
    HeaderGridView khungChinhAnGi;
    LinearLayout layOutMoiNhatAnGi;
    TextView textMoiNhatAnGi;

    ListView listViewDanhMucAnGi;
    LinearLayout layOutDanhMucAnGi;
    TextView textDanhMucAnGi;

    LinearLayout listViewDiaDiemAnGi;
    LinearLayout layOutDiaDiemAnGi;
    LinearLayout linear_layout_change_districtAnGi;
    TextView text_view_parent_districtAnGi;
    TextView textViewDiaDiemAnGi;
    View slideShowBanner;
    CircleIndicator indicator;
    List<Integer> mResources;

    AutoScrollViewPager view_pager_imageAnGi;
    BottomNavigationViewEx bottomNavigationViewEx;
    TextView btnHuy;

    MoreItemView moreItemView;
    Context context;
    MainActivity mainActivity;
    private static LayoutInflater mainA = null;
    boolean trangThaiMoiNhatAnGi = true;
    boolean trangThaiDanhMucAnGi = true;
    boolean trangThaiDiaDiemAnGi=true;

    String maTinh="";
    List<QuanHuyen> listQuanHuyen;
    ExpandableListView list_view_cityAnGi;
    List<TinhThanh> listTinhThanh;

    List<NhaHang> listNhaHangAnGi;
    BussinessNhaHang bussinessNhaHang;
    String TinhThanh="";
    String QuanHuyen="";
    String TabDanhMuc="";
    String TabMoiNhat="";
    List<QuanHuyen> listQuanHuyenTheoTinh;
    HashMap<QuanHuyen,List<Duong>> listDataChildAnGi;
    ExpandableListAdapterAnGi listAdapterDiaDiemAnGi;
    List<String> countDuong;
    public AnGi(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;

        mainA = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.angi, container, false);
        init(view);

        //load dữ liệu lên listQuanHuyen
        AsynQuanHuyen asynQuanHuyen=new AsynQuanHuyen();
        try {
            listQuanHuyen=asynQuanHuyen.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //load dữ liệu lên listTỉnh thành
        AsynTinhThanh asynTinhThanh=new AsynTinhThanh();
        try {
            listTinhThanh=asynTinhThanh.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        StaticData.setSelectedDanhMucAnGi(0);
        StaticData.setSelectedDiaDiemAnGi(-1);
        bussinessNhaHang =new BussinessNhaHang(context);
        //sự kiện khi nhấn tab thứ 1 bên chọn mới nhất
        layOutMoiNhatAnGi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trangThaiMoiNhatAnGi) {
                    layOutMoiNhatAnGi.setBackgroundResource(R.color.nenKhungChinh);
                    layOutDanhMucAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    if (listViewMoiNhatAnGi.getCount() > 0) {

                    } else {
                        loadListViewMoiNhatAnGi(view);
                    }

                    listViewMoiNhatAnGi.setVisibility(View.VISIBLE);
                    listViewDanhMucAnGi.setVisibility(View.GONE);
                    listViewDiaDiemAnGi.setVisibility(View.GONE);
                    khungChinhAnGi.setVisibility(View.GONE);

                    btnHuy.setVisibility(View.VISIBLE);
                    bottomNavigationViewEx.setVisibility(View.GONE);

                    trangThaiMoiNhatAnGi = false;
                    trangThaiDanhMucAnGi = true;
                    trangThaiDiaDiemAnGi=true;
                } else {
                    layOutMoiNhatAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    listViewMoiNhatAnGi.setVisibility(View.GONE);
                    listViewDanhMucAnGi.setVisibility(View.GONE);
                    listViewDiaDiemAnGi.setVisibility(View.GONE);
                    khungChinhAnGi.setVisibility(View.VISIBLE);

                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationViewEx.setVisibility(View.VISIBLE);

                    trangThaiMoiNhatAnGi = true;
                    trangThaiDanhMucAnGi = true;
                    trangThaiDiaDiemAnGi=true;
                }

            }});
            //sự kiện khi nhấn tab thứ 2 bên chọn danh mục
            layOutDanhMucAnGi.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View view){
                if (trangThaiDanhMucAnGi) {
                    layOutDanhMucAnGi.setBackgroundResource(R.color.nenKhungChinh);
                    layOutMoiNhatAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    if (listViewDanhMucAnGi.getCount() > 0) {

                    } else {
                        loadListViewDanhMucAnGi(view);
                    }

                    listViewDanhMucAnGi.setVisibility(View.VISIBLE);
                    listViewMoiNhatAnGi.setVisibility(View.GONE);
                    listViewDiaDiemAnGi.setVisibility(View.GONE);
                    khungChinhAnGi.setVisibility(View.GONE);

                    btnHuy.setVisibility(View.VISIBLE);
                    bottomNavigationViewEx.setVisibility(View.GONE);

                    trangThaiDanhMucAnGi = false;
                    trangThaiMoiNhatAnGi = true;
                    trangThaiDiaDiemAnGi=true;
                } else {
                    layOutDanhMucAnGi.setBackgroundResource(R.drawable.my_button_bg);
                    listViewMoiNhatAnGi.setVisibility(View.GONE);
                    listViewDanhMucAnGi.setVisibility(View.GONE);
                    listViewDiaDiemAnGi.setVisibility(View.GONE);
                    khungChinhAnGi.setVisibility(View.VISIBLE);

                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationViewEx.setVisibility(View.VISIBLE);

                    trangThaiMoiNhatAnGi = true;
                    trangThaiDanhMucAnGi = true;
                    trangThaiDiaDiemAnGi=true;

                }
            }
            });
             //sự kiện khi nhấn tab thứ 3 bên chọn địa điểm
            layOutDiaDiemAnGi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (trangThaiDiaDiemAnGi) {
                        layOutDiaDiemAnGi.setBackgroundResource(R.color.nenKhungChinh);
                        layOutMoiNhatAnGi.setBackgroundResource(R.drawable.my_button_bg);
                        layOutDanhMucAnGi.setBackgroundResource(R.drawable.my_button_bg);

                        if(list_view_cityAnGi.getCount()>0){

                        }else{
                            String temp=textViewDiaDiemAnGi.getText().toString();
                            loadDiaDiemTheoTinh(getMaTinh(temp));
                            text_view_parent_districtAnGi.setTextColor(getResources().getColor(R.color.red));
                        }

                        listViewDiaDiemAnGi.setVisibility(View.VISIBLE);
                        listViewMoiNhatAnGi.setVisibility(View.GONE);
                        listViewDanhMucAnGi.setVisibility(View.GONE);
                        khungChinhAnGi.setVisibility(View.GONE);

                        btnHuy.setVisibility(View.VISIBLE);
                        bottomNavigationViewEx.setVisibility(View.GONE);

                        trangThaiDiaDiemAnGi = false;
                        trangThaiMoiNhatAnGi = true;
                        trangThaiDanhMucAnGi=true;
                    } else {
                        layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                        listViewMoiNhatAnGi.setVisibility(View.GONE);
                        listViewDanhMucAnGi.setVisibility(View.GONE);
                        listViewDiaDiemAnGi.setVisibility(View.GONE);
                        khungChinhAnGi.setVisibility(View.VISIBLE);

                        btnHuy.setVisibility(View.GONE);
                        bottomNavigationViewEx.setVisibility(View.VISIBLE);

                        trangThaiMoiNhatAnGi = true;
                        trangThaiDanhMucAnGi = true;
                        trangThaiDiaDiemAnGi=true;

                    }
                }
            });
            //mở activity khi click chọn tỉnh thành bên tab địa điểm
             linear_layout_change_districtAnGi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, ChooseProvince.class);
                     intent.putExtra("BussinessTinhThanh",text_view_parent_districtAnGi.getText().toString());
                     startActivityForResult(intent , 2);
                 }
             });
            //khi nhấn vào thành phố trong tab thứ 3 thì load lại dữ liệu theo khung chính
             text_view_parent_districtAnGi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     textViewDiaDiemAnGi.setText(text_view_parent_districtAnGi.getText());
                     textViewDiaDiemAnGi.setTextColor(getResources().getColor(R.color.red));
                     text_view_parent_districtAnGi.setTextColor(getResources().getColor(R.color.red));
                     StaticData.setSelectedDiaDiemAnGi(-1);
                     loadDiaDiemTheoTinh(getMaTinh(textViewDiaDiemAnGi.getText().toString().trim()));
                     //load dữ liệu sau khi chọn tỉnh bên activity tỉnh thành
                     TinhThanh=text_view_parent_districtAnGi.getText().toString().trim();
                     QuanHuyen="";
                     TabDanhMuc=textDanhMucAnGi.getText().toString().trim();
                     TabMoiNhat=textMoiNhatAnGi.getText().toString().trim();
                     khungChinhAnGi.setAdapter(null);
                     listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
                     if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
                         khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
                     else{
                         khungChinhAnGi.setAdapter(null);
                     }
                     //đóng listview khi chọn xong item
                     layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                     listViewDiaDiemAnGi.setVisibility(View.GONE);
                     listViewMoiNhatAnGi.setVisibility(View.GONE);
                     listViewDanhMucAnGi.setVisibility(View.GONE);
                     khungChinhAnGi.setVisibility(View.VISIBLE);
                     btnHuy.setVisibility(View.GONE);
                     bottomNavigationViewEx.setVisibility(View.VISIBLE);
                     trangThaiMoiNhatAnGi=true;
                     trangThaiDanhMucAnGi=true;
                     trangThaiDiaDiemAnGi=true;
                 }
             });
            //load dữ liệu khung chính ăn gì
            loadDuLieuKhungChinhAnGi();
            return view;
        }
    //nhận dữ liệu trả về từ activity tỉnh thành
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                //lấy data nếu trả về là RESULT_OK
                String result=data.getStringExtra("result");
                maTinh=data.getStringExtra("idTinh");
                text_view_parent_districtAnGi.setText(result);
                text_view_parent_districtAnGi.setTextColor(getResources().getColor(R.color.red));
                textViewDiaDiemAnGi.setText(result);
                textViewDiaDiemAnGi.setTextColor(getResources().getColor(R.color.red));
                //công việc load lại list địa điểm
                //Toast.makeText(context,maTinh,Toast.LENGTH_SHORT).show();
                StaticData.setSelectedDiaDiemAnGi(-1);
                loadDiaDiemTheoTinh(maTinh);
                //đóng listview khi chọn xong item
                layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                listViewDiaDiemAnGi.setVisibility(View.GONE);
                listViewMoiNhatAnGi.setVisibility(View.GONE);
                listViewDanhMucAnGi.setVisibility(View.GONE);
                khungChinhAnGi.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatAnGi=true;
                trangThaiDanhMucAnGi=true;
                trangThaiDiaDiemAnGi=true;
                //load dữ liệu sau khi chọn tab địa điểm
                TinhThanh=text_view_parent_districtAnGi.getText().toString().trim();
                if(TinhThanh.equals(textViewDiaDiemAnGi.getText().toString().trim())) QuanHuyen="";
                else QuanHuyen=textViewDiaDiemAnGi.getText().toString().trim();

                TabDanhMuc=textDanhMucAnGi.getText().toString().trim();
                TabMoiNhat=textMoiNhatAnGi.getText().toString().trim();
                khungChinhAnGi.setAdapter(null);
                listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
                if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
                    khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
                else{
                    khungChinhAnGi.setAdapter(null);
                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //hủy lấy data nếu trả về là RESULT_OK
            }
        }
    }
    //ánh xạ các view
    public void init(View view) {
        listViewMoiNhatAnGi = (ListView) view.findViewById(R.id.listViewMoiNhatAnGi);
        khungChinhAnGi = (HeaderGridView) view.findViewById(R.id.khungChinhAnGi);
        layOutMoiNhatAnGi = (LinearLayout) view.findViewById(R.id.layOutMoiNhatAnGi);
        textMoiNhatAnGi = (TextView) view.findViewById(R.id.textViewMoiNhatAnGi);

        listViewDanhMucAnGi = (ListView) view.findViewById(R.id.listViewDanhMucAnGi);
        layOutDanhMucAnGi = (LinearLayout) view.findViewById(R.id.layOutDanhMucAnGi);
        textDanhMucAnGi = (TextView) view.findViewById(R.id.textViewDanhMucAnGi);

        listViewDiaDiemAnGi=(LinearLayout)view.findViewById(R.id.listViewDiaDiemAnGi);
        layOutDiaDiemAnGi=(LinearLayout)view.findViewById(R.id.layOutDiaDiemAnGi);
        linear_layout_change_districtAnGi=(LinearLayout)view.findViewById(R.id.linear_layout_change_districtAnGi);
        text_view_parent_districtAnGi=(TextView)view.findViewById(R.id.text_view_parent_districtAnGi);
        textViewDiaDiemAnGi=(TextView)view.findViewById(R.id.textViewDiaDiemAnGi);
        list_view_cityAnGi=(ExpandableListView)view.findViewById(R.id.list_view_cityAnGi);

        bottomNavigationViewEx=(BottomNavigationViewEx)mainActivity.findViewById(R.id.bottomNavigationView);
        btnHuy=(TextView)mainActivity.findViewById(R.id.btnHuy);


    }
    //load dữ liệu khung chính ăn gì
    public void loadDuLieuKhungChinhAnGi(){
        //sự chuyển động của khung hình trong khung chính
        slideShowBanner=mainA.inflate(R.layout.layout_image_viewpager_main,khungChinhAnGi,false);
        view_pager_imageAnGi=(AutoScrollViewPager)slideShowBanner.findViewById(R.id.view_pager_image);
        mResources=this.getDefaultImageSlideShow();
        ImageAdapter adapter=new ImageAdapter(context,mResources);
        indicator = (CircleIndicator) slideShowBanner.findViewById(R.id.indicator);
        view_pager_imageAnGi.setAdapter(adapter);

        view_pager_imageAnGi.setInterval(2000);
        view_pager_imageAnGi.setStopScrollWhenTouch(false);
        view_pager_imageAnGi.setBorderAnimation(false);
        view_pager_imageAnGi.setCycle(true);
        view_pager_imageAnGi.startAutoScroll();

        indicator.setViewPager(view_pager_imageAnGi);
        khungChinhAnGi.addHeaderView(slideShowBanner);
        //add các icon button main


        moreItemView=new MoreItemView(context);
        khungChinhAnGi.addHeaderView(moreItemView);

        TinhThanh="TP.HCM";
        QuanHuyen="";
        TabDanhMuc="Danh mục";
        TabMoiNhat="Mới nhất";
        khungChinhAnGi.setAdapter(null);
        listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
        if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
            khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
        else{
            khungChinhAnGi.setAdapter(null);
        }

        //Toast.makeText(context,listNhaHangAnGi.size()+"",Toast.LENGTH_SHORT).show();

    }
    //đưa dữ liệu lên list view tab 1 bên mục ăn gì
    public void loadListViewMoiNhatAnGi(View view) {
        int[] prgmImages = {R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh1, R.drawable.hinh4};
        String[] prgmNameList = {"Mới nhất", "Gần tôi", "Xem nhiều", "Du khách"};
        listViewMoiNhatAnGi.setAdapter(new CustomAdapterMoiNhat(mainActivity, prgmNameList, prgmImages, false));
        listViewMoiNhatAnGi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //set trạng thái item select
                stateSelectedItemListView(adapterView, view);
                //đóng listview khi chọn xong item
                layOutMoiNhatAnGi.setBackgroundResource(R.drawable.my_button_bg);
                listViewMoiNhatAnGi.setVisibility(View.GONE);
                khungChinhAnGi.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatAnGi = true;
                trangThaiDanhMucAnGi=true;
                trangThaiDiaDiemAnGi=true;
                //
                TinhThanh=text_view_parent_districtAnGi.getText().toString().trim();
                if(TinhThanh.equals(textViewDiaDiemAnGi.getText().toString().trim())) QuanHuyen="";
                else QuanHuyen=textViewDiaDiemAnGi.getText().toString().trim();

                TabDanhMuc= textDanhMucAnGi.getText().toString().trim();
                TabMoiNhat=textMoiNhatAnGi.getText().toString().trim();
                khungChinhAnGi.setAdapter(null);
                listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
                if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
                    khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
                else{
                    khungChinhAnGi.setAdapter(null);
                }
            }
        });
    }

    //trạng thái khi chọn item trong listview tô màu chữ hình, dấu check
    public void stateSelectedItemListView(AdapterView<?> adapterView, View view) {
        TextView tv;
        ImageView tvAvatar;
        ImageView check;
        for (int j = 0; j < adapterView.getChildCount(); j++) {
            tv = (TextView) adapterView.getChildAt(j).findViewById(R.id.tvNoiDung);
            tvAvatar = (ImageView) adapterView.getChildAt(j).findViewById(R.id.imgAvatatr);
            check = (ImageView) adapterView.getChildAt(j).findViewById(R.id.check);

            tv.setTextColor(getResources().getColor(R.color.black));
            tvAvatar.setColorFilter(null);
            check.setImageResource(0);
        }
        tv = (TextView) view.findViewById(R.id.tvNoiDung);
        tv.setTextColor(getResources().getColor(R.color.red));
        tvAvatar = (ImageView) view.findViewById(R.id.imgAvatatr);
        tvAvatar.setColorFilter(getContext().getResources().getColor(R.color.blue));
        check = (ImageView) view.findViewById(R.id.check);
        check.setImageResource(R.drawable.icon_stick);
        //setText khi click text view
        textMoiNhatAnGi.setText(tv.getText());
        textMoiNhatAnGi.setTextColor(getResources().getColor(R.color.red));

    }

    //load dữ liệu lên list view danhmuc
    public void loadListViewDanhMucAnGi(View view) {
        int[] prgmImages = {0,R.drawable.danhmuc16,
                R.drawable.danhmuc17, R.drawable.danhmuc18, R.drawable.danhmuc19, R.drawable.danhmuc20,
                R.drawable.danhmuc21, R.drawable.danhmuc22, R.drawable.danhmuc23, R.drawable.danhmuc24,
                R.drawable.danhmuc25, R.drawable.danhmuc26, R.drawable.danhmuc27, R.drawable.danhmuc28,
                R.drawable.danhmuc29, R.drawable.danhmuc30,R.drawable.danhmuc1, R.drawable.danhmuc2, R.drawable.danhmuc3,
                R.drawable.danhmuc4, R.drawable.danhmuc5, R.drawable.danhmuc6, R.drawable.danhmuc7,
                R.drawable.danhmuc8, R.drawable.danhmuc9, R.drawable.danhmuc10, R.drawable.danhmuc11, R.drawable.danhmuc12,
                R.drawable.danhmuc13, R.drawable.danhmuc14, R.drawable.danhmuc15};
        String[] prgmNameList = {"Danh mục", "Việt Nam", "Châu Mỹ", "Mỹ", "Tây Âu", "Ý", "Pháp", "Đức", "Tây Ban Nha", "Trung Hoa", "Ấn Độ",
                "Thái Lan", "Nhật Bản", "Hàn Quốc", "Malaysia", "Quốc tế", "Sang trọng", "Buffet", "Nhà hàng",
                "Ăn vặt/vỉa hè", "Ăn chay", "Cafe/Dessert", "Quán ăn", "Bar/Pub", "Quán nhậu",
                "Beer club", "Tiệm bánh", "Tiệc tận nơi", "Shop online", "Giao cơm văn phòng",
                "Khu ẩm thực"};

        listViewDanhMucAnGi.setAdapter(new CustomAdapterDanhMucAnGi(mainActivity, prgmNameList, prgmImages));
        listViewDanhMucAnGi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                StaticData.setSelectedDanhMucAnGi(position);
                //set trạng thái item select
                stateSelectedItemListViewDanhMuc(adapterView, view);
                //đóng listview khi chọn xong item
                layOutDanhMucAnGi.setBackgroundResource(R.drawable.my_button_bg);
                listViewMoiNhatAnGi.setVisibility(View.GONE);
                listViewDanhMucAnGi.setVisibility(View.GONE);
                khungChinhAnGi.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatAnGi = true;
                trangThaiDanhMucAnGi=true;
                trangThaiDiaDiemAnGi=true;
                //load dữ liệu sau khi chọn tab danh mục
                TinhThanh=text_view_parent_districtAnGi.getText().toString().trim();
                if(TinhThanh.equals(textViewDiaDiemAnGi.getText().toString().trim())) QuanHuyen="";
                else QuanHuyen=textViewDiaDiemAnGi.getText().toString().trim();

                TabDanhMuc=textDanhMucAnGi.getText().toString().trim();
                TabMoiNhat=textMoiNhatAnGi.getText().toString().trim();
                khungChinhAnGi.setAdapter(null);
                listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
                if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
                    khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
                else{
                    khungChinhAnGi.setAdapter(null);
                }
            }
        });

    }

    //trạng thái khi chọn item trong listview mới nhất AnGi tô màu chữ hình, dấu check
    public void stateSelectedItemListViewDanhMuc(AdapterView<?> adapterView, View view) {
        TextView tv;
        ImageView check;
        for (int j = 0; j < adapterView.getChildCount(); j++) {
            tv = (TextView) adapterView.getChildAt(j).findViewById(R.id.tvDanhMuc);
            check = (ImageView) adapterView.getChildAt(j).findViewById(R.id.checkDanhMuc);

            tv.setTextColor(getResources().getColor(R.color.black));
            check.setImageResource(0);
        }
        tv = (TextView) view.findViewById(R.id.tvDanhMuc);
        tv.setTextColor(getResources().getColor(R.color.red));
        check = (ImageView) view.findViewById(R.id.checkDanhMuc);
        check.setImageResource(R.drawable.icon_stick);
        //setText khi click text view
        textDanhMucAnGi.setText(tv.getText());
        if(tv.getText()=="Danh mục"){
            textDanhMucAnGi.setTextColor(getResources().getColor(R.color.textColorBar));
        }else{
            textDanhMucAnGi.setTextColor(getResources().getColor(R.color.red));
        }

    }
    //load quận huyện theo tỉnh rùi đưa vào listview list_view_cityAnGi
    public void loadDiaDiemTheoTinh(String maTinh){
        listQuanHuyenTheoTinh=new ArrayList<>();
        maTinh=maTinh.trim();
        //lấy các huyện trong list quan huyen theo tinh chon
        for(int i=0;i<listQuanHuyen.size();i++){
            if(listQuanHuyen.get(i).getMaTinhThanh().trim().equals(maTinh))
                listQuanHuyenTheoTinh.add(listQuanHuyen.get(i));
        }
        listDataChildAnGi=new HashMap<QuanHuyen, List<Duong>>();
        countDuong=new ArrayList<>();
        for (int i=0;i<listQuanHuyenTheoTinh.size();i++){
            AsynDuong asynDuong=new AsynDuong();
            try {
                List<Duong> duongList=asynDuong.execute(listQuanHuyenTheoTinh.get(i).getMaQuanHuyen().toString().trim()).get();
                countDuong.add(String.valueOf(duongList.size()));
                //
                listDataChildAnGi.put(listQuanHuyenTheoTinh.get(i),duongList);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        listAdapterDiaDiemAnGi=new ExpandableListAdapterAnGi(mainActivity,listQuanHuyenTheoTinh,listDataChildAnGi,countDuong);
        list_view_cityAnGi.setAdapter(listAdapterDiaDiemAnGi);

        list_view_cityAnGi.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                return false;
            }
        });
        list_view_cityAnGi.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                return false;
            }
        });
        /*list_view_cityAnGi.setAdapter(null);
        list_view_cityAnGi.setAdapter(new CustomAdapterDiaDiemAnGi(mainActivity,listQuanHuyenTheoTinh));
        list_view_cityAnGi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv;
                text_view_parent_districtAnGi.setTextColor(getResources().getColor(R.color.black));
                for(int j=0;j<adapterView.getChildCount();j++){
                    tv=(TextView)adapterView.getChildAt(j).findViewById(R.id.text_view_district_name);
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
                tv=(TextView)view.findViewById(R.id.text_view_district_name);
                tv.setTextColor(getResources().getColor(R.color.red));
                StaticData.setSelectedDiaDiemAnGi(i);
                textViewDiaDiemAnGi.setText(tv.getText());
                textViewDiaDiemAnGi.setTextColor(getResources().getColor(R.color.red));
                text_view_parent_districtAnGi.setTextColor(getResources().getColor(R.color.black));
                //đóng listview khi chọn xong item
                layOutDiaDiemAnGi.setBackgroundResource(R.drawable.my_button_bg);
                listViewDiaDiemAnGi.setVisibility(View.GONE);
                listViewMoiNhatAnGi.setVisibility(View.GONE);
                listViewDanhMucAnGi.setVisibility(View.GONE);
                khungChinhAnGi.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatAnGi=true;
                trangThaiDanhMucAnGi=true;
                trangThaiDiaDiemAnGi=true;
                //load dữ liệu sau khi chọn tab địa điểm
                TinhThanh=text_view_parent_districtAnGi.getText().toString().trim();
                if(TinhThanh.equals(textViewDiaDiemAnGi.getText().toString().trim())) QuanHuyen="";
                else QuanHuyen=textViewDiaDiemAnGi.getText().toString().trim();

                TabDanhMuc=textDanhMucAnGi.getText().toString().trim();
                TabMoiNhat=textMoiNhatAnGi.getText().toString().trim();
                khungChinhAnGi.setAdapter(null);
                listNhaHangAnGi=bussinessNhaHang.getListNhaHangAnGi(TinhThanh,QuanHuyen,TabDanhMuc,TabMoiNhat);
                if(listNhaHangAnGi!=null&&listNhaHangAnGi.size()>0)
                    khungChinhAnGi.setAdapter(new AdapterGridViewAnGi(mainActivity,listNhaHangAnGi));
                else{
                    khungChinhAnGi.setAdapter(null);
                }
            }
        });*/

    }
    //lấy maTinh theo ten tinh
    public String getMaTinh(String tenTinh){
        String maTinh="";
        for(int i=0;i<listTinhThanh.size();i++){
            if(listTinhThanh.get(i).getTenTinhThanh().equals(tenTinh)){
                maTinh=listTinhThanh.get(i).getMaTinhThanh();
            }

        }
        return maTinh.trim();
    }

    //hàm lấy hình set image viewpager khung chính
    public static List<Integer> getDefaultImageSlideShow() {
        List<Integer> mResources = new ArrayList<>();

        mResources.add(R.drawable.auto1);
        mResources.add(R.drawable.auto2);
        mResources.add(R.drawable.auto3);

        return mResources;
    }
}
