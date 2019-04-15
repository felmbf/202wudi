package com.example.administrator.maininterface;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Object.UserData;

public class SheZhi extends AppCompatActivity {

    private ImageView Back;
    private TextView LogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);

        initView();
        Click();
    }

    private void initView() {
        Back = (ImageView) findViewById(R.id.sz_back);
        LogOut = (TextView) findViewById(R.id.logout);
    }

    private void Click() {

        //返回我的信息界面
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //退出登录
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getUsername() != null) {
                    UserData.setNull();
                    Intent intent = new Intent(SheZhi.this, MyInfo.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SheZhi.this, "您还未登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
