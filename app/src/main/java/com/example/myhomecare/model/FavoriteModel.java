package com.example.myhomecare.model;

public class FavoriteModel {
    int id;
    int image;
    float ratingNumber;
    String brandname;
    String productName;
    String color;
    String size;
    Integer price;
    float offerPer;
    boolean available;

    public FavoriteModel(int id, int image, float ratingNumber, String brandname, String productName, String color, String size, Integer price, float offerPer, boolean available) {
        this.id = id;
        this.image = image;
        this.ratingNumber = ratingNumber;
        this.brandname = brandname;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.price = price;
        this.offerPer = offerPer;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(float ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public float getOfferPer() {
        return offerPer;
    }

    public void setOfferPer(float offerPer) {
        this.offerPer = offerPer;
    }
}
