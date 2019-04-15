package com.example.administrator.maininterface;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Object.UserData;

import java.text.DecimalFormat;

public class MyInfo extends AppCompatActivity {

    public static MyInfo myInfo = null;

    private LinearLayout Waimai;
    private LinearLayout Dingdan;
    private LinearLayout Faxian;

    private LinearLayout Info;
    private LinearLayout Yu_e;
    private LinearLayout Hone_bao;
    private LinearLayout Jin_bi;

    private TextView userName;
    private TextView userTel;
    private TextView useryuE;
    private TextView userjinBi;
    private ImageView sheZhi;

    //第一次点击事件发生的时间
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        myInfo = this;

        initView();
        Click();

        if (UserData.getUsername() != null) {
            userName.setText(UserData.getUsername());
            userTel.setText("手机号：" + UserData.getTelnum());
            DecimalFormat df = new DecimalFormat("#.00");
            useryuE.setText(df.format(UserData.getYuE()) + "元");
            userjinBi.setText(UserData.getJinBi() + "个");
        } else {
            userName.setText("请登录");
            userTel.setText("");
            useryuE.setText(0.00 + "元");
        }
    }

    public void initView() {
        Waimai = (LinearLayout) findViewById(R.id.waimai);
        Dingdan = (LinearLayout) findViewById(R.id.dingdan);
        Faxian = (LinearLayout) findViewById(R.id.faxian);
        sheZhi = (ImageView) findViewById(R.id.set_up);

        Info = (LinearLayout) findViewById(R.id._Info);
        Yu_e = (LinearLayout) findViewById(R.id.yu_e);
        Hone_bao = (LinearLayout) findViewById(R.id.hong_bao);
        Jin_bi = (LinearLayout) findViewById(R.id.jin_bi);

        userName = (TextView) findViewById(R.id.user_name);
        userTel = (TextView) findViewById(R.id.user_tel);
        useryuE = (TextView) findViewById(R.id.user_yuE);
        userjinBi = (TextView) findViewById(R.id.user_jinBi);
    }

    public void Click() {
        //进入外卖界面
        Waimai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfo.this, MainInterface.class);
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
                Intent intent = new Intent(MyInfo.this, Order.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        //进入发现界面
        Faxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfo.this, FaXian.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        
        //进入设置界面T
        sheZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfo.this, SheZhi.class);
                startActivity(intent);
            }
        });

        //登录
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getUsername() == null) {
                    Intent intent = new Intent(MyInfo.this, Info.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MyInfo.this, "您已登录，若要更换账号，请先退出登录。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Yu_e.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Yu_e.setBackgroundColor(Color.parseColor("#EEEED1"));
                return false;
            }
        });
        Yu_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Yu_e.setBackgroundColor(Color.parseColor("#ffffff"));
                Intent intent = new Intent(MyInfo.this, My_Wallet.class);
                startActivity(intent);
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
