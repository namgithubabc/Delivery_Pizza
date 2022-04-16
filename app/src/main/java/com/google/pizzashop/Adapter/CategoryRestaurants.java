package com.google.pizzashop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.pizzashop.Activities.CategoryRestaurant;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.Model.ResCategory;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRestaurants extends RecyclerView.Adapter<CategoryRestaurants.CategoryRestaurantHomeViewHolder> {
    private Context mContext;
    private List<ResCategory> mListResCategory;

    public CategoryRestaurants(Context mContext , List<ResCategory> resCategoryList) {
        this.mContext = mContext;
        this.mListResCategory = resCategoryList;
    }

    @NonNull
    @Override
    public CategoryRestaurantHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_restaurants, parent, false);
        return new CategoryRestaurantHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRestaurantHomeViewHolder holder , int position) {
        ResCategory uploadResCategory = mListResCategory.get(position);
        holder.itemTextViewCategory.setText(uploadResCategory.getmTitleCategory());
        holder.itemTextViewDistance.setText(uploadResCategory.getmDistance());
        holder.itemTextViewTime.setText(uploadResCategory.getmTime());
        holder.itemTextViewSale.setText(uploadResCategory.getmSale());
        Picasso.with(mContext).load(uploadResCategory.getmImageUrl()).into(holder.imgViewUrl);


        holder.layout_item_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryRestaurant.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListResCategory.size();
    }

    class CategoryRestaurantHomeViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout_item_restaurant;
        private ImageView imgViewUrl;
        private TextView itemTextViewCategory, itemTextViewDistance, itemTextViewTime, itemTextViewSale;

        public CategoryRestaurantHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item_restaurant = itemView.findViewById(R.id.res_layout);
            imgViewUrl = itemView.findViewById(R.id.img_restaurant);
            itemTextViewCategory = itemView.findViewById(R.id.item_tx_title);
            itemTextViewDistance = itemView.findViewById(R.id.item_tx_distance);
            itemTextViewTime = itemView.findViewById(R.id.item_tx_time);
            itemTextViewSale = itemView.findViewById(R.id.free_shop);
        }
    }
}
