package com.example.kunsubin.foody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunsubin.foody.Object.MenuBarItemBean;

import java.util.List;

/**
 * Created by kunsubin on 5/17/2017.
 */

public class DialogAdapter extends BaseAdapter {
    Context context;
    List<MenuBarItemBean> data;

    public DialogAdapter(Context context, List<MenuBarItemBean> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        MenuBarItemBean item = this.data.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.menu_item_dialog, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.list_view_item_menu_tab_text.setText(item.getTittle());

        if (item.isSelected()) {
            holder.list_view_item_menu_tab_is_selected.setVisibility(View.VISIBLE);
        } else {
            holder.list_view_item_menu_tab_is_selected.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return getCount() == 0 ? 1 : getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder {
        View v;
        TextView list_view_item_menu_tab_text;
        ImageView list_view_item_menu_tab_is_selected;

        public ViewHolder(View v) {
            this.v = v;
            this.list_view_item_menu_tab_text = (TextView) v.findViewById(R.id.list_view_item_menu_tab_text);
            this.list_view_item_menu_tab_is_selected = (ImageView) v.findViewById(R.id.list_view_item_menu_tab_is_selected);
        }
    }
}
