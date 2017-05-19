package com.example.kunsubin.foody.Object;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.kunsubin.foody.R;
import com.google.gson.JsonObject;

/**
 * Created by kunsubin on 4/6/2017.
 */

public class StaticData {
    //select tỉnh thành được chọn đề sét check với text view mặc định
    static int selected;

    public static int getSelected() {
        return selected;
    }

    public static void setSelected(int selected) {
        StaticData.selected = selected;
    }

    public static int getSelectedDanhMucODau() {
        return selectedDanhMucODau;
    }

    public static void setSelectedDanhMucODau(int selectedDanhMucODau) {
        StaticData.selectedDanhMucODau = selectedDanhMucODau;
    }

    static int selectedDanhMucODau;


    public static int getSelectedDanhMucAnGi() {
        return selectedDanhMucAnGi;
    }

    public static void setSelectedDanhMucAnGi(int selectedDanhMucAnGi) {
        StaticData.selectedDanhMucAnGi = selectedDanhMucAnGi;
    }

    static int selectedDanhMucAnGi;

    public static int getSelectedDiaDiemODau() {
        return selectedDiaDiemODau;
    }

    public static void setSelectedDiaDiemODau(int selectedDiaDiemODau) {
        StaticData.selectedDiaDiemODau = selectedDiaDiemODau;
    }

    static int selectedDiaDiemODau;

    public static int getSelectedDiaDiemAnGi() {
        return selectedDiaDiemAnGi;
    }

    public static void setSelectedDiaDiemAnGi(int selectedDiaDiemAnGi) {
        StaticData.selectedDiaDiemAnGi = selectedDiaDiemAnGi;
    }

    static int selectedDiaDiemAnGi;

    public static ObjectInfoUser getObjectInfoUser() {
        return objectInfoUser;
    }

    public static void setObjectInfoUser(ObjectInfoUser objectInfoUser) {
        StaticData.objectInfoUser = objectInfoUser;
    }

    static ObjectInfoUser objectInfoUser;

    static int groupODau;

    public static int getChildODau() {
        return childODau;
    }

    public static void setChildODau(int childODau) {
        StaticData.childODau = childODau;
    }

    public static int getGroupODau() {
        return groupODau;
    }

    public static void setGroupODau(int groupODau) {
        StaticData.groupODau = groupODau;
    }

    static int childODau;
    static int groupAnGi;

    public static int getChildAnGi() {
        return childAnGi;
    }

    public static void setChildAnGi(int childAnGi) {
        StaticData.childAnGi = childAnGi;
    }

    public static int getGroupAnGi() {
        return groupAnGi;
    }

    public static void setGroupAnGi(int groupAnGi) {
        StaticData.groupAnGi = groupAnGi;
    }

    static int childAnGi;

    public static int getPossionGioiTinh() {
        return PossionGioiTinh;
    }

    public static void setPossionGioiTinh(int possionGioiTinh) {
        PossionGioiTinh = possionGioiTinh;
    }

    static int PossionGioiTinh;

    public static int getPossionMarry() {
        return PossionMarry;
    }

    public static void setPossionMarry(int possionMarry) {
        PossionMarry = possionMarry;
    }

    static int PossionMarry;

    public static NhaHang getNhaHang() {
        return nhaHang;
    }

    public static void setNhaHang(NhaHang nhaHang) {
        StaticData.nhaHang = nhaHang;
    }

    static NhaHang nhaHang;

    public static final JsonObject GET_ERROR_OBJECT() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", false);
        jsonObject.addProperty("message", "Something went wrong");
        return jsonObject;
    }

    public static String getIDTinhThanh() {
        return IDTinhThanh;
    }

    public static void setIDTinhThanh(String IDTinhThanh) {
        StaticData.IDTinhThanh = IDTinhThanh;
    }

    static String IDTinhThanh;

    public static String getTenTinhThanh() {
        return TenTinhThanh;
    }

    public static void setTenTinhThanh(String tenTinhThanh) {
        TenTinhThanh = tenTinhThanh;
    }

    static String TenTinhThanh;

    public static void shakeView(Context context, View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake));
    }
}
