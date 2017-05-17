package com.example.kunsubin.foody;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kunsubin.foody.BaseSlideActivity.BaseSlideActivity;
import com.example.kunsubin.foody.Object.DanhMuc;
import com.example.kunsubin.foody.Object.ImageGalleryBean;
import com.example.kunsubin.foody.Object.MenuBarItemBean;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Object.TinhThanh;
import com.example.kunsubin.foody.Object.Type;
import com.example.kunsubin.foody.Other.ListItemDialog;
import com.example.kunsubin.foody.Other.ListItemDialogTypeRes;
import com.example.kunsubin.foody.WebService.AsynDanhMuc;
import com.example.kunsubin.foody.WebService.AsynQuanHuyen;
import com.example.kunsubin.foody.WebService.AsynTinhThanh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddNhaHang extends BaseSlideActivity implements View.OnClickListener,IOnClickImageNH{
    public static final int POPUP_CHOOSE_PROVINCE = 0;
    public static final int POPUP_CHOOSE_DISTRICT = 1;
    public static final int POPUP_CHOOSE_RESTYPE = 2;
    LinearLayout back_button_add_place;

    TextView text_view_choose_province;
    TextView text_view_choose_district;
    TextView text_view_done;

    EditText edit_text_name_res;
    LinearLayout linear_layout_choose_type_res;
    EditText edit_text_address_res;
    TextView text_view_res_type;

    LinearLayout linear_layout_map_location;
    TextView text_view_lat_long;
    LinearLayout linear_layout_phone_number;
    TextView text_view_open_time;
    TextView text_view_close_time;
    EditText edit_text_max_cash;
    EditText edit_text_min_cash;
    EditText edit_text_short_descr;

    FrameLayout frame_layout_add_image;

    TimePickerDialog openTimePiker;
    TimePickerDialog closeTimePiker;


    GallerySelectedFileAdapter selectedFileAdapter;
    RecyclerView grid_view_file;
    TextView text_view_photo_count;
    int positionSelectedProvince = 5;

    int positionSelectedDistrict = 0;
    ListItemDialog dialogChooseProvince;
    DialogAdapter choosePronvinceDialogAdapter;
    //ProvinceController provinceController;

    ListItemDialog dialogChooseDistrict;
    DialogAdapter chooseDistrictDialogAdapter;
   // DistrictController districtController;

    ListItemDialogTypeRes dialogChooseResType;
    DialogAdapter chooseResTypeAdapter;
    //MenuBarItemController menuBarItemController;

    List<TinhThanh> tinhThanhList;
    List<QuanHuyen> quanHuyenList;

    String curDistrictID;
    String curProvinceID ;
    List<MenuBarItemBean> selectedResType = new ArrayList<>();
    double lat = -1;
    double lng = -1;
    ArrayList<ImageGalleryBean> selectedImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nha_hang);

        curProvinceID="tphcm";
        tinhThanhList=new ArrayList<>();
        quanHuyenList=new ArrayList<>();
        //get

        //
        initView();
        initEvent();
        //initController();
        initPopup();
        setDefaultDisplay();
    }
