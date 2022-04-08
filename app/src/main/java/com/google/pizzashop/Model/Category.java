package com.google.pizzashop.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Category extends ResCategory {
    public int mId;
    private String mName;
    private String mKey;

    public Category() {
    }

    public Category(int mId , String mName) {
        this.mId = mId;
        this.mName = mName;
    }


    @Override
    public int getmId() {
        return mId;
    }

    @Override
    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    @Exclude
    public String getmKey() {
        return mKey;
    }
    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

    // cau truc hien thi tot nhat
    public Map<String, Object> toMap() {
        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("mName" , mName);

        return mapResult;
    }

}
