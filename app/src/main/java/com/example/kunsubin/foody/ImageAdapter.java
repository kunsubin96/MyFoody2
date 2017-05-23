package com.example.kunsubin.foody;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by kunsubin on 4/4/2017.
 */
//adapter set chuyển ảnh trong màn hình chính
public class ImageAdapter extends android.support.v4.view.PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Integer> mResources;
    //nhận vào 1 cái list hình
    public ImageAdapter(Context context, List<Integer> mResources) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_image_viewpager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.item_imageViewpager);

        imageView.setImageResource(mResources.get(position));

        container.addView(itemView);

        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
