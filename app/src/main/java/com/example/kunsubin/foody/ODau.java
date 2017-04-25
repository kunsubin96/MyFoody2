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
import com.example.kunsubin.foody.Object.DanhMuc;
import com.example.kunsubin.foody.Object.Duong;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Object.TinhThanh;
import com.example.kunsubin.foody.RecyclerView.MoreItemView;
import com.example.kunsubin.foody.WebService.AsynCheckLogin;
import com.example.kunsubin.foody.WebService.AsynDuong;
import com.example.kunsubin.foody.WebService.AsynGetImage;
import com.example.kunsubin.foody.WebService.AsynGetInfoUser;
import com.example.kunsubin.foody.WebService.AsynQuanHuyen;
import com.example.kunsubin.foody.WebService.AsynTinhThanh;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import me.relex.circleindicator.CircleIndicator;

import static com.example.kunsubin.foody.R.layout.odau;

/**
 * Created by kunsubin on 3/29/2017.
 */

public class ODau extends android.support.v4.app.Fragment {
    ListView listViewMoiNhatODau;
    ListView khungChinhODau;
    LinearLayout layOutMoiNhatODau;
    TextView textMoiNhatODau;
    ListView listViewDanhMucODau;
    LinearLayout layoutDanhmucODau;
    TextView textViewDanhMucODau;
    AutoScrollViewPager view_pager_imageODau;

    LinearLayout listViewDiaDiemODau;
    LinearLayout layOutDiaDiemODau;
    TextView textViewDiaDiemODau;
    LinearLayout linear_layout_change_districtODau;
    TextView text_view_parent_districtODau;
    BottomNavigationViewEx bottomNavigationViewEx;
    TextView btnHuy;

    MoreItemView moreItemView;
    View slideShowBanner;
    CircleIndicator indicator;
    boolean trangThaiMoiNhatODau = true;
    boolean trangThaiDanhMucODau = true;
    boolean trangThaiDiaDiemODau = true;
    Context context;
    private static LayoutInflater mainA = null;
    MainActivity mainActivity;

    List<Integer> mResources;
    String maTinh = "";

    List<QuanHuyen> listQuanHuyen;
    ExpandableListView list_view_cityODau;
    List<TinhThanh> listTinhThanh;

    List<NhaHang> listNhaHang;
    BussinessNhaHang bussinessNhaHang;
    String TinhThanh = "";
    String QuanHuyen = "";
    String TabDanhMuc = "";
    String TabMoiNhat = "";
    List<QuanHuyen> listQuanHuyenTheoTinh;
    HashMap<QuanHuyen, List<Duong>> listDataChildODau;
    ExpandableListAdapterODau listAdapterDiaDiemODau;
    LinearLayout linear_layout_child_expand;
    List<String> countDuong;
    public ODau(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
        this.mainActivity = mainActivity;
        mainA = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(odau, container, false);

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

        //tạo các trạng thái ban đầu khi nhấn tab
        StaticData.setSelectedDanhMucODau(0);
        StaticData.setSelectedDiaDiemODau(-1);

        bussinessNhaHang = new BussinessNhaHang(context);


        //sự kiện khi nhấn tab thứ 1 bên chọn mới nhất
        layOutMoiNhatODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trangThaiMoiNhatODau) {
                    layOutMoiNhatODau.setBackgroundResource(R.color.nenKhungChinh);
                    layoutDanhmucODau.setBackgroundResource(R.drawable.my_button_bg);
                    layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                    //check xem list tab mới nhất có dữ liệu chưa, nếu chưa thì đưa vào
                    if (listViewMoiNhatODau.getCount() > 0) {

                    } else {
                        loadListViewMoiNhatODau(view);
                    }

                    listViewMoiNhatODau.setVisibility(View.VISIBLE);
                    listViewDanhMucODau.setVisibility(View.GONE);
                    listViewDiaDiemODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.GONE);


