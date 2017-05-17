package com.example.kunsubin.foody.Other;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kunsubin.foody.R;

/**
 * Created by kunsubin on 5/17/2017.
 */

public class ListItemDialogTypeRes extends Dialog {
    TextView text_view_title;
    TextView edit_text_search;
    ImageView image_view_delete_search;
    ListView list_view;
    LinearLayout linear_layout_button;
    Button left_button;
    Button right_button;
    View view;
    Context context;
    BaseAdapter adapter;

    public ListItemDialogTypeRes(@NonNull Context context) {
        super(context, 0);
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.list_dialog_layout_1, null);
        setContentView(this.view);
        text_view_title = (TextView) findViewById(R.id.text_view_title);
        edit_text_search=(EditText)findViewById(R.id.edit_text_search);
        image_view_delete_search=(ImageView)findViewById(R.id.image_view_delete_search);
        list_view=(ListView)findViewById(R.id.list_view);
        linear_layout_button=(LinearLayout)findViewById(R.id.linear_layout_button);
        left_button=(Button)findViewById(R.id.left_button);
        right_button=(Button)findViewById(R.id.right_button);
    }

    public ListItemDialogTypeRes(@NonNull Context context, @StyleRes int themeResId) {
        super(context, false, null);
    }

    protected ListItemDialogTypeRes(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ListItemDialogTypeRes setLeftButton(String title, View.OnClickListener onClickListener) {
        this.left_button.setVisibility(View.VISIBLE);
        this.left_button.setText(title);

        this.linear_layout_button.setVisibility(View.VISIBLE);
        this.left_button.setOnClickListener(onClickListener);
        return this;

    }

    public ListItemDialogTypeRes setRightButton(String title, View.OnClickListener onClickListener) {
        this.right_button.setVisibility(View.VISIBLE);
        this.right_button.setText(title);

        this.linear_layout_button.setVisibility(View.VISIBLE);
        this.right_button.setOnClickListener(onClickListener);
        return this;
    }

    public ListItemDialogTypeRes setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        this.list_view.setAdapter(adapter);
        return this;
    }

    public ListItemDialogTypeRes setTitle(String title) {
        this.text_view_title.setVisibility(View.VISIBLE);
        this.text_view_title.setText(title);
        return this;
    }
    public ListItemDialogTypeRes setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.list_view.setOnItemClickListener(onItemClickListener);
        return this;
    }
    public void refreshData() {
        this.adapter.notifyDataSetChanged();
    }
}
