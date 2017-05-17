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

public class ListItemDialog extends Dialog {
    public static int SEARCH = 1;
    public static int NO_SEARCH = 0;

    ImageView image_view_back;
    TextView text_view_title;
    EditText edit_text_quick_search;
    ImageView image_view_icon;
    ListView list_view;
    Button left_button;
    Button right_button;
    LinearLayout linear_layout_button;
    BaseAdapter adapter;
    Context context;


    View view;

    int mode;

    public ListItemDialog(@NonNull Context context) {
        super(context, 0);
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.list_dialog_layout, null);
        setContentView(this.view);
        this.mode = NO_SEARCH;
        image_view_back = (ImageView) findViewById(R.id.image_view_back);
        text_view_title = (TextView) findViewById(R.id.text_view_title);
        edit_text_quick_search = (EditText) findViewById(R.id.edit_text_quick_search);
        image_view_icon = (ImageView) findViewById(R.id.image_view_icon);
        list_view = (ListView) findViewById(R.id.list_view);

        left_button = (Button) findViewById(R.id.left_button);
        right_button = (Button) findViewById(R.id.right_button);

        linear_layout_button = (LinearLayout) findViewById(R.id.linear_layout_button);

        image_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
        image_view_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchCLick();
            }
        });

    }

    public ListItemDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, false, null);
    }

    protected ListItemDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private void onSearchCLick() {

        this.image_view_back.setVisibility(View.VISIBLE);
        this.edit_text_quick_search.setVisibility(View.VISIBLE);
        this.text_view_title.setVisibility(View.GONE);
        this.image_view_icon.setVisibility(View.GONE);
    }

    private void onBackClick() {
        this.image_view_back.setVisibility(View.GONE);
        this.edit_text_quick_search.setVisibility(View.GONE);

        if (mode == SEARCH) {
            this.image_view_icon.setVisibility(View.VISIBLE);
        } else {
            this.image_view_icon.setVisibility(View.GONE);
        }

        this.text_view_title.setVisibility(View.VISIBLE);
    }

    public ListItemDialog setLeftButton(String title, View.OnClickListener onClickListener) {
        this.left_button.setVisibility(View.VISIBLE);
        this.left_button.setText(title);

        this.linear_layout_button.setVisibility(View.VISIBLE);
        this.left_button.setOnClickListener(onClickListener);
        return this;

    }

    public ListItemDialog setRightButton(String title, View.OnClickListener onClickListener) {
        this.right_button.setVisibility(View.VISIBLE);
        this.right_button.setText(title);

        this.linear_layout_button.setVisibility(View.VISIBLE);
        this.right_button.setOnClickListener(onClickListener);
        return this;
    }

    public ListItemDialog setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        this.list_view.setAdapter(adapter);
        return this;
    }

    public ListItemDialog setTitle(String title) {
        this.text_view_title.setVisibility(View.VISIBLE);
        this.text_view_title.setText(title);
        return this;
    }
    public ListItemDialog setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.list_view.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public ListItemDialog setMode(int mode) {
        if (this.mode != mode) {
            this.mode = mode;
            if (mode == SEARCH) {
                this.image_view_icon.setVisibility(View.VISIBLE);
            } else {
                this.image_view_icon.setVisibility(View.GONE);
            }
        }

        return this;
    }



    public void refreshData() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void show() {

        super.show();
    }
}
