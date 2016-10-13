package com.mfeldsztejn.rxjavatest.detail;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mfeldsztejn.rxjavatest.R;
import com.mfeldsztejn.rxjavatest.dto.Image;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mfeldsztejn on 9/14/16.
 */

public class ImageAdapter extends PagerAdapter {

    private List<Image> values;

    public ImageAdapter(List<Image> values) {
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.imagepager_layout, container, false);
        container.addView(view);
        final ImageView img = (ImageView) view.findViewById(R.id.img);

        Image image = values.get(position);
        Picasso.with(container.getContext())
               .load(image.getDisplaySizes().get(0).getUri())
               .fit()
               .into(img);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
