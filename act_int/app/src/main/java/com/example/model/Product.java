package com.example.model;

import java.io.Serializable;

public class Product implements Serializable {
    int pCode;
    String pName;
    Double pPrice;
    // Constructor

    public Product(int pCode, String pName, Double pPrice) {
        this.pCode = pCode;
        this.pName = pName;
        this.pPrice = pPrice;
    }
    // Getter and setter

    public int getpCode() {
        return pCode;
    }

    public void setpCode(int pCode) {
        this.pCode = pCode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }
}
