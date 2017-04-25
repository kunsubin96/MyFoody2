package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kunsubin.foody.Object.Duong;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.StaticData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kunsubin on 4/25/2017.
 */

public class ExpandableListAdapterODau extends BaseExpandableListAdapter {
    private Context _context;
    private List<QuanHuyen> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<QuanHuyen, List<Duong>> _listDataChild;
    LayoutInflater infalInflater;
    List<String> countDuong;
    IChooseStreet iChooseStreet;
    public void setChooseStreet(IChooseStreet iChooseStreet) {
        this.iChooseStreet = iChooseStreet;
    }

    public ExpandableListAdapterODau(Context context, List<QuanHuyen> listDataHeader,
                                     HashMap<QuanHuyen, List<Duong>> listChildData, List<String> countDuong) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.countDuong=countDuong;
        infalInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Duong getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    public class ChildHolder
    {
        TextView textItem;
        public ChildHolder(View view){
            this.textItem=(TextView) view.findViewById(R.id.lblListItemODau);
        }
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Duong childText = getChild(groupPosition, childPosition);
        ChildHolder childHolder;
        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.item_child, null);
            childHolder=new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        childHolder=(ChildHolder) convertView.getTag();
        childHolder.textItem.setText(childText.getTenDuong());
        childHolder.textItem.setTextColor(_context.getResources().getColor(R.color.black));
        if(StaticData.getGroupODau()==groupPosition&&StaticData.getChildODau()==childPosition){
            childHolder.textItem.setTextColor(_context.getResources().getColor(R.color.red));
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public QuanHuyen getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    public class Holder
    {
        TextView textItem;
        TextView text_view_num_of_street;
        LinearLayout linear_layout_child_expand;
        public Holder(View view){
            this.textItem=(TextView) view.findViewById(R.id.text_view_district_name);
            this.text_view_num_of_street=(TextView) view.findViewById(R.id.text_view_num_of_street);
            this.linear_layout_child_expand=(LinearLayout)view.findViewById(R.id.linear_layout_child_expand);
        }
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        QuanHuyen headerTitle = getGroup(groupPosition);

        Holder holder;
        if(convertView==null){
            convertView=infalInflater.inflate(R.layout.custom_list_item_diadiem, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }
        holder=(Holder) convertView.getTag();

        holder.textItem.setText(headerTitle.getTenQuanHuyen());
        holder.textItem.setTextColor(_context.getResources().getColor(R.color.black));
        holder.text_view_num_of_street.setText(countDuong.get(groupPosition).toString()+" đường");
        holder.linear_layout_child_expand.setOnClickListener(new ExpandStreet(groupPosition));
        if (groupPosition == StaticData.getSelectedDiaDiemODau()) {
            holder.textItem.setTextColor(_context.getResources().getColor(R.color.red));
        }

        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ExpandStreet implements  View.OnClickListener{
        int groupPosion;
        public ExpandStreet(int groupPosition){
            this.groupPosion=groupPosition;
        }

        @Override
        public void onClick(View v) {
            ExpandableListAdapterODau.this.iChooseStreet.onExpand(this.groupPosion);
        }
    }
}
