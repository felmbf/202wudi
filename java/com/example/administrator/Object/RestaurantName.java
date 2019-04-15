package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/16.
 */

public class RestaurantName {
    private static String restaurantName;

    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
        RestaurantName.restaurantName = restaurantName;
    }
}
