package com.google.pizzashop.Model;

public class ResCategory {
    int mId;
    String mImageUrl;
    String mTitleCategory;
    String mDistance;
    String mTime;

    public ResCategory() {
    }

    public ResCategory(int mId , String mImageUrl , String mTitleCategory , String mDistance , String mTime) {
        this.mId = mId;
        this.mImageUrl = mImageUrl;
        this.mTitleCategory = mTitleCategory;
        this.mDistance = mDistance;
        this.mTime = mTime;
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
}
