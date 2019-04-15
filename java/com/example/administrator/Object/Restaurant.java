package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/11.
 */

public class Restaurant {
    private String name;
    private int imageId;
    //private String IconUrl;
    private int salesCount;

    /*public Restaurant(String name, int salesCount, String iconUrl) {
        this.name = name;
        this.salesCount = salesCount;
        IconUrl = iconUrl;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    /*public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.IconUrl = iconUrl;
    }*/

    public Restaurant(String name, int salesCount) {
        this.name = name;
        this.salesCount = salesCount;
    }
}
