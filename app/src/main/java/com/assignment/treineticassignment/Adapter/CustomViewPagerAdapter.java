package com.assignment.treineticassignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.assignment.treineticassignment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomViewPagerAdapter extends PagerAdapter  {

    private Context mContext;
    private List<String> mImageUrls;

    public CustomViewPagerAdapter(Context context, List<String> imageUrls) {
        mContext = context;
        mImageUrls = imageUrls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Load image from URL using a library such as Picasso or Glide
        Picasso.get()
                .load(mImageUrls.get(position))
                .placeholder(R.mipmap.vr_box)
                .error(R.mipmap.vr_box)
                .fit()
                .centerCrop()
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
