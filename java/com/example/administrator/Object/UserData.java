package com.example.administrator.Object;

/**
 * Created by Administrator on 2018/12/21.
 */

public class UserData {
    private static String username;
    private static String password;
    private static String telnum;
    private static double yuE;
    private static int jinBi;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserData.password = password;
    }

    public static String getTelnum() {
        return telnum;
    }

    public static void setTelnum(String telnum) {
        UserData.telnum = telnum;
    }

    public static double getYuE() {
        return yuE;
    }

    public static void setYuE(double yuE) {
        UserData.yuE = yuE;
    }

    public static int getJinBi() {
        return jinBi;
    }

    public static void setJinBi(int jinBi) {
        UserData.jinBi = jinBi;
    }

    public static void setNull() {
        UserData.username = null;
        UserData.password = null;
        UserData.telnum = null;
        UserData.yuE = 0.00;
        UserData.jinBi = 0;
    }
}
