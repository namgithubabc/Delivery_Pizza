package com.google.pizzashop.Fragment.tabHome;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.pizzashop.Activities.HomeActivity;
import com.google.pizzashop.Adapter.CategoryHomeAdapter;
import com.google.pizzashop.Adapter.CategoryRestaurants;
import com.google.pizzashop.Adapter.ViewPagerAdapter;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.Model.ResCategory;
import com.google.pizzashop.R;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;
import java.util.List;


public class HomeFragmentBottom extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewRes;
    private CategoryHomeAdapter mAdapter;
    private CategoryRestaurants mAdapterRes;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Category> mUploads;
    private List<ResCategory> mResUploads;
    private EditText searchHome;
    private HomeActivity homeActivity;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_bottom , container , false);
        initWeight();
        homeActivity = new HomeActivity();
        ControlCategory();
        ControlCategoryRestaurants();
        return mView;
    }


    private void initWeight() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mRecyclerView = mView.findViewById(R.id.rcv_category);
        mRecyclerViewRes = mView.findViewById(R.id.rcv_res_category);
        searchHome = mView.findViewById(R.id.search);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewRes.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerViewRes.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mRecyclerViewRes.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
    }



    private void ControlCategory(){
        mUploads = new ArrayList<>();
        mAdapter = new CategoryHomeAdapter(getActivity(), mUploads);
        mRecyclerView.setAdapter(mAdapter);


        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("category");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Category upload = postSnapshot.getValue(Category.class);
                    upload.setmKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ControlCategoryRestaurants() {
        mResUploads = new ArrayList<>();
        mAdapterRes = new CategoryRestaurants(getActivity(),mResUploads);
        mRecyclerViewRes.setAdapter(mAdapterRes);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("restaurants");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mResUploads.clear();

                for (DataSnapshot postSnapshot1 : snapshot.getChildren()) {
                    ResCategory res = postSnapshot1.getValue(ResCategory.class);
                    res.setmKey(postSnapshot1.getKey());
                    mResUploads.add(res);
                }

                mAdapterRes.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity() , error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}