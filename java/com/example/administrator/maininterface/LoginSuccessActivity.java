package com.example.administrator.maininterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.currentThread().sleep(2000);//阻断2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Utils.gotoActivity(LoginSuccessActivity.this, MyInfo.class,
                false, null);
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
    }
}
