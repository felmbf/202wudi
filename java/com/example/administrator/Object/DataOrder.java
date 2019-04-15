package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/21.
 */

public class DataOrder {

    private String date;
    private String neirong;
    private String O_rest;
    private double O_totalPrice;
    private int imageId;

    public DataOrder(String date, String neirong, String o_rest, double o_totalPrice, int imageId) {
        this.date = date;
        this.neirong = neirong;
        O_rest = o_rest;
        O_totalPrice = o_totalPrice;
        this.imageId = imageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getO_rest() {
        return O_rest;
    }

    public void setO_rest(String o_rest) {
        O_rest = o_rest;
    }

    public double getO_totalPrice() {
        return O_totalPrice;
    }

    public void setO_totalPrice(double o_totalPrice) {
        O_totalPrice = o_totalPrice;
    }
}
