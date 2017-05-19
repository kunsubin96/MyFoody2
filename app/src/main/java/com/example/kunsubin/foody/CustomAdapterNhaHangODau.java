package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kunsubin.foody.Object.BinhLuan;
import com.example.kunsubin.foody.Object.NhaHang;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kunsubin on 4/9/2017.
 */

public class CustomAdapterNhaHangODau extends BaseAdapter{
    Context context;
    List<NhaHang> listNhaHang;
    private static LayoutInflater inflater=null;
    IChooseItemNhaHang iChoose;
    public void setChooseNhaHang(IChooseItemNhaHang iChoose) {
        this.iChoose = iChoose;
    }
    public CustomAdapterNhaHangODau(MainActivity mainActivity, List<NhaHang> listNhaHang) {
        context=mainActivity;
        this.listNhaHang=listNhaHang;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listNhaHang.size();
    }

    @Override
    public NhaHang getItem(int position) {
        return listNhaHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int i) {
        return i;
    }
    public class Holder
    {
        View item;
        //tạo các view
        public RelativeLayout layout_parent_header_restaurant;
        public TextView text_view_rate_arg_restaurant;
        public LinearLayout linear_layout_header_restaurant;
        public TextView text_view_name_restaurant;
        public TextView text_view_address_restaurant;

        public LinearLayout linear_layout_main_img_restaurant;
        public ImageView image_view_main_img_restaurant;

        public LinearLayout linear_layout_sub_img_restaurant;
        public ImageView image_view_sub_img_res_1;
        public ImageView image_view_sub_img_res_2;
        public ImageView image_view_sub_img_res_3;

        public LinearLayout linear_layout_parent_comment_res;

        public LinearLayout linear_layout_sub_comment_res_1;
        public CircleImageView image_view_avatar_comment_1;

        public TextView text_view_name_user_1;
        public TextView text_view_user_rate_1;
        public TextView text_view_comment_1;

        public LinearLayout linear_layout_sub_comment_res_2;
        public CircleImageView image_view_avatar_comment_2;
        public TextView text_view_name_user_2;
        public TextView text_view_user_rate_2;
        public TextView text_view_comment_2;

        public LinearLayout linear_layout_num_of_review;
        public LinearLayout linear_layout_num_of_photo;

        public TextView text_view_num_of_review;
        public TextView text_view_num_of_photo;

        public LinearLayout itemnhahangodau;
        public Holder(View view){
            this.item=view;
            init(view);
        }
        //ánh xạ các view
        public void init(View view){
            layout_parent_header_restaurant = (RelativeLayout) item.findViewById(R.id.layout_parent_header_restaurant);
            text_view_rate_arg_restaurant = (TextView) item.findViewById(R.id.text_view_rate_arg_restaurant);
            linear_layout_header_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_header_restaurant);
            text_view_name_restaurant = (TextView) item.findViewById(R.id.text_view_name_restaurant);
            text_view_address_restaurant = (TextView) item.findViewById(R.id.text_view_address_restaurant);


            linear_layout_main_img_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_main_img_restaurant);
            image_view_main_img_restaurant = (ImageView) item.findViewById(R.id.image_view_main_img_restaurant);

