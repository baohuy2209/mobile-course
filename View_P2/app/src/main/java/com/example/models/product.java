package com.example.models;

import androidx.annotation.NonNull;

public class product {
    String productName;
    Double productPrice;

    // Constructor

    public product(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    // Getter, Setter

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @NonNull
    @Override
    public String toString(){
        return this.productName + ": " + this.productPrice + "d";
    }
}
