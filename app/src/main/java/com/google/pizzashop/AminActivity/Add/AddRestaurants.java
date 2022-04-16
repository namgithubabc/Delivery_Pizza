package com.google.pizzashop.AminActivity.Add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.Model.ResCategory;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

public class AddRestaurants extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private EditText editTextRestaurantsId, editTextRestaurantsTitle,editTextRestaurantsDistance, editTextRestaurantsTime, editTextRestaurantsSale;
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
        btnAddRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataRestaurants();
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void AddDataRestaurants() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRefRestaurants.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));


            fileReference.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        String id_restaurant = editTextRestaurantsId.getText().toString();
                        String name_restaurant = editTextRestaurantsTitle.getText().toString();
                        String distance_restaurant = editTextRestaurantsDistance.getText().toString();
                        String time_restaurant = editTextRestaurantsTime.getText().toString();
                        String sale_restaurant = editTextRestaurantsSale.getText().toString();
                        String image_restaurants = downloadUri.toString();
                        ResCategory res = new ResCategory(Integer.parseInt(id_restaurant),image_restaurants,name_restaurant,distance_restaurant,time_restaurant,sale_restaurant);


                        mDatabaseRefRestaurants.child(String.valueOf(res.mId)).setValue(res , new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error , @NonNull DatabaseReference ref) {
                                Toast.makeText(getApplicationContext() , "Successfully" , Toast.LENGTH_SHORT).show();
                                editTextRestaurantsId.setText("");
                                editTextRestaurantsTitle.setText("");
                                editTextRestaurantsDistance.setText("");
                                editTextRestaurantsTime.setText("");
                                editTextRestaurantsSale.setText("");
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            Toast.makeText(this, "No file image selected", Toast.LENGTH_SHORT).show();
        }
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
        editTextRestaurantsSale = findViewById(R.id.editSale);
        imgChooseRestaurants = findViewById(R.id.img_choose_restaurant);
        btnAddRestaurants = findViewById(R.id.btn_add_restaurants);
        mStorageRefRestaurants = FirebaseStorage.getInstance().getReference("restaurants");
        mDatabaseRefRestaurants = FirebaseDatabase.getInstance().getReference("restaurants");
    }
}