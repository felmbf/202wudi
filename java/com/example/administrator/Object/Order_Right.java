package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/13.
 */

public class Order_Right {
    private int imageId;
    private String foodName;
    private String foodType;
    private double price;
    private int salesCount;
    private int selectedCount = 0;

    public Order_Right(int imageId, String foodName, String foodType, double price, int salesCount) {
        this.imageId = imageId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.price = price;
        this.salesCount = salesCount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }
}
