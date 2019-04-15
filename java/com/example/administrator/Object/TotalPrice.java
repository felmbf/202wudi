package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/14.
 */

public class TotalPrice {
    private static double totalPrice = 0;

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(double totalPrice) {
        TotalPrice.totalPrice = totalPrice;
    }
}
