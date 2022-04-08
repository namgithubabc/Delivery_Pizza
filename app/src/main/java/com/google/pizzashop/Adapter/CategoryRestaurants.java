package com.google.pizzashop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.pizzashop.Model.Category;
import com.google.pizzashop.Model.ResCategory;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRestaurants extends RecyclerView.Adapter<CategoryRestaurants.CategoryRestaurantHomeViewHolder> {
    private Context mContext;
    private List<Category> mListResCategory;

    public CategoryRestaurants(Context mContext , List<Category> mListCategory) {
        this.mContext = mContext;
        this.mListResCategory = mListCategory;
    }

    @NonNull
    @Override
    public CategoryRestaurantHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_item_category, parent, false);
        return new CategoryRestaurantHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRestaurantHomeViewHolder holder , int position) {
        ResCategory uploadResCategory = mListResCategory.get(position);
        holder.itemTextViewCategory.setText(uploadResCategory.getmTitleCategory());
        holder.itemTextViewDistance.setText(uploadResCategory.getmDistance());
        holder.itemTextViewTime.setText(uploadResCategory.getmTime());
        Picasso.with(mContext).load(R.drawable.sp1).into(holder.imgViewUrl);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CategoryRestaurantHomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgViewUrl;
        private TextView itemTextViewCategory,itemTextViewDistance, itemTextViewTime;
        public CategoryRestaurantHomeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
