package com.google.pizzashop.Activities;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.pizzashop.Adapter.ViewPagerAdapter;
import com.google.pizzashop.R;

public class HomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inintWeight();
        menuBottom();
    }



    private void menuBottom() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        //su kien click cua menu navigationBottom
        mBottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tabRate:
                        mViewPager.setCurrentItem(0); //set vi tri cua item thu i
                        break;

                    case R.id.tabFavorite:
                        mViewPager.setCurrentItem(1);
                        break;

                    case R.id.tabMyPage:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_my_profile:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        //su kien chuyen page phia tren
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position , float positionOffset , int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mBottomNavigationView.getMenu().findItem(R.id.tabRate);
                        break;
                    case 1:
                        mBottomNavigationView.getMenu().findItem(R.id.tabFavorite);
                        break;
                    case 2:
                        mBottomNavigationView.getMenu().findItem(R.id.tabMyPage);
                        break;
                    case 3:
                        mBottomNavigationView.getMenu().findItem(R.id.tab_my_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void inintWeight() {
        mViewPager = findViewById(R.id.viewPager);
        mBottomNavigationView = findViewById(R.id.bottomNavigation);
    }
}