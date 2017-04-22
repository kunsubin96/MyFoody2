package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunsubin.foody.Object.StaticData;
import com.example.kunsubin.foody.Object.TinhThanh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 4/6/2017.
 */

public class CustomAdapterTinhThanh extends BaseAdapter{
    Context context;
    List<TinhThanh> tinhThanhList;
    ChooseProvince chooseProvince;
    private static LayoutInflater inflater = null;
    int banDau;
    public CustomAdapterTinhThanh(ChooseProvince chooseProvince, List<TinhThanh> list, int vt) {
        context = chooseProvince;
        this.chooseProvince=chooseProvince;
        tinhThanhList = list;
        banDau=vt;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tinhThanhList.size();
    }

    @Override
    public TinhThanh getItem(int i) {
        return tinhThanhList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int i) {
        return i;
    }

    public static class Holder {
        View item;
        ImageView iconCheck;
        TextView textItem;
        TextView macDinh;

        public Holder(View view){
            item=view;
            this.iconCheck = (ImageView) view.findViewById(R.id.image_view_check_status);
            this.textItem = (TextView) view.findViewById(R.id.text_view_province_name);
            this.macDinh = (TextView) view.findViewById(R.id.text_view_set_default);
        }
    }

    @Override
    public View getView(int i, View view, final ViewGroup viewGroup) {

        Holder holder ;
        TinhThanh tinhThanh=this.tinhThanhList.get(i);
        if (view==null) {
            view = inflater.inflate(R.layout.choose_province_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        }else{
            holder=(Holder) view.getTag();
        }
        holder=(Holder) view.getTag();
        if(i==banDau){
            holder.textItem.setTextColor(context.getResources().getColor(R.color.blue));
        }
        if(i== StaticData.getSelected()){
            holder.iconCheck.setImageResource(R.drawable.icon_stick);
            holder.textItem.setText(tinhThanh.getTenTinhThanh());
            holder.macDinh.setVisibility(View.VISIBLE);
        }else {
            holder.iconCheck.setImageResource(0);
            holder.textItem.setText(tinhThanh.getTenTinhThanh());
            holder.macDinh.setVisibility(View.GONE);
        }


        return view;
    }
}
