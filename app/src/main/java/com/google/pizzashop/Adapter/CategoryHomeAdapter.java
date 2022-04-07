package com.google.pizzashop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.pizzashop.Model.Category;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {
    private Context mContext;
    private List<Category> mListCategory;


    public CategoryHomeAdapter(Context mContext , List<Category> mListCategory) {
        this.mContext = mContext;
        this.mListCategory = mListCategory;
    }

    @NonNull
    @Override
    public CategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_item_category, parent, false);
        return new CategoryHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeViewHolder holder , int position) {
        Category uploadCategory = mListCategory.get(position);
        holder.itemTextViewCategory.setText(uploadCategory.getmName());
    }

    @Override
    public int getItemCount() {
        return mListCategory.size();
    }

    public class CategoryHomeViewHolder extends RecyclerView.ViewHolder{
        private TextView itemTextViewCategory;
        public CategoryHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextViewCategory = itemView.findViewById(R.id.item_category);
        }
    }
}
