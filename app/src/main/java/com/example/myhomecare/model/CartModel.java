package com.example.myhomecare.model;

public class CartModel {
    int productId;
    String productName;
    String productBrandName;
    String shopName;
    String size;
    Float price;
    Float percentageOff;
    Float offerPrice;
    int quantity;
    String image;

    public CartModel() {
    }

    public CartModel(int productId, String productName, String productBrandName, String shopName, String size, Float price, Float percentageOff, Float offerPrice, int quantity, String image) {
        this.productId = productId;
        this.productName = productName;
        this.productBrandName = productBrandName;
        this.shopName = shopName;
        this.size = size;
        this.price = price;
        this.percentageOff = percentageOff;
        this.offerPrice = offerPrice;
        this.quantity = quantity;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPercentageOff() {
        return percentageOff;
    }

    public void setPercentageOff(Float percentageOff) {
        this.percentageOff = percentageOff;
    }

    public Float getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Float offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productBrandName='" + productBrandName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", percentageOff=" + percentageOff +
                ", offerPrice=" + offerPrice +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                '}';
    }
}
