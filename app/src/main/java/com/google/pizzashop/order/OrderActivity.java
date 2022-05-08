package com.google.pizzashop.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.pizzashop.Adapter.ViewPagerAdapter;
import com.google.pizzashop.R;

public class OrderActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPagers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tabLayout = findViewById(R.id.tabLayout);
        viewPagers = findViewById(R.id.viewPagers);

        ViewPagerAdapter viewPager = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagers.setAdapter(viewPager);
        tabLayout.setupWithViewPager(viewPagers);

    }
}