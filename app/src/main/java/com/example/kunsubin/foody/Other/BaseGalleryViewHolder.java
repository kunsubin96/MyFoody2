package com.example.kunsubin.foody.Other;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kunsubin.foody.Object.ImageGalleryBean;

/**
 * Created by kunsubin on 5/18/2017.
 */

public abstract class BaseGalleryViewHolder extends RecyclerView.ViewHolder{
    public BaseGalleryViewHolder(View itemView) {
        super(itemView);
    }
    public abstract  void renderData(ImageGalleryBean data, boolean isItemSelect, int position);
}
