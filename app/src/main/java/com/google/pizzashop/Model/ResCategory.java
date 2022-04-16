package com.google.pizzashop.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ResCategory {
    public int mId;
    String mImageUrl;
    String mTitleCategory;
    String mDistance;
    String mTime;
    String mSale;
    String mKey;

    public ResCategory() {
    }

    public ResCategory(int mId , String mImageUrl , String mTitleCategory , String mDistance , String mTime , String mSale) {
        this.mId = mId;
        this.mImageUrl = mImageUrl;
        this.mTitleCategory = mTitleCategory;
        this.mDistance = mDistance;
        this.mTime = mTime;
        this.mSale = mSale;
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmTitleCategory() {
        return mTitleCategory;
    }

    public void setmTitleCategory(String mTitleCategory) {
        this.mTitleCategory = mTitleCategory;
    }

    public String getmDistance() {
        return mDistance;
    }

    public void setmDistance(String mDistance) {
        this.mDistance = mDistance;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmSale() {
        return mSale;
    }

    public void setmSale(String mSale) {
        this.mSale = mSale;
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
        mapResult.put("mImageUrl" , mImageUrl);
        mapResult.put("mTitleCategory" , mTitleCategory);
        mapResult.put("mDistance" , mDistance);
        mapResult.put("mTime" , mTime);
        mapResult.put("mSale" , mSale);

        return mapResult;
    }
}
