package com.google.pizzashop.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.pizzashop.AminActivity.Add.AddCategory;
import com.google.pizzashop.AminActivity.Add.AddRestaurants;
import com.google.pizzashop.R;


public class HomeFragment extends Fragment {
    private View mView;

    private LinearLayout CategoryAdmin,ProductAdmin,OrderAdmin, UserAdmin, SaleAdmin, ThongKeAdmin;
    private TextView totalUser,totalCategory,totalProduct;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home , container , false);
        initWeight();
        setListener();
        return mView;
    }

    private void setListener() {
        CategoryAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowDiaLogOptionCategory();
            }
        });
        ProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    public void  ShowDiaLogOptionCategory(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("Section Data");
        dialog.setCancelable(true); // tuy chinh khi nhan ra ngoai dialog se cho tat hoac khong cho tat
        dialog.setContentView(R.layout.dialog_option_category_and_restaurants); // setContentView la dung de set cai dialog_layout cho thang dialog
        Button btnCategoryFood =  (Button) dialog.findViewById(R.id.btnRectCategoryFood);
        Button btnRestaurant = (Button)  dialog.findViewById(R.id.btnRectRestaurant);

        btnCategoryFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCategory = new Intent(getActivity(), AddCategory.class);
                startActivity(intentCategory);
            }
        });

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRes = new Intent(getActivity(), AddRestaurants.class);
                startActivity(intentRes);
            }
        });

        dialog.show();
    }

    private void initWeight() {
        CategoryAdmin = mView.findViewById(R.id.add_category);
        ProductAdmin = mView.findViewById(R.id.add_product);
        OrderAdmin = mView.findViewById(R.id.add_order);
        SaleAdmin = mView.findViewById(R.id.add_sale);
        ThongKeAdmin = mView.findViewById(R.id.add_statistical);
        UserAdmin = mView.findViewById(R.id.add_user);
    }
}