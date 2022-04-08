package com.google.pizzashop.Fragment.tabHome;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.pizzashop.Adapter.CategoryHomeAdapter;
import com.google.pizzashop.Adapter.CategoryRestaurants;
import com.google.pizzashop.Adapter.ViewPagerAdapter;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.R;

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
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_bottom , container , false);
        initWeight();
        ControlCategory();
        return mView;
    }

    private void initWeight() {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mRecyclerView = mView.findViewById(R.id.rcv_category);
        mRecyclerViewRes = mView.findViewById(R.id.rcv_res_category);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
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
}