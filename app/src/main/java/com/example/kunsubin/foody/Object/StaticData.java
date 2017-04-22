package com.example.kunsubin.foody.Object;

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


}
