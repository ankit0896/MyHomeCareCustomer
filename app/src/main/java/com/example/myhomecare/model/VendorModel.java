package com.example.myhomecare.model;

public class VendorModel {
    int id;
    int price;
    int offerPrice;
    String name;

    public VendorModel(int id, int price, int offerPrice, String name) {
        this.id = id;
        this.price = price;
        this.offerPrice = offerPrice;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