            linear_layout_sub_img_restaurant = (LinearLayout) item.findViewById(R.id.linear_layout_sub_img_restaurant);
            image_view_sub_img_res_1 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_1);
            image_view_sub_img_res_2 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_2);
            image_view_sub_img_res_3 = (ImageView) item.findViewById(R.id.image_view_sub_img_res_3);

            linear_layout_parent_comment_res = (LinearLayout) item.findViewById(R.id.linear_layout_parent_comment_res);

            linear_layout_sub_comment_res_1 = (LinearLayout) item.findViewById(R.id.linear_layout_sub_comment_res_1);
            image_view_avatar_comment_1 = (CircleImageView) item.findViewById(R.id.image_view_avatar_comment_1);
            text_view_name_user_1 = (TextView) item.findViewById(R.id.text_view_name_user_1);
            text_view_user_rate_1 = (TextView) item.findViewById(R.id.text_view_user_rate_1);
            text_view_comment_1 = (TextView) item.findViewById(R.id.text_view_comment_1);

            linear_layout_sub_comment_res_2 = (LinearLayout) item.findViewById(R.id.linear_layout_sub_comment_res_2);
            image_view_avatar_comment_2 = (CircleImageView) item.findViewById(R.id.image_view_avatar_comment_2);
            text_view_name_user_2 = (TextView) item.findViewById(R.id.text_view_name_user_2);
            text_view_user_rate_2 = (TextView) item.findViewById(R.id.text_view_user_rate_2);
            text_view_comment_2 = (TextView) item.findViewById(R.id.text_view_comment_2);

            linear_layout_num_of_review=(LinearLayout) item.findViewById(R.id.linear_layout_num_of_review);
            linear_layout_num_of_photo=(LinearLayout) item.findViewById(R.id.linear_layout_num_of_photo);
            text_view_num_of_review = (TextView) item.findViewById(R.id.text_view_num_of_review);
            text_view_num_of_photo = (TextView) item.findViewById(R.id.text_view_num_of_photo);

            itemnhahangodau=(LinearLayout)item.findViewById(R.id.itemnhahangodau);
        }
        //show dữ liệu lên theo từng item trong listNhaHang lên khung màn hình chính
        public void showHolder(NhaHang nhaHang){
            showHeader(nhaHang);
            showMoreImage(nhaHang);
            showBinhLuan(nhaHang);
            updateStatusNhaHang(nhaHang);

            onClickItem(nhaHang);
        }
        //hiểu thị tiêu đề nhà hàng, địa chỉ..
        private void showHeader(NhaHang nhaHang) {
            text_view_rate_arg_restaurant.setText(nhaHang.getDanhGia() + "");
            text_view_name_restaurant.setText(nhaHang.getName());
            text_view_address_restaurant.setText(nhaHang.getDiaChi());

            if (nhaHang.getHinh()!= null) {
                Glide.with(context).load(nhaHang.getHinh()).into(image_view_main_img_restaurant);
            }

        }
        //hình ảnh khác của nhà hàng
        public void showMoreImage(NhaHang nhaHang) {
            if (nhaHang.getListHinh() == null || nhaHang.getListHinh().size() <= 0) {
                linear_layout_sub_img_restaurant.setVisibility(View.GONE);
                return;
            }
            linear_layout_sub_img_restaurant.setVisibility(View.VISIBLE);
            switch (nhaHang.getListHinh().size()) {
                case 1:
                    showImageSmall(1, nhaHang);
                    break;
                case 2:
                    showImageSmall(2, nhaHang);
                    break;
                case 3:
                    showImageSmall(3, nhaHang);
                    break;
                default:
                    showImageSmall(3, nhaHang);
                    break;
            }
        }
        //show các mở rộng  của nhà hàng
        public void showImageSmall(int index, NhaHang nhaHang) {
            //có 1 hình
            if (index == 1) {
                image_view_sub_img_res_2.setVisibility(View.GONE);
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);

                return;
            }
            // //có 2 hình
            if (index == 2) {
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(nhaHang.getListHinh().get(1)).into(image_view_sub_img_res_2);

                return;
            }
            //có 3 hình
            if (index == 3) {
                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);
                image_view_sub_img_res_3.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(nhaHang.getListHinh().get(1)).into(image_view_sub_img_res_2);
                Glide.with(context).load(nhaHang.getListHinh().get(2)).into(image_view_sub_img_res_3);

                return;

            }
        }
        //hiển thị bình luận của nhà cho hiển thị tối đa 2 bình luận
        public void showBinhLuan(NhaHang nhaHang) {
            if (nhaHang.getListBinhLuan() == null||nhaHang.getListBinhLuan().size() == 0) {
                linear_layout_parent_comment_res.setVisibility(View.GONE);
            } else {
                linear_layout_parent_comment_res.setVisibility(View.VISIBLE);

                if (nhaHang.getListBinhLuan().size() == 1) {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.GONE);

                    BinhLuan binhLuan1 = nhaHang.getListBinhLuan().get(0);

                    showBinhLuan1(binhLuan1);


                } else {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.VISIBLE);

                    BinhLuan binhLuan1 = nhaHang.getListBinhLuan().get(0);
                    BinhLuan binhLuan2 = nhaHang.getListBinhLuan().get(1);

                    showBinhLuan1(binhLuan1);
                    showBinhLuan2(binhLuan2);

                }
            }

        }
        //bình luận 1
        public void showBinhLuan1(BinhLuan binhLuan) {
            if(binhLuan.getObjectInfoUser().getHinh()!=null)
                Glide.with(context).load(binhLuan.getObjectInfoUser().getHinh()).into(image_view_avatar_comment_1);

            text_view_name_user_1.setText(binhLuan.getObjectInfoUser().getHoTen());
            text_view_user_rate_1.setText(binhLuan.getDanhGia() + "");
            text_view_comment_1.setText(binhLuan.getNoiDung());
        }
        //bình luận 2
        public void showBinhLuan2(BinhLuan binhLuan) {
            if(binhLuan.getObjectInfoUser().getHinh()!=null)
                Glide.with(context).load(binhLuan.getObjectInfoUser().getHinh()).into(image_view_avatar_comment_2);

            text_view_name_user_2.setText(binhLuan.getObjectInfoUser().getHoTen());
            text_view_user_rate_2.setText(binhLuan.getDanhGia() + "");
            text_view_comment_2.setText(binhLuan.getNoiDung());
        }
        //set trạng thái của nhà hàng
        public void updateStatusNhaHang(NhaHang nhaHang) {
            String numOfReview = nhaHang.getListBinhLuan().size() + "";
            String numOfPhoto = nhaHang.getListHinh().size()+"";

            if(numOfPhoto.equals("0") && numOfReview.equals("0")){
                linear_layout_num_of_photo.setVisibility(View.GONE);
                text_view_num_of_review.setText("Bạn hãy là người đầu tiên đánh giá");
            }else{
                linear_layout_num_of_photo.setVisibility(View.VISIBLE);
                text_view_num_of_review.setText(numOfReview);
                text_view_num_of_photo.setText(numOfPhoto);
            }


        }
        public void onClickItem(NhaHang nhaHang){
            itemnhahangodau.setOnClickListener(new ItemNhaHang(nhaHang));
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        NhaHang nhaHang=listNhaHang.get(position);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.nhahang_odau, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();
        holder.showHolder(nhaHang);
        return convertView;
    }
    public class ItemNhaHang implements  View.OnClickListener{
        NhaHang nhaHang;
        public ItemNhaHang(NhaHang nhaHang){
            this.nhaHang=nhaHang;
        }

        @Override
        public void onClick(View v) {
            CustomAdapterNhaHangODau.this.iChoose.ChooseItemNhaHang(this.nhaHang);
        }
    }


}
