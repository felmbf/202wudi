package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/14.
 */

public class ToTalCount {
    private static int totalCount = 0;

    public static int getTotalCount() {
        return totalCount;
    }

    public static void setTotalCount(int totalCount) {
        ToTalCount.totalCount = totalCount;
    }
}
