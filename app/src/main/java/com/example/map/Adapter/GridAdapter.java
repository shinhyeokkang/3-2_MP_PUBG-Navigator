package com.example.map.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.weapon.Equipment;
import com.example.map.weapon.Melee;
import com.example.map.weapon.Consumable;
import com.example.map.weapon.Throwables;
import com.example.map.weapon.Ammunition;
import com.example.map.weapon.Grid;
import com.example.map.Model.GridLayoutModel;
import com.example.map.R;
import com.example.map.weapon.Weapon;

import java.util.List;



public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context mContext;
    private List<GridLayoutModel> gridLayoutModelList;



    public GridAdapter(Context mContext, List<GridLayoutModel> gridLayoutModelList){
        this.mContext = mContext;
        this.gridLayoutModelList=gridLayoutModelList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout,parent,false);
        return new ViewHolder(itemView);
    }  //xml불러오기


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final GridLayoutModel gridLayoutModel = gridLayoutModelList.get(position);

        holder.gridLayoutText.setText(gridLayoutModel.getGridName());
        Glide.with(mContext).load(gridLayoutModel.getImageLink()).into(holder.gridLayoutImage);

        holder.gridCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView gridName = view.findViewById(R.id.gridTextView);
                Log.d("gridName : ", gridName.getText().toString());

                Intent intent =null;

                switch (gridName.getText().toString()){
                    case "Gun":
                        intent = new Intent(mContext, Grid.class);
                        intent.putExtra("gridtype", "Gun");
                        mContext.startActivity(intent);
                        break; //in weapon
                    case "Attachment":
                        intent = new Intent(mContext, Grid.class);
                        intent.putExtra("gridtype", "Attachment");
                        mContext.startActivity(intent);
                        break; //in weapon
                    case "Assault Rifle":
                        intent = new Intent(mContext, Weapon.class);
                        intent.putExtra("weaponType","Assault Rifle");
                        mContext.startActivity(intent);
                        break; //in gun
                    case "Throwable":
                        intent = new Intent(mContext, Throwables.class);
                        mContext.startActivity(intent);
                        break; //in weapon
                    case "Ammunition":
                        intent = new Intent(mContext, Ammunition.class);
                        mContext.startActivity(intent);
                        break; //in weapon
                    case "Consumable":
                        intent = new Intent(mContext, Consumable.class);
                        mContext.startActivity(intent);
                        break;
                    case "Melee":
                        intent = new Intent(mContext, Melee.class);
                        mContext.startActivity(intent);
                        break;
                    case "Equipment":
                        intent= new Intent(mContext, Equipment.class);
                        mContext.startActivity(intent);
                        break;
                    default:
//                        intent = new Intent(mContext, WeaponsActivity.class);
//                        intent.putExtra("weaponType","no weapon");
//                        mContext.startActivity(intent);
                        break;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return gridLayoutModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutText;
        private ImageView gridLayoutImage;
        private CardView gridCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            gridLayoutText = itemView.findViewById(R.id.gridTextView);
            gridLayoutImage = itemView.findViewById(R.id.gridImageView);
            gridCardView = itemView.findViewById(R.id.gridCardLayout);
        }//xml의 아이템 불러오기
    }

}
