package com.example.jay.worknasidemo5.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.jay.worknasidemo5.R;

/**
 * Created by Jay on 11/21/2017.
 */

public class ImageSlideShowAdapter extends PagerAdapter {

    public static final int[] images = {R.drawable.before_dusk, R.drawable.daressalaam_tanzania, R.drawable.city_center_askari, R.drawable.dsm,
            R.drawable.kigamboni};
    public Context context;
    private LayoutInflater layoutInflater;

    public ImageSlideShowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (images != null) {
            return images.length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slide_show, container, false);

        ImageView image = view.findViewById(R.id.sliding_image);
        image.setImageResource(images[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

}
