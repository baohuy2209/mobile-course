package com.nguyenbaohuy.models;

public class Favorite {
    int id;
    int receipt_id;
    String date;

    public Favorite(int id, int receipt_id, String date) {
        this.id = id;
        this.receipt_id = receipt_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(int receipt_id) {
        this.receipt_id = receipt_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
