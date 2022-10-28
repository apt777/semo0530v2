package com.foodproject.model;

public class Feature {
    private String mProductName;
    private String mProductDescription;
    private String mProductRestaurant;
    private String mProductValue;
    private int mProductImages;


    public Feature(String mProductName, String mProductDescription, String mProductRestaurant, String mProductValue, int mProductImages) {
        this.mProductName = mProductName;
        this.mProductDescription = mProductDescription;
        this.mProductRestaurant = mProductRestaurant;
        this.mProductValue = mProductValue;
        this.mProductImages = mProductImages;
    }

    public String getmProductName() {
        return mProductName;
    }

    public String getmProductDescription() {
        return mProductDescription;
    }

    public int getmProductDrawable() { return mProductImages; }

    public String getmProductRestaurant() {
        return mProductRestaurant;
    }

    public String getmProductValue() {
        return mProductValue;
    }

}