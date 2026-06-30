package com.mobile_course.models;

public class Product {
    int pCode;
    String pName;
    Double pPrice;

    public Product(int pCode, String pName, Double pPrice) {
        this.pCode = pCode;
        this.pName = pName;
        this.pPrice = pPrice;
    }

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
