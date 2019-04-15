package com.example.administrator.maininterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FaXian extends AppCompatActivity {

    private LinearLayout Waimai;
    private LinearLayout Dingdan;
    private LinearLayout Wode;

    //第一次点击事件发生的时间
    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_xian);

        initViews();
        Click();
    }

    public void initViews() {
        Wode = (LinearLayout) findViewById(R.id.wode);
        Dingdan = (LinearLayout) findViewById(R.id.dingdan);
        Waimai = (LinearLayout) findViewById(R.id.waimai);
    }

    public void Click() {

        //进入我的信息界面
        Wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaXian.this, MyInfo.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //进入外卖界面
        Waimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaXian.this, MainInterface.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //进入订单界面
        Dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaXian.this, Order.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    //提示退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
