package com.example.kunsubin.foody.Other;

import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kunsubin.foody.IOnClickImage;
import com.example.kunsubin.foody.Object.ImageGalleryBean;
import com.example.kunsubin.foody.R;


/**
 * Created by apple on 5/12/17.
 */

public class ChoosePhotoHolder extends BaseGalleryViewHolder implements View.OnClickListener{
    View v;
    public ImageView image_view;
    public CheckedTextView image_view_check;
    public int position;
    public IOnClickImage iOnClickImage;
    Context context;

    public ChoosePhotoHolder(Context context,View itemView,IOnClickImage iOnClickImage) {
        super(itemView);
        this.v = itemView;
        this.context=context;
        image_view = (ImageView) v.findViewById(R.id.image_view_file);
        image_view_check = (CheckedTextView) v.findViewById(R.id.check_text_view);
        image_view_check.bringToFront();
        this.iOnClickImage=iOnClickImage;
        this.v.setOnClickListener(this);
    }

    @Override
    public void renderData(ImageGalleryBean data, boolean isItemSelect, int position) {
        Glide.with(context).load("file://" + data.getPath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(this.image_view);

        if (isItemSelect) {
            this.image_view_check.setChecked(true);
        } else {
            this.image_view_check.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        iOnClickImage.onClickImage(v,getAdapterPosition());
    }
}
