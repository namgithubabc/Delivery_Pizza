package com.google.pizzashop.Adapter;


import android.annotation.SuppressLint;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.pizzashop.AminActivity.Add.AddCategory;
import com.google.pizzashop.Model.OptionCategory;
import com.google.pizzashop.R;
import com.google.pizzashop.my_Interface.IClickListener;

import java.util.List;

public class OptionListSetCategory extends RecyclerView.Adapter<OptionListSetCategory.SetOptionCateogryViewHolder> {
    private List<OptionCategory> mListCategorySetUp;
    private IClickListener iClickListener;

    public void setData(List<OptionCategory> mListCategorySetUp) {
        this.mListCategorySetUp = mListCategorySetUp;
        notifyDataSetChanged();
    }

    public OptionListSetCategory(List<OptionCategory> mListCategorySetUp,IClickListener iClickListener) {
        this.mListCategorySetUp = mListCategorySetUp;
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public SetOptionCateogryViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_set_option_category , parent , false);
        return new SetOptionCateogryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetOptionCateogryViewHolder holder , @SuppressLint("RecyclerView") int position) {
        OptionCategory optionCategory = mListCategorySetUp.get(position);
        if (optionCategory == null) {
            return;
        }

        holder.titleSetUpCategory.setText(optionCategory.getmTitle());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0 ){
                    iClickListener.onClickItemCategoryAdd(optionCategory,position);
                }
                if(position==1){
                    iClickListener.onClickItemCategoryUpdate(optionCategory,position);
                }
                if(position==2){
                    iClickListener.onClickItemCategoryDelete(optionCategory,position);
                }
                if(position==3){
                    iClickListener.onClickItemCategoryWatch(optionCategory,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListCategorySetUp.size();
    }

    class SetOptionCateogryViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout itemLayout;
        private TextView titleSetUpCategory;

        public SetOptionCateogryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.layout_items);
            titleSetUpCategory = itemView.findViewById(R.id.Add_category);
        }
    }
}
