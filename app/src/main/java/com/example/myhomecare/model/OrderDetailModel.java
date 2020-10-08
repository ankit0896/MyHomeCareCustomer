package com.example.myhomecare.model;

public class OrderDetailModel {
    int id;
    String orderId;
    String orderDate;
    String orderTrackingNumber;
    int orderQuantity;
    float orderTotalAmount;

    public OrderDetailModel(int id, String orderId, String orderDate, String orderTrackingNumber, int orderQuantity, float orderTotalAmount) {
        this.id = id;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTrackingNumber = orderTrackingNumber;
        this.orderQuantity = orderQuantity;
        this.orderTotalAmount = orderTotalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public float getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(float orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }
}
