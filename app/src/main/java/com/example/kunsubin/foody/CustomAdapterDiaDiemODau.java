package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.StaticData;

import java.util.List;

/**
 * Created by kunsubin on 4/8/2017.
 */

public class CustomAdapterDiaDiemODau extends BaseAdapter{
    Context context;
    List<QuanHuyen> listQuanHuyen;
    private static LayoutInflater inflater=null;
    public CustomAdapterDiaDiemODau(MainActivity mainActivity, List<QuanHuyen> listQuanHuyen) {
        // TODO Auto-generated constructor stub
        context=mainActivity;
       this.listQuanHuyen=listQuanHuyen;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listQuanHuyen.size();
    }

    @Override
    public QuanHuyen getItem(int position) {
        // TODO Auto-generated method stub
        return listQuanHuyen.get(position);
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
    public class Holder
    {
        TextView textItem;
        public Holder(View view){
            this.textItem=(TextView) view.findViewById(R.id.text_view_district_name);
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.custom_list_item_diadiem, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();

        holder.textItem.setText(listQuanHuyen.get(position).getTenQuanHuyen());
        holder.textItem.setTextColor(context.getResources().getColor(R.color.black));

        if (position == StaticData.getSelectedDiaDiemODau()) {
            holder.textItem.setTextColor(context.getResources().getColor(R.color.red));
        }
        return convertView;
    }
}
