package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunsubin.foody.Object.StaticData;

/**
 * Created by kunsubin on 4/3/2017.
 */

public class CustomAdapterDanhMucODau extends BaseAdapter{
    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapterDanhMucODau(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
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
    //ánh xạ các view
    public class Holder
    {
        ImageView iconItem;
        TextView textItem;
        ImageView checked;
        public Holder(View view){
            this.iconItem=(ImageView) view.findViewById(R.id.icon_danhmuc);
            this.textItem=(TextView) view.findViewById(R.id.tvDanhMuc);
            this.checked=(ImageView) view.findViewById(R.id.checkDanhMuc);
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.custom_list_item_danh_muc, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();
        //set data lên list view
        holder.iconItem.setImageResource(imageId[position]);
        holder.textItem.setText(result[position]);
        holder.checked.setImageResource(0);
        holder.textItem.setTextColor(context.getResources().getColor(R.color.black));
        if(position==0)
            holder.iconItem.getLayoutParams().width = 1;
        //thiết lập danh mục được chọn trong list view
        if (position == StaticData.getSelectedDanhMucODau()) {
            holder.textItem.setTextColor(context.getResources().getColor(R.color.red));
            holder.checked.setImageResource(R.drawable.icon_stick);
        }
        return convertView;
    }
}
