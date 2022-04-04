package com.google.pizzashop.AminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.pizzashop.Fragment.CategoryFragment;
import com.google.pizzashop.Fragment.HomeFragment;
import com.google.pizzashop.Fragment.OrderFragment;
import com.google.pizzashop.Fragment.ProductFragment;
import com.google.pizzashop.Fragment.SaleFragment;
import com.google.pizzashop.Fragment.ThongKeFragment;
import com.google.pizzashop.Fragment.UserFragment;
import com.google.pizzashop.R;

public class CategoryAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final  int FRAGMENT_HOME = 0;
    private static final  int FRAGMENT_FAVORITE = 1;
    private static final  int FRAGMENT_HISTORY = 2;
    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout mdrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Add toolBar
        setSupportActionBar(toolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //tao doi tuong click mo va dong navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mdrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        //bat su kien click cho navigation vi minh da implements thu vien navigation phia tren nen duoi nay chung ta se truyen this vao
        navigationView.setNavigationItemSelectedListener(this);

        // khi bat dau vao app  thi minh se checked HomeFragment
        replace(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(mCurrentFragment != FRAGMENT_HOME){
                replace(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }
        else if(id == R.id.nav_favorite){
            if(mCurrentFragment != FRAGMENT_FAVORITE){
                replace(new CategoryFragment());
                mCurrentFragment = FRAGMENT_FAVORITE;
            }
        }
        else if(id == R.id.nav_history){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replace(new ProductFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if(id == R.id.nav_order){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replace(new OrderFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if(id == R.id.nav_sale){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replace(new SaleFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if(id == R.id.nav_user){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replace(new UserFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        else if(id == R.id.nav_thong_ke){
            if(mCurrentFragment != FRAGMENT_HISTORY){
                replace(new ThongKeFragment());
                mCurrentFragment = FRAGMENT_HISTORY;
            }
        }
        //dong navigation
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}