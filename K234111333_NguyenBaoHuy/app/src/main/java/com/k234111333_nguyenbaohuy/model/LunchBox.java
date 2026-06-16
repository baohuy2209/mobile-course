package com.k234111333_nguyenbaohuy.model;
//"placeName": "Cơm Tấm Đại Đồng",
//        "dishName": "Cơm Sườn",
//        "photo": "lunch_com_tam_dai_dong",
//        "ratingValue": 4.1,
//        "ratingCount": "100+",
//        "address": "38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM"
public class LunchBox {
    String placeName;
    String dishName;
    int photo;
    Double ratingValue;
    String ratingCount;
    String address;

    public LunchBox(String placeName, String dishName, int photo, Double ratingValue, String ratingCount, String address) {
        this.placeName = placeName;
        this.dishName = dishName;
        this.photo = photo;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.address = address;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getDishName() {
        return dishName;
    }

    public int getPhoto() {
        return photo;
    }

    public Double getRatingValue() {
        return ratingValue;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String getAddress() {
        return address;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setRatingValue(Double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
