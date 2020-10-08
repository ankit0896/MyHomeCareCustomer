package com.example.myhomecare.model;

public class FashionModel {
    int id;
    int image;
    float ratingNumber;
    boolean favorite;
    String brandName;
    String productName;
    int price;
    float offPrice;


    public FashionModel(int id, int image, float ratingNumber, boolean favorite, String brandName, String productName, int price, float offPrice) {
        this.id = id;
        this.image = image;
        this.ratingNumber = ratingNumber;
        this.favorite = favorite;
        this.brandName = brandName;
        this.productName = productName;
        this.price = price;
        this.offPrice = offPrice;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getOffPrice() {
        return offPrice;
    }

    public void setOffPrice(float offPrice) {
        this.offPrice = offPrice;
    }
}
