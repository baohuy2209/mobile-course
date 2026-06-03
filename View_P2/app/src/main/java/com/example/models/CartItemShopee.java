package com.example.models;

public class CartItemShopee {
    private int id;
    private String productName;
    private String variant;       // VD: "Màu: Trắng | Size: L"
    private double price;
    private double originalPrice; // Giá gốc (để gạch ngang nếu có sale)
    private int quantity;
    private int imageResId;       // R.drawable.xxx
    private boolean isChecked;

    public CartItemShopee(int id, String productName, String variant,
                    double price, double originalPrice,
                    int quantity, int imageResId) {
        this.id = id;
        this.productName = productName;
        this.variant = variant;
        this.price = price;
        this.originalPrice = originalPrice;
        this.quantity = quantity;
        this.imageResId = imageResId;
        this.isChecked = true;
    }

    // Getters
    public int getId() { return id; }
    public String getProductName() { return productName; }
    public String getVariant() { return variant; }
    public double getPrice() { return price; }
    public double getOriginalPrice() { return originalPrice; }
    public int getQuantity() { return quantity; }
    public int getImageResId() { return imageResId; }
    public boolean isChecked() { return isChecked; }

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setChecked(boolean checked) { isChecked = checked; }

    public boolean isOnSale() {
        return originalPrice > price;
    }

    public double getSubtotal() {
        return price * quantity;
    }

}
