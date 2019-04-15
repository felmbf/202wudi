package com.example.administrator.Object;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class SettleMent {
    private int imageId;
    private String fNmae;
    private int fCount;
    private double fPrice;

    public SettleMent(int imageId, String fNmae, int fCount, double fPrice) {
        this.imageId = imageId;
        this.fNmae = fNmae;
        this.fCount = fCount;
        this.fPrice = fPrice;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getfNmae() {
        return fNmae;
    }

    public void setfNmae(String fNmae) {
        this.fNmae = fNmae;
    }

    public int getfCount() {
        return fCount;
    }

    public void setfCount(int fCount) {
        this.fCount = fCount;
    }

    public double getfPrice() {
        return fPrice;
    }

    public void setfPrice(double fPrice) {
        this.fPrice = fPrice;
    }
}
