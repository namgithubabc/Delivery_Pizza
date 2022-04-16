package com.google.pizzashop.Model;

public class OptionCategory extends Category {
    private int mId;
    private String mTitle;

    public OptionCategory() {
    }


    public OptionCategory(String mTitle) {
        this.mTitle = mTitle;
    }

    public OptionCategory(int mId , String mTitle) {
        this.mId = mId;
        this.mTitle = mTitle;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
