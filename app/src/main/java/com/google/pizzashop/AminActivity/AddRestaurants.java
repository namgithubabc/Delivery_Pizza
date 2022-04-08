package com.google.pizzashop.AminActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

public class AddRestaurants extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private EditText editTextRestaurantsId, editTextRestaurantsTitle,editTextRestaurantsDistance, editTextRestaurantsTime;
    private ImageView imgChooseRestaurants;
    private Button btnAddRestaurants;
    private Uri mImageUri;
    private StorageReference mStorageRefRestaurants;
    private DatabaseReference mDatabaseRefRestaurants;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurants);
        initWeight();
        setListener();
    }

    private void setListener() {
        imgChooseRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageDevice();
            }
        });
    }

    private void openImageDevice() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(imgChooseRestaurants);
        }
    }

    private void initWeight() {

        editTextRestaurantsId = findViewById(R.id.editIdRestaurants);
        editTextRestaurantsTitle = findViewById(R.id.editTitleNameRestaurant);
        editTextRestaurantsDistance = findViewById(R.id.editDistanceRestaurant);
        editTextRestaurantsTime = findViewById(R.id.editTimeRestaurant);
        imgChooseRestaurants = findViewById(R.id.img_choose_restaurant);
        btnAddRestaurants = findViewById(R.id.btn_add_restaurants);

        mStorageRefRestaurants = FirebaseStorage.getInstance().getReference("restaurants");
        mDatabaseRefRestaurants = FirebaseDatabase.getInstance().getReference("restaurants");
    }
}