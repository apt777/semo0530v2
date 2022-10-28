package com.foodproject.model;

public class Food {

    private String image;
    private String product;
    private Long price;
    private String location;

    public Food() {
    }
    public Food(String image, String product, Long price, String location) {
        this.image = image;
        this.product = product;
        this.price = price;
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
