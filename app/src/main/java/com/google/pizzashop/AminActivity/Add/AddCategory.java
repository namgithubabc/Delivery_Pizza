package com.google.pizzashop.AminActivity.Add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.pizzashop.Model.Category;
import com.google.pizzashop.R;

public class AddCategory extends AppCompatActivity {
    private EditText edtAddIdCategory, edtAddNameCategory, edtAddDescriptionCategory;
    private Button btnAddCategory;
    private DatabaseReference mRef;
    private StorageReference mStorage;
    private Uri mUri;
    private StorageTask mUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        initUi();
        setOnclickAddListener();
    }

    private void setOnclickAddListener() {
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataCategory();
            }
        });
    }

    private void AddDataCategory() {
        mRef = FirebaseDatabase.getInstance().getReference("category");
        String id_category = edtAddIdCategory.getText().toString();
        String name_category = edtAddNameCategory.getText().toString();
        Category category = new Category(Integer.parseInt(id_category),name_category);

        mRef.child(String.valueOf(category.mId)).setValue(category , new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error , @NonNull DatabaseReference ref) {
                Toast.makeText(AddCategory.this , "Add Successfully" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUi() {
        edtAddIdCategory = findViewById(R.id.editIdNameCategory);
        edtAddNameCategory = findViewById(R.id.editTextNameCategory);
        edtAddDescriptionCategory = findViewById(R.id.editTextDescriptionCategory);
        btnAddCategory = findViewById(R.id.btn_add_category);
    }
}