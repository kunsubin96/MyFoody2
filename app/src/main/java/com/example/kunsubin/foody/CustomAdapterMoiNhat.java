package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kunsubin on 3/22/2017.
 */

public class CustomAdapterMoiNhat extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    boolean mocAnGiOrODau;
    private static LayoutInflater inflater=null;
    public CustomAdapterMoiNhat(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages,boolean f) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        mocAnGiOrODau=f;
        inflater = ( LayoutInflater )context.
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
        ImageView newODau;
        public Holder(View view){
            this.iconItem=(ImageView) view.findViewById(R.id.imgAvatatr);
            this.textItem=(TextView) view.findViewById(R.id.tvNoiDung);
            this.checked=(ImageView) view.findViewById(R.id.check);
            this.newODau=(ImageView) view.findViewById(R.id.moinhatODau);
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.custom_list_item, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();
        //set data lên item list view
        holder.iconItem.setImageResource(imageId[position]);
        holder.textItem.setText(result[position]);
        holder.checked.setImageResource(0);
        //tô màu mặc định ban đầu tại vị trí 0
        if(position==0){
            holder.iconItem.setColorFilter(context.getResources().getColor(R.color.blue));
            holder.textItem.setTextColor(context.getResources().getColor(R.color.red));
            holder.checked.setImageResource(R.drawable.icon_stick);
        }
        //set icon cuối listview bên tab mới nhất
        if((position==result.length-1)&&mocAnGiOrODau)
            holder.newODau.setImageResource(R.drawable.icon_new);

        return convertView;
    }
}
