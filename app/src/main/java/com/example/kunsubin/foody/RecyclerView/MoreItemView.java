package com.example.kunsubin.foody.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/5/2017.
 */
//tạo một LinearLayout chứa RecyclerView
public class MoreItemView extends LinearLayout implements MoreItemAdapter.IMoreItemClick{
    public static final int ITEM_DEFAULT=0;



    private MoreItemAdapter adapter;
    private RecyclerView recyclerView;
    private List<MoreItem> moreItemList;
    private  myGridLayoutManger myGridLayoutManger;
    private int defaultPadding;
    private Context context;
    class myGridLayoutManger extends GridLayoutManager {
        public myGridLayoutManger(Context context, int spanCount) {
            super(context, spanCount);
        }
        @Override
        public boolean canScrollVertically() {
            return false;
        }
    }

    public MoreItemView(Context context) {
        super(context);
        init(context);

    }

    public MoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }


    public MoreItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        this.moreItemList = new ArrayList<>();
        this.recyclerView = new RecyclerView(context);
        //lấy dữ liệu
        moreItemList = this.getListMoreItem(MoreItemView.ITEM_DEFAULT);
        //set padding
        defaultPadding= this.dpToPx(3.0f);
        //tạo grid layout để add vào recyclerView
        myGridLayoutManger=new myGridLayoutManger(context, 2);
        //set layout cho recyclerView
        this.recyclerView.setLayoutManager(myGridLayoutManger);
        this.adapter = new MoreItemAdapter(context, moreItemList, this);
        //phân cách giữa các item
        this.recyclerView.setPadding(defaultPadding, this.dpToPx(10),defaultPadding,this.dpToPx(10));
        //set adapter cho recyclerView
        this.recyclerView.setAdapter(this.adapter);
        //add recyclerView vào linearlayout
        this.addView(this.recyclerView);
    }

    public void setMoreItemListByType(int type){
        moreItemList.clear();
        moreItemList=this.getListMoreItem(type);
        this.adapter.notifyDataSetChanged();
    }
    //lấy dữ liệu đưa lên để set các Item menu trên khung màn hình chính
    public List<MoreItem> getListMoreItem(int type) {
        List<MoreItem> list = new ArrayList<>();

        if(type == MoreItemView.ITEM_DEFAULT){
            list.add(new MoreItem("Gần tôi", MoreItem.MoreItemCode.NEARBY));
            list.add(new MoreItem("Coupon", MoreItem.MoreItemCode.COUPON));
            list.add(new MoreItem("Đặt chỗ ưu đãi", MoreItem.MoreItemCode.BOOK));
            list.add(new MoreItem("Đặt giao hàng", MoreItem.MoreItemCode.DELIVERY));
            list.add(new MoreItem("E-card", MoreItem.MoreItemCode.ECARD));

            list.add(new MoreItem("Game & Fun", MoreItem.MoreItemCode.GAME_FUN));
            list.add(new MoreItem("Bình luận", MoreItem.MoreItemCode.REVIEW));
            list.add(new MoreItem("Blogs", MoreItem.MoreItemCode.BLOGS));
            list.add(new MoreItem("Top thành viên", MoreItem.MoreItemCode.TOPMEMBER));
            list.add(new MoreItem("Video", MoreItem.MoreItemCode.VIDEO));
        }
        return list;
    }
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    //event click item trong recyclerView
    @Override
    public void moreItemClick(int pos) {
        switch (this.moreItemList.get(pos).getTittle()) {
            case "Gần tôi":
            case "Coupon":
            case "Đặt chỗ ưu đãi":
            case "Đặt giao hàng":
            case "E-card":
                Toast.makeText(context, "You clicked " + this.moreItemList.get(pos).getTittle(), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "You clicked " + this.moreItemList.get(pos).getTittle(), Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
