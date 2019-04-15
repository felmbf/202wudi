package com.example.administrator.Object;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class CartList {
    private static List<Cart> cartList = new ArrayList<>();

    public static List<Cart> getCartList() {
        return cartList;
    }

    public static void setCartList(List<Cart> cartList) {
        CartList.cartList = cartList;
    }
}
