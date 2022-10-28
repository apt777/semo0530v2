package com.foodproject.model;

public class Place {

    private int placeId;
    private String placeName;
    private String location;
    private String placeRating;
    private String placeDelivery;
    private int placeImages;
    private boolean favorite = false;

    public Place(int placeId, String placeName, String location, String placeRating, String placeDelivery,int placeImages) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.location = location;
        this.placeRating = placeRating;
        this.placeDelivery = placeDelivery;
        this.placeImages = placeImages;
    }

    public int getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getLocation() {
        return location;
    }

    public String getRating() {
        return placeRating;
    }

    public String getDelivery() {
        return placeDelivery;
    }

    public int getPlaceDrawable() {
        return placeImages;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
