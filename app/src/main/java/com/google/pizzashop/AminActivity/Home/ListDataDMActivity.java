package com.google.pizzashop.AminActivity.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.pizzashop.Adapter.OptionListSetCategory;
import com.google.pizzashop.AminActivity.Add.AddCategory;
import com.google.pizzashop.AminActivity.Delete.DeleteCategory;
import com.google.pizzashop.AminActivity.Thong_Ke.ThongKeActivity;
import com.google.pizzashop.AminActivity.Update.UpdateCategory;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.Model.OptionCategory;
import com.google.pizzashop.R;
import com.google.pizzashop.my_Interface.IClickListener;

import java.util.ArrayList;
import java.util.List;

public class ListDataDMActivity extends AppCompatActivity{
    private RecyclerView rcvSetUpCategory;
    private OptionListSetCategory optionListSetCategoryAdapter;
    private List<OptionCategory>  mListCategorySetUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_dmactivity);
        initWeight();
        ControlData();
    }

    private void ControlData() {
        //khoi tao user Adapter
        optionListSetCategoryAdapter = new OptionListSetCategory(mListCategorySetUp , new IClickListener() {
            @Override
            public void onClickItemCategoryAdd(Category category , int position) {
                onclickGoToAdd(category);
            }

            @Override
            public void onClickItemCategoryUpdate(Category category , int position) {

            }

            @Override
            public void onClickItemCategoryDelete(Category category , int position) {

            }

            @Override
            public void onClickItemCategoryWatch(Category category , int position) {

            }
        });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL , false);
        rcvSetUpCategory.setLayoutManager(linearLayoutManager);
        rcvSetUpCategory.setFocusable(false);

        optionListSetCategoryAdapter.setData(getDataCategory());
        rcvSetUpCategory.setAdapter(optionListSetCategoryAdapter);
    }

    private List<OptionCategory> getDataCategory() {
        List<OptionCategory> mListCategorySetUp = new ArrayList<>();
        mListCategorySetUp.add(new OptionCategory("Add Category"));
        mListCategorySetUp.add(new OptionCategory("Update Category"));
        mListCategorySetUp.add(new OptionCategory("Delete Category"));
        mListCategorySetUp.add(new OptionCategory("Watch All"));

        return mListCategorySetUp;
    }

    private void initWeight() {
        rcvSetUpCategory = findViewById(R.id.rcv_option_edit_data);
    }

    private void onclickGoToAdd (Category category) {
        Dialog dialog = new Dialog(ListDataDMActivity.this);
        dialog.setTitle("Dialog");
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_option_category_and_restaurants);
        Button btnFood =  (Button) dialog.findViewById(R.id.btnRectCategoryFood);
        Button btnRestaurant = (Button)  dialog.findViewById(R.id.btnRectRestaurant);

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCategory.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddCategory.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void onclickGoToUpdate (Category category) {
        List<OptionCategory> mListCategorySetUp = new ArrayList<>();
        Intent intent = new Intent(getApplicationContext() , UpdateCategory.class);
        startActivity(intent);
    }
    private void onclickGoToDelete (Category category) {
        List<OptionCategory> mListCategorySetUp = new ArrayList<>();
        Intent intent = new Intent(getApplicationContext() , AddCategory.class);
        startActivity(intent);
    }
    private void onclickGoToWatch (Category category) {
        List<OptionCategory> mListCategorySetUp = new ArrayList<>();
        Intent intent = new Intent(getApplicationContext() , AddCategory.class);
        startActivity(intent);
    }
}