package com.example.kunsubin.foody;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kunsubin.foody.Object.StaticData;

/**
 * Created by kunsubin on 3/26/2017.
 */
//show Dialog khi nhấn dấu cộng bên góc trái màn hình
public class BottomSheet3DialogFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    View view;
    LinearLayout linear_layout_add_location_menu;
    LinearLayout linear_layout_checkin_plus_menu;
    LinearLayout linear_layout_ecoupon_plus_menu;
    LinearLayout linear_layout_review_plus_menu;
    LinearLayout linear_layout_upload_image_plus_menu;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_plus_menu, container, false);
        //ánh xạ các view
        linear_layout_add_location_menu = (LinearLayout) view.findViewById(R.id.linear_layout_add_location_menu);
        linear_layout_checkin_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_checkin_plus_menu);
        linear_layout_ecoupon_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_ecoupon_plus_menu);
        linear_layout_review_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_review_plus_menu);
        linear_layout_upload_image_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_upload_image_plus_menu);
        //sét sự kiện cho các view
        linear_layout_add_location_menu.setOnClickListener(this);
        linear_layout_checkin_plus_menu.setOnClickListener(this);
        linear_layout_ecoupon_plus_menu.setOnClickListener(this);
        linear_layout_review_plus_menu.setOnClickListener(this);
        linear_layout_upload_image_plus_menu.setOnClickListener(this);

        return view;
    }
    //nhận kết quả trả về từ activity đăng nhập
    @Override
    public void onActivityResult (int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 32) {
            if (resultCode == Activity.RESULT_OK) {
                //đăng nhập thành công thì tiến hành click lại linear_layout_add_location_menu
                linear_layout_add_location_menu.performClick();
            }
        }
    }
    //xử lý các sự kiện click cho các view
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_add_location_menu:
                //nếu đăng nhập rùi
                if(StaticData.getObjectInfoUser()!=null){
                    getActivity().startActivity(new Intent(getActivity(),AddNhaHang.class));
                }else{
                    //nếu chưa đăng nhập
                    getActivity().startActivityForResult(new Intent(getActivity(),LoginUser.class),32);
                }
                Toast.makeText(getActivity().getApplicationContext(), "Thêm địa điểm", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_checkin_plus_menu:
                Toast.makeText(getActivity().getApplicationContext(), "Check in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_ecoupon_plus_menu:
                Toast.makeText(getActivity().getApplicationContext(), "Ecoupon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_review_plus_menu:
                Toast.makeText(getActivity().getApplicationContext(), "Viết bình luận", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_upload_image_plus_menu:
                Toast.makeText(getActivity().getApplicationContext(), "Đăng ảnh", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
