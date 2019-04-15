package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/14.
 */

public class Cart {
    private String foodName;
    private double foodPrice;
    private double totalPrice;
    private int foodCount;
    private int totalCount = 0;

    public Cart(String foodName, double foodPrice, int foodCount) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCount = foodCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
