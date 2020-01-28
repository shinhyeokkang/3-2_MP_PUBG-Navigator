package com.example.map.Adapter;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.map.R;

class SliderCard extends RecyclerView.ViewHolder {
    private static int viewWidth = 0;
    private static int viewHeight = 0;

    private final ImageView imageView;


    public SliderCard(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);

    }

    void setContent (final String resId) {
        if(viewWidth==0){
            itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    viewWidth=itemView.getWidth();
                    viewHeight=itemView.getHeight();
                    loadBitmap(resId);

                }
            });
        } else {
            loadBitmap(resId);
        }
    }

    private void loadBitmap(String resId) {
        Glide.with(itemView.getContext()).load(resId).apply(new RequestOptions().override(400,400)).into(imageView);
    }







}
