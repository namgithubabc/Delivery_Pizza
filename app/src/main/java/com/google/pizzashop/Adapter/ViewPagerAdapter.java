package com.google.pizzashop.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.pizzashop.Fragment.tabHome.CartFragmentBottom;
import com.google.pizzashop.Fragment.tabHome.HomeFragmentBottom;
import com.google.pizzashop.Fragment.tabHome.ProfileFragmentBottom;
import com.google.pizzashop.Fragment.tabHome.SearchFragmentBotom;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm , int behavior) {
        super(fm , behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragmentBottom();

            case 1:
                return new CartFragmentBottom();

            case 2:
                return new SearchFragmentBotom();

            case 3:
                return new ProfileFragmentBottom();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
