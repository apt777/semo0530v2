package com.foodproject.model;

public class Item {

    private int placeId;
    private String itemName;
    private String itemPrice;
    private int itemImages;

    public Item(String itemName, String itemPrice, int itemImages) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImages = itemImages;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemDrawable() { return itemImages; }

    public String getItemPrice() {
        return itemPrice;
    }
}
