package com.example.kunsubin.foody.BussinessAccess;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.kunsubin.foody.DataAccess.DataAccess;
import com.example.kunsubin.foody.Object.BinhLuan;
import com.example.kunsubin.foody.Object.DanhMuc;
import com.example.kunsubin.foody.Object.Info;
import com.example.kunsubin.foody.Object.NhaHang;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.TinhThanh;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class BussinessNhaHang extends DataAccess {
    BussinessBinhLuan bussinessBinhLuan;
    BussinessMoreImageNhaHang bussinessMoreImageNhaHang;

    BussinessTinhThanh bussinessTinhThanh;
    BussinessQuanHuyen bussinessQuanHuyen;
    BussinessDanhMuc bussinessDanhMuc;

    List<TinhThanh> listTinhThanh;
    List<QuanHuyen> listQuanHuyen;
    List<DanhMuc> listDanhMuc;
    public BussinessNhaHang(Context context) {
        super(context);
        bussinessBinhLuan = new BussinessBinhLuan(context);
        bussinessMoreImageNhaHang = new BussinessMoreImageNhaHang(context);

        bussinessTinhThanh=new BussinessTinhThanh(context);
        bussinessQuanHuyen=new BussinessQuanHuyen(context);
        bussinessDanhMuc=new BussinessDanhMuc(context);

        listTinhThanh=(List<TinhThanh>) bussinessTinhThanh.executeSelect();
        listQuanHuyen=(List<QuanHuyen>) bussinessQuanHuyen.executeSelect();
        listDanhMuc=(List<DanhMuc>) bussinessDanhMuc.executeSelect();
    }

    @Override
    public void open() {
        super.open();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    public List<NhaHang> getListNhaHang(String TinhThanh, String QuanHuyen, String DanhMuc, String MoiNhat) {
        //get mã tỉnh thành, quận huyện, danh mục
        String maTinh=getMaTinhThanh(TinhThanh);
        String maHuyen=getQuanHuyen(maTinh,QuanHuyen);
        String danhMuc=getDanhMuc(DanhMuc);
        Log.d("maTinh",maTinh);
        Log.d("maHuyen",maHuyen);
         Log.d("danhMuc",danhMuc);
        List<NhaHang> list = new ArrayList<>();
        this.open();
        String query;
        if(maHuyen.equals("")){
            if(danhMuc.equals("danhmuc")){
                query="SELECT * FROM tbl_nhahang WHERE ma_tinhthanh='"+maTinh+"'";
            }else{
                query="SELECT * FROM tbl_nhahang WHERE ma_tinhthanh='"+maTinh+"' and id_danhmucODau='"+danhMuc+"'";
            }
        }else{
            if(danhMuc.equals("danhmuc")){
                query="SELECT * FROM tbl_nhahang WHERE ma_quanhuyen='"+maHuyen+"'";
            }else{
                query="SELECT * FROM tbl_nhahang WHERE ma_quanhuyen='"+maHuyen+"' and id_danhmucODau='"+danhMuc+"'";
            }
        }

        if(MoiNhat.equals("Phổ biến")){
            query+=" order by luot_xem  desc";
        }else{
            query+="";
        }
        Log.d("QUERY",query);
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                NhaHang item = new NhaHang();
                item.setId(cursor.getString(0));
                item.setName(cursor.getString(3));
                item.setDiaChi(cursor.getString(4));
                item.setDanhGia(round(cursor.getDouble(5), 1));
                item.setSDT(cursor.getString(6));
                item.setHinh(cursor.getBlob(7));
                item.setLuotXem(cursor.getInt(8));
                item.setDanhMucODau(cursor.getString(9));
                item.setDanhMucAnGi(cursor.getString(10));

                List<BinhLuan> binhluan = bussinessBinhLuan.getBinhLuanList(cursor.getString(0));
                item.setListBinhLuan(binhluan);
                List<byte[]> moreImages = bussinessMoreImageNhaHang.getListMoreImage(cursor.getString(0));
                item.setListHinh(moreImages);

                list.add(item);

            }
        } finally {
            cursor.close();
            this.close();
        }
        return list;
    }
    public List<NhaHang> getListNhaHangAnGi(String TinhThanh, String QuanHuyen, String DanhMuc, String MoiNhat) {
        //get mã tỉnh thành, quận huyện, danh mục
        String maTinh=getMaTinhThanh(TinhThanh);
        String maHuyen=getQuanHuyen(maTinh,QuanHuyen);
        String danhMuc=getDanhMuc(DanhMuc);
        Log.d("maTinhAnGi",maTinh);
        Log.d("maHuyenAnGi",maHuyen);
        Log.d("danhMucAnGi",danhMuc);
        List<NhaHang> list = new ArrayList<>();
        this.open();
        String query;
        if(maHuyen.equals("")){
            if(danhMuc.equals("danhmuc")){
                query="SELECT * FROM tbl_nhahang WHERE ma_tinhthanh='"+maTinh+"'";
            }else{
                query="SELECT * FROM tbl_nhahang WHERE ma_tinhthanh='"+maTinh+"' and id_danhmuc_AnGi='"+danhMuc+"'";
            }
        }else{
            if(danhMuc.equals("danhmuc")){
                query="SELECT * FROM tbl_nhahang WHERE ma_quanhuyen='"+maHuyen+"'";
            }else{
                query="SELECT * FROM tbl_nhahang WHERE ma_quanhuyen='"+maHuyen+"' and id_danhmuc_AnGi='"+danhMuc+"'";
            }
        }

        if(MoiNhat.equals("Phổ biến")){
            query+=" order by luot_xem  desc";
        }else{
            query+="";
        }
        Log.d("QUERYAngi",query);
        Cursor cursor = this.database.rawQuery(query, null);
        Log.d("CursorAnGi",cursor.getCount()+"");
        try {
            while (cursor.moveToNext()) {
                NhaHang item = new NhaHang();
                item.setId(cursor.getString(0));
                item.setName(cursor.getString(3));
                item.setDiaChi(cursor.getString(4));
                item.setHinh(cursor.getBlob(7));

                item.setMonChinh(getMonAn(cursor.getString(0)));
                Info info=getInfo(cursor.getString(0));
                item.setInfo(info);

                list.add(item);

            }
        } finally {
            cursor.close();
            this.close();
        }
        return list;
    }
    public String getMonAn(String Id_NhaHang){
        this.open();
        String monAn="";
        String query="SELECT * FROM tbl_monan WHERE id_nhahang='"+Id_NhaHang+"'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                 if(cursor.getString(1)!=null)
                     monAn=cursor.getString(1);
            }
        } finally {
            cursor.close();
        }
        return monAn;
    }
    public Info getInfo(String Id_NhaHang){
        this.open();
        Info info=null;
        String query="SELECT * FROM tbl_info WHERE id_nhahang='"+Id_NhaHang+"'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                info=new Info();
                info.setId(cursor.getString(0));
                info.setPhoto(cursor.getBlob(1));
                info.setDate(cursor.getString(2));
                info.setName(cursor.getString(4));
            }
        } finally {
            cursor.close();
        }
        return info;
    }
    public String getMaTinhThanh(String tenTinh){
        String maTinh="";
        for(int i=0;i<listTinhThanh.size();i++){
            if(listTinhThanh.get(i).getTenTinhThanh().equals(tenTinh))
            {
                maTinh=listTinhThanh.get(i).getMaTinhThanh();
                break;
            }
        }
        return maTinh;
    }
    public String getDanhMuc(String tenDM){
        String danhMuc="";
        for(int i=0;i<listDanhMuc.size();i++){
            if(listDanhMuc.get(i).getTen().equals(tenDM))
            {
                danhMuc=listDanhMuc.get(i).getId();
                break;
            }
        }
        return danhMuc;
    }
    public String getQuanHuyen(String maTinh,String tenQuanHuyen){
        String maHuyen="";
        for(int i=0;i<listQuanHuyen.size();i++){
            if(listQuanHuyen.get(i).getMaTinhThanh().equals(maTinh)&&listQuanHuyen.get(i).getTenQuanHuyen().equals(tenQuanHuyen)){
                maHuyen=listQuanHuyen.get(i).getMaQuanHuyen();
                break;
            }
        }
        return maHuyen;
    }
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
