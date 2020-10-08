package com.example.myhomecare.model;

public class WomensTopModel {
    int id;
    int image;
    String productName;
    String brandName;
    float ratingNumber;
    int price;
    int offerPrice;
    int offPrice;

    public WomensTopModel(int id, int image, String productName, String brandName, float ratingNumber, int price) {
        this.id = id;
        this.image = image;
        this.productName = productName;
        this.brandName = brandName;
        this.ratingNumber = ratingNumber;
        this.price = price;
    }

    public WomensTopModel(int id, int image, String productName, String brandName, float ratingNumber, int price, int offerPrice, int offPrice) {
        this.id = id;
        this.image = image;
        this.productName = productName;
        this.brandName = brandName;
        this.ratingNumber = ratingNumber;
        this.price = price;
        this.offerPrice = offerPrice;
        this.offPrice = offPrice;
    }

    public int getOffPrice() {
        return offPrice;
    }

    public void setOffPrice(int offPrice) {
        this.offPrice = offPrice;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public float getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(float ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