                    btnHuy.setVisibility(View.VISIBLE);
                    bottomNavigationViewEx.setVisibility(View.GONE);
                    //thiết lập lại các cờ
                    trangThaiMoiNhatODau = false;
                    trangThaiDanhMucODau = true;
                    trangThaiDiaDiemODau = true;
                } else {
                    layOutMoiNhatODau.setBackgroundResource(R.drawable.my_button_bg);
                    listViewMoiNhatODau.setVisibility(View.GONE);
                    listViewDanhMucODau.setVisibility(View.GONE);
                    listViewDiaDiemODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.VISIBLE);

                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationViewEx.setVisibility(View.VISIBLE);

                    trangThaiMoiNhatODau = true;
                    trangThaiDanhMucODau = true;
                    trangThaiDiaDiemODau = true;
                }
            }
        });
        //sự kiện khi nhấn tab thứ 2 bên chọn danh mục
        layoutDanhmucODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trangThaiDanhMucODau) {
                    layoutDanhmucODau.setBackgroundResource(R.color.nenKhungChinh);
                    layOutMoiNhatODau.setBackgroundResource(R.drawable.my_button_bg);
                    layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                    //check xem list tab mới nhất có dữ liệu chưa, nếu chưa thì đưa vào
                    if (listViewDanhMucODau.getCount() > 0) {

                    } else {
                        loadListViewDanhMucODau(view);
                    }

                    listViewDanhMucODau.setVisibility(View.VISIBLE);
                    listViewMoiNhatODau.setVisibility(View.GONE);
                    listViewDiaDiemODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.GONE);


                    btnHuy.setVisibility(View.VISIBLE);
                    bottomNavigationViewEx.setVisibility(View.GONE);

                    trangThaiDanhMucODau = false;
                    trangThaiMoiNhatODau = true;
                    trangThaiDiaDiemODau = true;
                } else {
                    layoutDanhmucODau.setBackgroundResource(R.drawable.my_button_bg);
                    listViewMoiNhatODau.setVisibility(View.GONE);
                    listViewDanhMucODau.setVisibility(View.GONE);
                    listViewDiaDiemODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.VISIBLE);

                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationViewEx.setVisibility(View.VISIBLE);

                    trangThaiDanhMucODau = true;
                    trangThaiMoiNhatODau = true;
                    trangThaiDiaDiemODau = true;

                }
            }
        });
        //sự kiện khi nhấn tab thứ 3 bên chọn địa điểm
        layOutDiaDiemODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trangThaiDiaDiemODau) {
                    layOutDiaDiemODau.setBackgroundResource(R.color.nenKhungChinh);
                    layOutMoiNhatODau.setBackgroundResource(R.drawable.my_button_bg);
                    layoutDanhmucODau.setBackgroundResource(R.drawable.my_button_bg);
                    //check xem list tab mới nhất có dữ liệu chưa, nếu chưa thì đưa vào
                    if (list_view_cityODau.getCount() > 0) {

                    } else {
                        String temp = textViewDiaDiemODau.getText().toString();

                        if(listTinhThanh.size()>0){

                           loadDiaDiemTheoTinh(getMaTinh(temp));
                        }
                        text_view_parent_districtODau.setTextColor(getResources().getColor(R.color.red));
                    }

                    listViewDiaDiemODau.setVisibility(View.VISIBLE);
                    listViewMoiNhatODau.setVisibility(View.GONE);
                    listViewDanhMucODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.GONE);

                    btnHuy.setVisibility(View.VISIBLE);
                    bottomNavigationViewEx.setVisibility(View.GONE);

                    trangThaiDanhMucODau = true;
                    trangThaiMoiNhatODau = true;
                    trangThaiDiaDiemODau = false;
                } else {
                    layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                    listViewMoiNhatODau.setVisibility(View.GONE);
                    listViewDanhMucODau.setVisibility(View.GONE);
                    listViewDiaDiemODau.setVisibility(View.GONE);
                    khungChinhODau.setVisibility(View.VISIBLE);

                    btnHuy.setVisibility(View.GONE);
                    bottomNavigationViewEx.setVisibility(View.VISIBLE);

                    trangThaiDanhMucODau = true;
                    trangThaiMoiNhatODau = true;
                    trangThaiDiaDiemODau = true;

                }
            }
        });
        //mở activity khi nhấn chọn thay đổi tỉnh thành
        linear_layout_change_districtODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChooseProvince.class);
                intent.putExtra("BussinessTinhThanh", text_view_parent_districtODau.getText().toString());
                startActivityForResult(intent, 1);


            }
        });
        //khi nhấn vào thành phố trong tab thứ 3 thì load lại dữ liệu theo khung chính
        text_view_parent_districtODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewDiaDiemODau.setText(text_view_parent_districtODau.getText());
                textViewDiaDiemODau.setTextColor(getResources().getColor(R.color.red));
                text_view_parent_districtODau.setTextColor(getResources().getColor(R.color.red));
                StaticData.setSelectedDiaDiemODau(-1);
                loadDiaDiemTheoTinh(getMaTinh(textViewDiaDiemODau.getText().toString().trim()));
                //load dữ liệu sau khi chọn tỉnh bên activity tỉnh thành
                TinhThanh = text_view_parent_districtODau.getText().toString().trim();
                QuanHuyen = "";
                TabDanhMuc = textViewDanhMucODau.getText().toString().trim();
                TabMoiNhat = textMoiNhatODau.getText().toString().trim();
                khungChinhODau.setAdapter(null);
                listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
                if (listNhaHang != null && listNhaHang.size() > 0)
                    khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
                else {
                    khungChinhODau.setAdapter(null);
                }
                //đóng listview khi chọn xong item
                layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                listViewDiaDiemODau.setVisibility(View.GONE);
                listViewMoiNhatODau.setVisibility(View.GONE);
                listViewDanhMucODau.setVisibility(View.GONE);
                khungChinhODau.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatODau = true;
                trangThaiDanhMucODau = true;
                trangThaiDiaDiemODau = true;
            }
        });
        //load dữ liệu khung chính
        loadDuLieuKhungChinh();
        return view;
    }
    //nhận dữ liệu trả về từ activity tỉnh thành
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                //lấy data nếu trả về là RESULT_OK
                String result = data.getStringExtra("result");
                maTinh = data.getStringExtra("idTinh");
                text_view_parent_districtODau.setText(result);
                text_view_parent_districtODau.setTextColor(getResources().getColor(R.color.red));
                textViewDiaDiemODau.setText(result);
                textViewDiaDiemODau.setTextColor(getResources().getColor(R.color.red));
                //công việc load lại list địa điểm
                //Toast.makeText(context,maTinh,Toast.LENGTH_SHORT).show();
                StaticData.setSelectedDiaDiemODau(-1);
                loadDiaDiemTheoTinh(maTinh);

                //đóng listview khi chọn xong item
                layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                listViewDiaDiemODau.setVisibility(View.GONE);
                listViewMoiNhatODau.setVisibility(View.GONE);
                listViewDanhMucODau.setVisibility(View.GONE);
                khungChinhODau.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatODau = true;
                trangThaiDanhMucODau = true;
                trangThaiDiaDiemODau = true;
                //load dữ liệu sau khi chọn tỉnh bên activity tỉnh thành
                TinhThanh = text_view_parent_districtODau.getText().toString().trim();
                if (TinhThanh.equals(textViewDiaDiemODau.getText().toString().trim()))
                    QuanHuyen = "";
                else QuanHuyen = textViewDiaDiemODau.getText().toString().trim();

                TabDanhMuc = textViewDanhMucODau.getText().toString().trim();
                TabMoiNhat = textMoiNhatODau.getText().toString().trim();
                khungChinhODau.setAdapter(null);
                //lấy dữ liệu từ database đổ lên listNhaHang
                listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
                if (listNhaHang != null && listNhaHang.size() > 0)
                    khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
                else {
                    khungChinhODau.setAdapter(null);
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //hủy lấy data nếu trả về là RESULT_OK
            }
        }
    }
    //ánh xạ các view
    public void init(View view) {
        listViewMoiNhatODau = (ListView) view.findViewById(R.id.listViewMoiNhatODau);
        khungChinhODau = (ListView) view.findViewById(R.id.khungChinhODau);
        layOutMoiNhatODau = (LinearLayout) view.findViewById(R.id.layOutMoiNhatODau);
        textMoiNhatODau = (TextView) view.findViewById(R.id.textViewMoiNhatODau);

        listViewDanhMucODau = (ListView) view.findViewById(R.id.listViewDanhMucOdau);
        layoutDanhmucODau = (LinearLayout) view.findViewById(R.id.layOutDanhMucODau);
        textViewDanhMucODau = (TextView) view.findViewById(R.id.textViewDanhMucODau);

        listViewDiaDiemODau = (LinearLayout) view.findViewById(R.id.listViewDiaDiemODau);
        layOutDiaDiemODau = (LinearLayout) view.findViewById(R.id.layOutDiaDiemODau);
        textViewDiaDiemODau = (TextView) view.findViewById(R.id.textViewDiaDiemODau);
        text_view_parent_districtODau = (TextView) view.findViewById(R.id.text_view_parent_districtODau);
        list_view_cityODau = (ExpandableListView) view.findViewById(R.id.list_view_cityODau);

        linear_layout_change_districtODau = (LinearLayout) view.findViewById(R.id.linear_layout_change_districtODau);
        bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity.findViewById(R.id.bottomNavigationView);
        btnHuy = (TextView) mainActivity.findViewById(R.id.btnHuy);
        linear_layout_child_expand=(LinearLayout)view.findViewById(R.id.linear_layout_child_expand);

    }

    //load dữ liệu listview khung chính ban đầu mới vào
    public void loadDuLieuKhungChinh() {
        slideShowBanner = mainA.inflate(R.layout.layout_image_viewpager_main, khungChinhODau, false);
        view_pager_imageODau = (AutoScrollViewPager) slideShowBanner.findViewById(R.id.view_pager_image);
        mResources = this.getDefaultImageSlideShow();
        ImageAdapter adapter = new ImageAdapter(context, mResources);
        indicator = (CircleIndicator) slideShowBanner.findViewById(R.id.indicator);
        view_pager_imageODau.setAdapter(adapter);

        view_pager_imageODau.setInterval(2000);
        view_pager_imageODau.setStopScrollWhenTouch(false);
        view_pager_imageODau.setBorderAnimation(false);
        view_pager_imageODau.setCycle(true);
        view_pager_imageODau.startAutoScroll();

        indicator.setViewPager(view_pager_imageODau);
        //add khung hình chuyển động vào trong header listview
        khungChinhODau.addHeaderView(slideShowBanner);
        //add item more iteme menu trong khung chính
        moreItemView = new MoreItemView(context);
        khungChinhODau.addHeaderView(moreItemView);
        //load dữ liệu trang chính
        TinhThanh = "TP.HCM";
        QuanHuyen = "";
        TabDanhMuc = "Danh mục";
        TabMoiNhat = "Mới nhất";
        khungChinhODau.setAdapter(null);
        listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
        if (listNhaHang != null && listNhaHang.size() > 0)
            khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
        else {
            khungChinhODau.setAdapter(null);
        }
        //Toast.makeText(context,listNhaHang.size()+"",Toast.LENGTH_SHORT).show();
    }

    //load dữ liệu lên list view mới nhất
    public void loadListViewMoiNhatODau(View view) {

        int[] prgmImages = {R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh3, R.drawable.hinh4,
                R.drawable.hinh5, R.drawable.hinh6, R.drawable.hinh7, R.drawable.hinh8};
        String[] prgmNameList = {"Mới nhất", "Gần tôi", "Phổ biến", "Du khách", "Ưu đãi Ecard", "Đặt chỗ", "Ưu đãi thẻ", "Đặt giao hàng"};
        listViewMoiNhatODau.setAdapter(new CustomAdapterMoiNhat(mainActivity, prgmNameList, prgmImages, true));
        listViewMoiNhatODau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //set trạng thái item select
                stateSelectedItemListView(adapterView, view);
                //đóng listview khi chọn xong item
                layOutMoiNhatODau.setBackgroundResource(R.drawable.my_button_bg);
                listViewMoiNhatODau.setVisibility(View.GONE);
                listViewDanhMucODau.setVisibility(View.GONE);
                listViewDiaDiemODau.setVisibility(View.GONE);
                khungChinhODau.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatODau = true;
                trangThaiDanhMucODau = true;
                trangThaiDiaDiemODau = true;
                //load dữ liệu sau khi chọn tab mới nhất
                TinhThanh = text_view_parent_districtODau.getText().toString().trim();
                if (TinhThanh.equals(textViewDiaDiemODau.getText().toString().trim()))
                    QuanHuyen = "";
                else QuanHuyen = textViewDiaDiemODau.getText().toString().trim();

                TabDanhMuc = textViewDanhMucODau.getText().toString().trim();
                TabMoiNhat = textMoiNhatODau.getText().toString().trim();
                khungChinhODau.setAdapter(null);
                listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
                if (listNhaHang != null && listNhaHang.size() > 0)
                    khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
                else {
                    khungChinhODau.setAdapter(null);
                }

            }
        });
    }

    //trạng thái khi chọn item trong listview mới nhất ODau tô màu chữ hình, dấu check
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
        textMoiNhatODau.setText(tv.getText());

    }

    //load dữ liệu lên list view danhmuc
    public void loadListViewDanhMucODau(View view) {
        int[] prgmImages = {0, R.drawable.danhmuc1, R.drawable.danhmuc2, R.drawable.danhmuc3,
                R.drawable.danhmuc4, R.drawable.danhmuc5, R.drawable.danhmuc6, R.drawable.danhmuc7,
                R.drawable.danhmuc8, R.drawable.danhmuc9, R.drawable.danhmuc10, R.drawable.danhmuc11, R.drawable.danhmuc12,
                R.drawable.danhmuc13, R.drawable.danhmuc14, R.drawable.danhmuc15, R.drawable.danhmuc16,
                R.drawable.danhmuc17, R.drawable.danhmuc18, R.drawable.danhmuc19, R.drawable.danhmuc20,
                R.drawable.danhmuc21, R.drawable.danhmuc22, R.drawable.danhmuc23, R.drawable.danhmuc24,
                R.drawable.danhmuc25, R.drawable.danhmuc26, R.drawable.danhmuc27, R.drawable.danhmuc28,
                R.drawable.danhmuc29, R.drawable.danhmuc30};
        String[] prgmNameList = {"Danh mục", "Sang trọng", "Buffet", "Nhà hàng",
                "Ăn vặt/vỉa hè", "Ăn chay", "Cafe/Dessert", "Quán ăn", "Bar/Pub", "Quán nhậu",
                "Beer club", "Tiệm bánh", "Tiệc tận nơi", "Shop online", "Giao cơm văn phòng",
                "Khu ẩm thực", "Việt Nam", "Châu Mỹ", "Mỹ", "Tây Âu", "Ý", "Pháp", "Đức", "Tây Ban Nha", "Trung Hoa", "Ấn Độ",
                "Thái Lan", "Nhật Bản", "Hàn Quốc", "Malaysia", "Quốc tế"};

        listViewDanhMucODau.setAdapter(new CustomAdapterDanhMucODau(mainActivity, prgmNameList, prgmImages));
        listViewDanhMucODau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                StaticData.setSelectedDanhMucODau(position);
                //Toast.makeText(context,adapterView.getChildCount()+" "+adapterView.getCount()+ " "+position,Toast.LENGTH_SHORT).show();
                //set trạng thái item select
                stateSelectedItemListViewDanhMuc(adapterView, view);

                //đóng listview khi chọn xong item
                layoutDanhmucODau.setBackgroundResource(R.drawable.my_button_bg);
                listViewMoiNhatODau.setVisibility(View.GONE);
                listViewDanhMucODau.setVisibility(View.GONE);
                listViewDiaDiemODau.setVisibility(View.GONE);
                khungChinhODau.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);

                trangThaiMoiNhatODau = true;
                trangThaiDanhMucODau = true;
                trangThaiDiaDiemODau = true;
                //load dữ liệu sau khi chọn tab danh mục
                TinhThanh = text_view_parent_districtODau.getText().toString().trim();
                if (TinhThanh.equals(textViewDiaDiemODau.getText().toString().trim()))
                    QuanHuyen = "";
                else QuanHuyen = textViewDiaDiemODau.getText().toString().trim();

                TabDanhMuc = textViewDanhMucODau.getText().toString().trim();
                TabMoiNhat = textMoiNhatODau.getText().toString().trim();
                khungChinhODau.setAdapter(null);
                listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
                if (listNhaHang != null && listNhaHang.size() > 0)
                    khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
                else {
                    khungChinhODau.setAdapter(null);
                }

            }
        });

    }

    //trạng thái khi chọn item trong listview mới nhất ODau tô màu chữ hình, dấu check
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
        textViewDanhMucODau.setText(tv.getText());
        if (tv.getText() == "Danh mục") {
            textViewDanhMucODau.setTextColor(getResources().getColor(R.color.textColorBar));
        } else {
            textViewDanhMucODau.setTextColor(getResources().getColor(R.color.red));
        }

    }
    ////load dữ liệu lên list view khi chọn quận huyện, tinh thành
    public void loadDiaDiemTheoTinh(String maTinh) {
        listQuanHuyenTheoTinh =new ArrayList<>();
        maTinh=maTinh.trim();
        //lấy các huyện trong list quan huyen theo tinh chon
        for (int i = 0; i < listQuanHuyen.size(); i++) {
            if (listQuanHuyen.get(i).getMaTinhThanh().trim().equals(maTinh))
                listQuanHuyenTheoTinh.add(listQuanHuyen.get(i));
        }
        listDataChildODau=new HashMap<QuanHuyen, List<Duong>>();
        countDuong=new ArrayList<>();
        for (int i=0;i<listQuanHuyenTheoTinh.size();i++){
            AsynDuong asynDuong=new AsynDuong();
            try {
                List<Duong> duongList=asynDuong.execute(listQuanHuyenTheoTinh.get(i).getMaQuanHuyen().toString().trim()).get();
                countDuong.add(String.valueOf(duongList.size()));
                //
                listDataChildODau.put(listQuanHuyenTheoTinh.get(i),duongList);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        listAdapterDiaDiemODau=new ExpandableListAdapterODau(mainActivity,listQuanHuyenTheoTinh,listDataChildODau,countDuong);
        list_view_cityODau.setAdapter(listAdapterDiaDiemODau);

        list_view_cityODau.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                StaticData.setSelectedDiaDiemODau(i);

                //Toast.makeText(getContext(), "Group:" +String.valueOf(i),Toast.LENGTH_LONG).show();
                //Toast.makeText(getContext(), "Group:" +String.valueOf(expandableListView.ge()),Toast.LENGTH_LONG).show();
                return false;
            }
        });
        list_view_cityODau.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getContext(),"Group:"+String.valueOf(i)+ "  Item:" +String.valueOf(i1),Toast.LENGTH_LONG).show();

                return false;
            }
        });
       // Toast.makeText(getContext(),String.valueOf(listQuanHuyen.get(0).getMaTinhThanh())+String.valueOf(listQuanHuyenTheoTinh.size()),Toast.LENGTH_LONG).show();
        //list_view_cityODau.setAdapter();


      /*  list_view_cityODau.setAdapter(new CustomAdapterDiaDiemODau(mainActivity, listQuanHuyenTheoTinh));
        list_view_cityODau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv;
                text_view_parent_districtODau.setTextColor(getResources().getColor(R.color.black));
                for (int j = 0; j < adapterView.getChildCount(); j++) {
                    tv = (TextView) adapterView.getChildAt(j).findViewById(R.id.text_view_district_name);
                    tv.setTextColor(getResources().getColor(R.color.black));
                }
                tv = (TextView) view.findViewById(R.id.text_view_district_name);
                tv.setTextColor(getResources().getColor(R.color.red));
                StaticData.setSelectedDiaDiemODau(i);
                textViewDiaDiemODau.setText(tv.getText());
                textViewDiaDiemODau.setTextColor(getResources().getColor(R.color.red));
                text_view_parent_districtODau.setTextColor(getResources().getColor(R.color.black));
                //đóng listview khi chọn xong item
                layOutDiaDiemODau.setBackgroundResource(R.drawable.my_button_bg);
                listViewDiaDiemODau.setVisibility(View.GONE);
                listViewMoiNhatODau.setVisibility(View.GONE);
                listViewDanhMucODau.setVisibility(View.GONE);
                khungChinhODau.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.GONE);
                bottomNavigationViewEx.setVisibility(View.VISIBLE);
                trangThaiMoiNhatODau = true;
                trangThaiDanhMucODau = true;
                trangThaiDiaDiemODau = true;
                //load dữ liệu sau khi chọn tab địa điểm
                TinhThanh = text_view_parent_districtODau.getText().toString().trim();
                if (TinhThanh.equals(textViewDiaDiemODau.getText().toString().trim()))
                    QuanHuyen = "";
                else QuanHuyen = textViewDiaDiemODau.getText().toString().trim();

                TabDanhMuc = textViewDanhMucODau.getText().toString().trim();
                TabMoiNhat = textMoiNhatODau.getText().toString().trim();
                khungChinhODau.setAdapter(null);
                listNhaHang = bussinessNhaHang.getListNhaHang(TinhThanh, QuanHuyen, TabDanhMuc, TabMoiNhat);
                if (listNhaHang != null && listNhaHang.size() > 0)
                    khungChinhODau.setAdapter(new CustomAdapterNhaHangODau(mainActivity, listNhaHang));
                else {
                    khungChinhODau.setAdapter(null);
                }
            }
        });*/

    }
    //lấy maTinh theo ten tinh
    public String getMaTinh(String tenTinh) {
        String maTinh = "";
        for (int i = 0; i < listTinhThanh.size(); i++) {
            if (listTinhThanh.get(i).getTenTinhThanh().equals(tenTinh)) {
                maTinh = listTinhThanh.get(i).getMaTinhThanh();
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
