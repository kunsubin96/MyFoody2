package com.example.kunsubin.foody;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by kunsubin on 3/26/2017.
 */
//show Dialog khi nhấn dấu cộng bên góc trái màn hình
public class BottomSheet3DialogFragment extends BottomSheetDialogFragment implements View.OnClickListener{
    View view;
    LinearLayout linear_layout_add_place_plus_menu;
    LinearLayout linear_layout_checkin_plus_menu;
    LinearLayout linear_layout_ecoupon_plus_menu;
    LinearLayout linear_layout_review_plus_menu;
    LinearLayout linear_layout_upload_image_plus_menu;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_plus_menu, container, false);

        linear_layout_add_place_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_add_place_plus_menu);
        linear_layout_checkin_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_checkin_plus_menu);
        linear_layout_ecoupon_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_ecoupon_plus_menu);
        linear_layout_review_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_review_plus_menu);
        linear_layout_upload_image_plus_menu = (LinearLayout) view.findViewById(R.id.linear_layout_upload_image_plus_menu);

        linear_layout_add_place_plus_menu.setOnClickListener(this);
        linear_layout_checkin_plus_menu.setOnClickListener(this);
        linear_layout_ecoupon_plus_menu.setOnClickListener(this);
        linear_layout_review_plus_menu.setOnClickListener(this);
        linear_layout_upload_image_plus_menu.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        String str = "You clicked ";
        switch (view.getId()) {
            case R.id.linear_layout_add_place_plus_menu:
               /* if(GlobalStaticData.isLogined()){
                    getActivity().startActivity(new Intent(this.getActivity(), AddNewPlaceActivity.class));
                }else{
                    startActivityForResult(new Intent(this.getActivity(), LoginChooserActivity.class), AppConfig.REQUEST_CODE_LOGIN_TO_ADD_NEW_PLACE);
                }*/
                str = str + "Them Dia Diem";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_checkin_plus_menu:
                str = str + "Checkin";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_ecoupon_plus_menu:
                str = str + "Ecoupon";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_review_plus_menu:
                str = str + "Viết bình luận";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_layout_upload_image_plus_menu:
                str = str + "Đăng ảnh";
                Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
