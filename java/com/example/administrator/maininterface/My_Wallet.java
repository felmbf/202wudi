package com.example.administrator.maininterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class My_Wallet extends AppCompatActivity {

    private TextView _Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__wallet);

        //MyInfo.myInfo.Reply();

        _Exit = (TextView) findViewById(R.id._exit);

        _Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Wallet.this, MyInfo.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
