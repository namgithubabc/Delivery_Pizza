package com.google.pizzashop.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.pizzashop.Fragment.Taborder.ActiveTabHistory;
import com.google.pizzashop.Fragment.Taborder.ActiveTabOrder;

public class tabAdapter extends FragmentStatePagerAdapter {
    public tabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public tabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ActiveTabOrder();
            case 1:
                return new ActiveTabHistory();
            default:
                return new ActiveTabOrder();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Order";
            case 1:
                title = "History";

        }
        return super.getPageTitle(position);
    }
}
