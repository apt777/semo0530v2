package com.foodproject.model;

public class Favorit {
    private String mProductName;
    private String mProductDescription;
    private String mProductRestaurant;
    private String mProductValue;
    private int mProductImages;

    public Favorit(String mProductName, String mProductDescription, String mProductRestaurant, String mProductValue, int mProductImages) {
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

    public String getmProductRestaurant() {
        return mProductRestaurant;
    }

    public int getmProductDrawable() { return mProductImages;}

    public String getmProductValue() {
        return mProductValue;
    }

}
