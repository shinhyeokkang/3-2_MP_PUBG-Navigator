package com.example.map.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.map.weapon.Grid;
import com.example.map.Map.Map;
import com.example.map.Model.CategoryModel;
import com.example.map.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<CategoryModel> categoryList;



    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryList) {
        mContext = context;
        this.categoryList = categoryList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CategoryModel categoryModel = categoryList.get(position);

        holder.categoryName.setText(categoryModel.getCategoryName());
        holder.categoryDesc.setText(categoryModel.getCategoryDesc());

        Glide.with(mContext).load(categoryModel.getCategoryImage()).apply(RequestOptions.placeholderOf(R.drawable.geo)).into(new SimpleTarget<Drawable>() {

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.cardImageLayout.setBackground(resource);
                    Drawable cardViewBackground = holder.cardImageLayout.getBackground();
                }
            }



        });
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("categoryClick:", "clicked");
                TextView name = view.findViewById(R.id.category_name);
                Log.d("clickName", name.getText().toString());
                Intent intent = null;

                switch (name.getText().toString()){
                    case "WEAPON":
                        intent = new Intent(mContext, Grid.class);
                        intent.putExtra("gridtype", "basic");
                        break;

                    case "MAP":
                        intent = new Intent(mContext, Map.class);
                        break;
                }
                mContext.startActivity(intent);
            }
        });
        holder.categoryCardView.setCardElevation(5);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryName, categoryDesc;
        public CardView categoryCardView;
        public LinearLayout cardImageLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.category_name);
            categoryCardView = itemView.findViewById(R.id.category_cardview);
            cardImageLayout = itemView.findViewById(R.id.cardImageLayout);
            categoryDesc = itemView.findViewById(R.id.category_desc);
        }

    }
}