/*    private void initController() {
        provinceController = new ProvinceController(this.getApplicationContext(), "vietnam", false);
        districtController = new DistrictController(this.getApplicationContext());
        menuBarItemController = new MenuBarItemController(this.getApplicationContext(), APIAction.GET_CATEGORY_WHAT2DO);
    }*/

    private void initPopup() {
        buildPopup(POPUP_CHOOSE_PROVINCE);
        buildPopup(POPUP_CHOOSE_DISTRICT);
        buildPopup(POPUP_CHOOSE_RESTYPE);
    }

    private void initView() {
        back_button_add_place=(LinearLayout)findViewById(R.id.back_button_add_place);
        text_view_done = (TextView) findViewById(R.id.text_view_done);
        text_view_choose_province = (TextView) findViewById(R.id.text_view_choose_province);
        text_view_choose_district = (TextView) findViewById(R.id.text_view_choose_district);

        edit_text_name_res = (EditText) findViewById(R.id.edit_text_name_res);
        linear_layout_choose_type_res = (LinearLayout) findViewById(R.id.linear_layout_choose_type_res);
        edit_text_address_res = (EditText) findViewById(R.id.edit_text_address_res);

        linear_layout_map_location = (LinearLayout) findViewById(R.id.linear_layout_map_location);
        text_view_lat_long = (TextView) findViewById(R.id.text_view_lat_long);
        linear_layout_phone_number = (LinearLayout) findViewById(R.id.linear_layout_phone_number);
        text_view_open_time = (TextView) findViewById(R.id.text_view_open_time);
        text_view_close_time = (TextView) findViewById(R.id.text_view_close_time);
        edit_text_max_cash = (EditText) findViewById(R.id.edit_text_max_cash);
        edit_text_min_cash = (EditText) findViewById(R.id.edit_text_min_cash);
        edit_text_short_descr = (EditText) findViewById(R.id.edit_text_short_descr);
        text_view_res_type = (TextView) findViewById(R.id.text_view_res_type);

        frame_layout_add_image = (FrameLayout) findViewById(R.id.frame_layout_add_image);

        text_view_photo_count = (TextView) findViewById(R.id.text_view_photo_count);

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        openTimePiker = new TimePickerDialog(this, onOpenTimeSet, hour, minute, true);
        closeTimePiker = new TimePickerDialog(this, onCloseTimeSet, hour, minute, true);


        grid_view_file = (RecyclerView) findViewById(R.id.recycle_view);
        selectedFileAdapter = new GallerySelectedFileAdapter(getApplicationContext(), selectedImages, Type.IMAGESELECTED_INADDPLACE);
        selectedFileAdapter.setiOnClickImage(this);


        grid_view_file.setLayoutManager(new GridLayoutManager(this, 3));
        grid_view_file.setAdapter(selectedFileAdapter);

    }

    TimePickerDialog.OnTimeSetListener onOpenTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNhaHang.this.text_view_open_time.setText(hourOfDay + ":" + minute);
        }
    };
    TimePickerDialog.OnTimeSetListener onCloseTimeSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddNhaHang.this.text_view_close_time.setText(hourOfDay + ":" + minute);
        }
    };

    private void initEvent() {
        back_button_add_place.setOnClickListener(this);
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
        linear_layout_choose_type_res.setOnClickListener(this);
        linear_layout_map_location.setOnClickListener(this);
        text_view_open_time.setOnClickListener(this);
        text_view_close_time.setOnClickListener(this);
        text_view_done.setOnClickListener(this);
        frame_layout_add_image.setOnClickListener(this);
    }

    private void setDefaultDisplay() {
        addLayoutNewPhone();
        text_view_choose_province.setText(StaticData.getTenTinhThanh());
    }


    private void addLayoutNewPhone() {
        View v = View.inflate(this, R.layout.item_phone_layout, null);
        ImageView image_view_add_phone_number = (ImageView) v.findViewById(R.id.image_view_add_phone_number);
        image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.icon_plus_blue));
        image_view_add_phone_number.setTag(10);
        image_view_add_phone_number.setOnClickListener(new OnClickAddNumber(this.getApplicationContext(), image_view_add_phone_number, v));
        this.linear_layout_phone_number.addView(v);
    }

    class OnClickAddNumber implements View.OnClickListener {
        Context context;
        ImageView image_view_add_phone_number;
        View layout;

        public OnClickAddNumber(Context context, ImageView image_view_add_phone_number, View layout) {
            this.context = context;
            this.image_view_add_phone_number = image_view_add_phone_number;
            this.layout = layout;
        }

        @Override
        public void onClick(View v) {
            if ((Integer) image_view_add_phone_number.getTag() == 10) {
                addLayoutNewPhone();
                image_view_add_phone_number.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_minus_gray));
                image_view_add_phone_number.setTag(100);
                return;
            }
            AddNhaHang.this.linear_layout_phone_number.removeView(layout);
            ImageView image_view_add_phone_number_last = (ImageView) AddNhaHang.this.linear_layout_phone_number
                    .getChildAt(AddNhaHang.this.linear_layout_phone_number.getChildCount() - 1)
                    .findViewById(R.id.image_view_add_phone_number);
            image_view_add_phone_number_last.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.icon_plus_blue));
            image_view_add_phone_number_last.setTag(10);

        }
    }
    private void updateNumofPhoto() {
        if (this.selectedFileAdapter.imageSelected.size() > 0) {
            text_view_photo_count.setText("" + this.selectedFileAdapter.imageSelected.size());
            text_view_photo_count.setVisibility(View.VISIBLE);
        } else {
            text_view_photo_count.setText("");
            text_view_photo_count.setVisibility(View.GONE);
        }
    }

    private void updateLatLong() {
        if (lat == -1 || lng == -1) {
            text_view_lat_long.setText("Lat " + "0" + " - " + "Long " + "0");
            return;
        }
        text_view_lat_long.setText("Lat " + this.lat + " - " + "Long " + this.lng);
    }
    /*private List<MoreImageRestaurantBean> getListMoreItem(final String resid) {
        List<MoreImageRestaurantBean> list = new ArrayList<>();
        for (int i = 1; i < selectedImages.size(); i++) {
            MoreImageRestaurantBean item = new MoreImageRestaurantBean(UUID.randomUUID().toString(), resid,
                    GlobalFunction.decodeImage2Base64(selectedImages.get(i).getPath()));

            list.add(item);
        }
        return list;
    }*/

    private String getAllPhoneNumber() {
        String result = "";
        for (int i = 0; i < this.linear_layout_phone_number.getChildCount(); i++) {
            View v = this.linear_layout_phone_number.getChildAt(i);
            String s = ((EditText) v.findViewById(R.id.edit_text_phone_number)).getText().toString();
            result = result + s + "-";
        }
        return result.substring(0, result.length() - 1);
    }
    private void updateTextResType() {
        this.text_view_res_type.setText("");
        String resType = "";
        if (selectedResType.size() <= 0) {
            this.text_view_res_type.setText("Loại hình địa điểm *");
            return;
        }
        for (MenuBarItemBean i : selectedResType) {
            resType += i.getTittle() + ", ";
        }

        resType = resType.substring(0, resType.length() - 2);
        this.text_view_res_type.setText(resType);
    }
    private void showAlert(String mess) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder
                .setMessage(mess)
                .setCancelable(false)
                .setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialogBuilder.show();
    }

    private void buildPopup(int type) {
        if (type == POPUP_CHOOSE_PROVINCE) {
            dialogChooseProvince = new ListItemDialog(this);
            choosePronvinceDialogAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseProvince
                    .setMode(ListItemDialog.NO_SEARCH)
                    .setTitle("Chọn Tỉnh/Thành phố")
                    .setLeftButton("BỎ QUA", new OnCancelDialogClick(dialogChooseProvince))
                    .setAdapter(choosePronvinceDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_PROVINCE, choosePronvinceDialogAdapter));
        } else if (type == POPUP_CHOOSE_DISTRICT) {
            dialogChooseDistrict = new ListItemDialog(this);
            chooseDistrictDialogAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseDistrict
                    .setMode(ListItemDialog.SEARCH)
                    .setTitle("Chọn Quận")
                    .setLeftButton("BỎ QUA", new OnCancelDialogClick(dialogChooseDistrict))
                    .setAdapter(chooseDistrictDialogAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_DISTRICT, chooseDistrictDialogAdapter));
        } else if (type == POPUP_CHOOSE_RESTYPE) {
            dialogChooseResType = new ListItemDialogTypeRes(this);
            chooseResTypeAdapter = new DialogAdapter(this.getApplicationContext(), getDataDialog(type));
            dialogChooseResType
                    .setTitle("Loại hình")
                    .setLeftButton("Hủy", new OnCancelDialogClick(dialogChooseResType))
                    .setRightButton(getString(R.string.TEXT_ACTION_DONE), new OnDoneChooseResType(chooseResTypeAdapter))
                    .setAdapter(chooseResTypeAdapter)
                    .setOnItemClickListener(new OnItemInDialogClick(POPUP_CHOOSE_RESTYPE, chooseResTypeAdapter));
        }

    }

    private List<MenuBarItemBean> getDataDialog(int type) {
        List<MenuBarItemBean> list = new ArrayList<>();
        try {
            if (type == POPUP_CHOOSE_PROVINCE) {
                tinhThanhList.clear();
                AsynTinhThanh asynTinhThanh=new AsynTinhThanh();
                tinhThanhList=asynTinhThanh.execute().get();
                //List<ProvinceBean> provinceBeanList = provinceController.getListProvince();
                for (int i=0;i<tinhThanhList.size();i++) {
                    list.add(new MenuBarItemBean(tinhThanhList.get(i).getMaTinhThanh().trim(), tinhThanhList.get(i).getTenTinhThanh().trim(), "", false));
                }
                list.get(positionSelectedProvince).setSelected(true);
            } else if (type == POPUP_CHOOSE_DISTRICT) {
                //List<DistrictBean> distrctBeanList = districtController.getListDistrict(this.curProvinceID);
                quanHuyenList.clear();
                AsynQuanHuyen asynQuanHuyen=new AsynQuanHuyen();
                List<QuanHuyen> quanHuyens=asynQuanHuyen.execute().get();
                for (int i=0;i<quanHuyens.size();i++){
                    if(curProvinceID.trim().equals(quanHuyens.get(i).getMaTinhThanh().trim())){
                        quanHuyenList.add(quanHuyens.get(i));
                    }
                }
                for (QuanHuyen i : quanHuyenList) {
                    list.add(new MenuBarItemBean(i.getMaQuanHuyen(), i.getTenQuanHuyen(), "", false));
                }
            } else if (type == POPUP_CHOOSE_RESTYPE) {
                List<MenuBarItemBean> menuBarItemBeanList = new ArrayList<>();
                AsynDanhMuc asynDanhMuc=new AsynDanhMuc();
                List<DanhMuc> mucList=asynDanhMuc.execute().get();

                for (int i = 1; i < mucList.size(); i++) {
                    list.add(new MenuBarItemBean(mucList.get(i).getId(),mucList.get(i).getTen(),"",false));
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return list;
    }
    @Override
    public void onClickReviewImage(View v, int index) {

    }

    @Override
    public void onClickImage(View v, final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Cài đặt các thuộc tính
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa ảnh?");
        // Cài đặt button yes- logout
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                selectedFileAdapter.removeSelectedImage(index);
                selectedImages.remove(index);

                updateNumofPhoto();
            }
        });

        // Cài đặt button hủy Dismiss ẩn Dialog
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_button_add_place:
                finish();
                break;
            case R.id.text_view_done:
               // sendData();
                break;
            case R.id.text_view_choose_province:
                dialogChooseProvince.show();
                break;
            case R.id.text_view_choose_district:
                dialogChooseDistrict.show();
                break;
            case R.id.linear_layout_choose_type_res:
                dialogChooseResType.show();
                break;
            case R.id.linear_layout_map_location:
               /* if (PermissionUtil.isGPSPermission(this)) {
                    Intent intentLocation = new Intent(this, MapsActivity.class);
                    intentLocation.putExtra("lat", lat);
                    intentLocation.putExtra("long", lng);
                    startActivityForResult(intentLocation, 1);
                }*/
                break;
            case R.id.text_view_open_time:
                this.openTimePiker.show();
                break;
            case R.id.text_view_close_time:
                this.closeTimePiker.show();
                break;
            case R.id.frame_layout_add_image:
               /* if (PermissionUtil.isReadWritePermission_LowAPI(this)) {
                    Intent intent = new Intent(this, GalleryFolderActivity.class);
                    intent.putExtra("mode", GalleryFolderActivity.MULTI_SELECT);
                    intent.putParcelableArrayListExtra("images", this.selectedFileAdapter.imageSelected);
                    startActivityForResult(intent, 1);
                    break;
                }
                PermissionUtil.isReadWritePermission_HighAPI(this);*/
                break;
        }
    }
    class OnDoneChooseResType implements View.OnClickListener {

        DialogAdapter adapter;

        public OnDoneChooseResType(DialogAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            selectedResType.clear();
            for (int i = 0; i < adapter.data.size(); i++) {
                if (adapter.data.get(i).isSelected()) {
                    selectedResType.add(adapter.data.get(i));
                }
            }
            updateTextResType();
            dialogChooseResType.dismiss();
        }
    }

    class OnCancelDialogClick implements View.OnClickListener {
        Dialog dialog;

        public OnCancelDialogClick(Dialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View v) {
            this.dialog.dismiss();
        }
    }
    class OnItemInDialogClick implements AdapterView.OnItemClickListener {
        int type;
        DialogAdapter adapter;

        public OnItemInDialogClick(int type, DialogAdapter adapter) {
            this.type = type;
            this.adapter = adapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (type != POPUP_CHOOSE_RESTYPE) {
                if (adapter != null && adapter.data.size() > 0) {
                    for (int i = 0; i < adapter.data.size(); i++) {
                        adapter.data.get(i).setSelected(false);
                    }
                }
                ((MenuBarItemBean) adapter.getItem(position)).setSelected(true);
                MenuBarItemBean item = ((MenuBarItemBean) adapter.getItem(position));
                if (type == POPUP_CHOOSE_PROVINCE) {
                    positionSelectedProvince = position;
                    dialogChooseProvince.refreshData();
                    dialogChooseProvince.dismiss();
                    curProvinceID = item.getId();
                    text_view_choose_province.setText(item.getTittle());
                    chooseDistrictDialogAdapter.data = getDataDialog(POPUP_CHOOSE_DISTRICT);
                    text_view_choose_district.setText("Chọn Quận");
                    curDistrictID = "";
                    dialogChooseDistrict.refreshData();
                } else if (type == POPUP_CHOOSE_DISTRICT) {
                    positionSelectedDistrict = position;
                    dialogChooseDistrict.refreshData();
                    dialogChooseDistrict.dismiss();
                    curDistrictID = item.getId();
                    text_view_choose_district.setText(item.getTittle());
                }
            } else if (type == POPUP_CHOOSE_RESTYPE) {

                MenuBarItemBean item = ((MenuBarItemBean) adapter.getItem(position));
                if (item.isSelected()) {
                    ((MenuBarItemBean) adapter.getItem(position)).setSelected(false);
                } else {
                    ((MenuBarItemBean) adapter.getItem(position)).setSelected(true);
                }
                dialogChooseResType.refreshData();
            }
        }
    }
}
