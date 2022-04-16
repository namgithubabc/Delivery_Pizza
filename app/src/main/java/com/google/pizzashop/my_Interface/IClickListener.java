package com.google.pizzashop.my_Interface;

import com.google.pizzashop.Model.Category;

public interface IClickListener {
    void onClickItemCategoryAdd(Category category, int position);
    void onClickItemCategoryUpdate(Category category, int position);
    void onClickItemCategoryDelete(Category category, int position);
    void onClickItemCategoryWatch(Category category, int position);
}
