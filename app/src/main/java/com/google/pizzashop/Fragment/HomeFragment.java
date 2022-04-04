package com.google.pizzashop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.pizzashop.AminActivity.AddCategory;
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
                Intent intentCategory = new Intent(getActivity(), AddCategory.class);
                startActivity(intentCategory);
            }
        });
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