package com.google.pizzashop.AminActivity.Update;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.pizzashop.AminActivity.Home.CategoryAdminActivity;
import com.google.pizzashop.Model.ResCategory;
import com.google.pizzashop.R;
import com.squareup.picasso.Picasso;

public class UpdateRestaurants extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText edtUpdateId, edtUpdateName, edtUpdateDistance,edtUpdateTime,edtUpdateSale;
    private ImageView imgUpdateChoose;
    private Button btnUpdate;
    private LinearLayout backHome;

    private Uri mImageUriUpdate;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_restaurants);
        initWeight();
        setListener();
    }

    private void setListener() {
        imgUpdateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseImageUpdate();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryAdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private void UpdateData() {

        mStorageRef = FirebaseStorage.getInstance().getReference("restaurants");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("restaurants");

        if (mImageUriUpdate != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUriUpdate));


            fileReference.putFile(mImageUriUpdate).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
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
                        String idUpdate = edtUpdateId.getText().toString();
                        String nameUpdate = edtUpdateName.getText().toString();
                        String distanceUpdate = edtUpdateDistance.getText().toString();
                        String timeUpdate = edtUpdateTime.getText().toString();
                        String saleUpdate = edtUpdateSale.getText().toString();
                        String imageUpdate = downloadUri.toString();
                        ResCategory update = new ResCategory(Integer.parseInt(idUpdate),imageUpdate,nameUpdate,distanceUpdate,timeUpdate,saleUpdate);

                        mDatabaseRef.child(String.valueOf(update.mId)).updateChildren(update.toMap());
                        Toast.makeText(UpdateRestaurants.this , "Update Successfully" , Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateRestaurants.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }

    }

    private void initWeight() {
        edtUpdateId = findViewById(R.id.editIdUpdateRestaurants);
        edtUpdateName = findViewById(R.id.editTitleNameUpdateRestaurant);
        edtUpdateDistance = findViewById(R.id.editDistanceUpdateRestaurant);
        edtUpdateTime = findViewById(R.id.editTimeUpdateRestaurant);
        edtUpdateSale  = findViewById(R.id.editUpdateSale);
        imgUpdateChoose = findViewById(R.id.img_choose_update_restaurant);
        btnUpdate = findViewById(R.id.btn_update_restaurants);
        backHome = findViewById(R.id.back_home);
    }

    private void ChooseImageUpdate() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUriUpdate = data.getData();

            Picasso.with(this).load(mImageUriUpdate).into(imgUpdateChoose);
        }
    }
}