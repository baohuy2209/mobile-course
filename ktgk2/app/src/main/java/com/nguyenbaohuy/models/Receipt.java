package com.nguyenbaohuy.models;

import java.sql.Blob;

public class Receipt {
    int id;
    String receipt_name;
    // Nguyen lieu
    String receipt_nat;
    String receipt_cl;
    String receipt_time;
    // do kho
    String level;
    byte[] receipt_image;
    int category_id;

    public Receipt(int id, String receipt_name, String receipt_nat, String receipt_cl, String receipt_time, String level, byte[] receipt_image, int category_id) {
        this.id = id;
        this.receipt_name = receipt_name;
        this.receipt_nat = receipt_nat;
        this.receipt_cl = receipt_cl;
        this.receipt_time = receipt_time;
        this.level = level;
        this.receipt_image = receipt_image;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getReceipt_nat() {
        return receipt_nat;
    }

    public void setReceipt_nat(String receipt_nat) {
        this.receipt_nat = receipt_nat;
    }

    public String getReceipt_cl() {
        return receipt_cl;
    }

    public void setReceipt_cl(String receipt_cl) {
        this.receipt_cl = receipt_cl;
    }

    public String getReceipt_time() {
        return receipt_time;
    }

    public void setReceipt_time(String receipt_time) {
        this.receipt_time = receipt_time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public byte[] getReceipt_image() {
        return receipt_image;
    }

    public void setReceipt_image(byte[] receipt_image) {
        this.receipt_image = receipt_image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
