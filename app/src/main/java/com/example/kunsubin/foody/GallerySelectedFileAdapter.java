package com.example.kunsubin.foody;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kunsubin.foody.Object.ImageGalleryBean;
import com.example.kunsubin.foody.Object.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunsubin on 5/17/2017.
 */

public class GallerySelectedFileAdapter extends  RecyclerView.Adapter<GallerySelectedFileAdapter.ViewHolder> {
    Context context;
    public ArrayList<ImageGalleryBean> imageSelected = new ArrayList<>();
    IOnClickImageNH iOnClickImage;
    Type type;

    public void setiOnClickImage(IOnClickImageNH iOnClickImage) {
        this.iOnClickImage = iOnClickImage;
    }

    public GallerySelectedFileAdapter(Context context, ArrayList<ImageGalleryBean> imageSelected, Type type) {
        this.context = context;
        this.imageSelected = imageSelected;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.gallery_image_item, parent, false),iOnClickImage,type);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageGalleryBean item = (ImageGalleryBean) this.imageSelected.get(position);

        Glide.with(context).load("file://" + item.getPath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image_view);

        if (isSelected(item)) {
            holder.image_view_check.setChecked(true);
        } else {
            holder.image_view_check.setChecked(false);
        }
        if(type== Type.IMAGESELECTED_INADDPLACE)
            holder.image_view_check.setCheckMarkDrawable(R.drawable.icon_remove2);
        return;
    }

    private boolean isSelected(ImageGalleryBean image) {
        for (ImageGalleryBean selectedImage : this.imageSelected) {
            if (selectedImage.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }
    public long getItemId(int position) {
        return position;
    }



    @Override
    public int getItemCount() {
        return imageSelected.size();
    }

    public void setData(List<ImageGalleryBean> images) {
        this.imageSelected.clear();
        this.imageSelected.addAll(images);
    }

    public void addAll(List<ImageGalleryBean> images) {
        int startIndex = this.imageSelected.size();
        this.imageSelected.addAll(startIndex, images);
        notifyItemRangeInserted(startIndex, images.size());
    }


    public void addSelected(ImageGalleryBean image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageGalleryBean item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                return;
            }
        }
        this.imageSelected.add(image);
        notifyItemChanged(this.imageSelected.indexOf(image));
    }

    public void removeSelectedImage(ImageGalleryBean image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageGalleryBean item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                this.imageSelected.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
        notifyItemChanged(this.imageSelected.indexOf(image));

    }
    public void removeSelectedImage(int position) {
        this.imageSelected.remove(position);
        notifyItemRemoved(position);


    }

    public void removeSelectedPosition(int position, int clickPosition) {
        this.imageSelected.remove(position);
        notifyItemChanged(clickPosition);
    }
    public void removeSelectedPosition(ImageGalleryBean item, int clickPosition) {
        this.imageSelected.remove(item);
        notifyItemChanged(clickPosition);
    }

    public void removeAllSelectedSingleClick() {
        this.imageSelected.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View v;
        ImageView image_view;
        CheckedTextView image_view_check;
        public int position;
        IOnClickImageNH iOnClickImage;
        Type type;
        public ViewHolder(View v,IOnClickImageNH iOnClickImage,Type type) {
            super(v);
            this.v = v;
            this.type = type;
            image_view = (ImageView) v.findViewById(R.id.image_view_file);
            image_view_check = (CheckedTextView) v.findViewById(R.id.check_text_view);
            image_view_check.bringToFront();
            this.iOnClickImage=iOnClickImage;
            this.image_view_check.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iOnClickImage.onClickImage(v,getAdapterPosition());
        }
    }
}
