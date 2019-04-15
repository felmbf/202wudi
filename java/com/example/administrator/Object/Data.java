package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/11/15.
 */

public class Data   {
    private static int flag = 0;

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        Data.flag = flag;
    }

    /*@Override
    public void onCreate() {
        flag = 0;
        super.onCreate();
    }*/
}
