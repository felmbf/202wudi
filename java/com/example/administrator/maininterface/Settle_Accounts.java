package com.example.administrator.maininterface;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Adapter.SettleAdapter;
import com.example.administrator.Object.CartList;
import com.example.administrator.Object.SettleMent;
import com.example.administrator.Object.Temp;
import com.example.administrator.Object.TotalPrice;
import com.example.administrator.Object.UserData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Settle_Accounts extends AppCompatActivity {

    private ListView SettlelistView;
    private List<SettleMent> settleMentList = new ArrayList<>();
    private SettleAdapter settleAdapter;

    private TextView totalPrice1;
    private TextView totalPrice2;
    private ImageView Back;
    private TextView Pay;
    private CheckBox is_jinBi;

    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle_accounts);

        initView();
        initData();
        Settlement_Adapter();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) SettlelistView.getLayoutParams();
        params.height = CartList.getCartList().size() * 180;
        SettlelistView.setLayoutParams(params);

        Click();
    }

    public void initView() {
        totalPrice1 = (TextView) findViewById(R.id.order_totalPrice);
        totalPrice2 = (TextView) findViewById(R.id.order_totalPrice_2);
        Back = (ImageView) findViewById(R.id.back_cart);
        Pay = (TextView) findViewById(R.id.pay);
        is_jinBi = (CheckBox) findViewById(R.id.is_jinBi);
    }

    public void initData() {
        for (int i = 0; i < CartList.getCartList().size(); i++) {
            SettleMent settleMent = new SettleMent(R.drawable.rest_1,
                    CartList.getCartList().get(i).getFoodName(),
                    CartList.getCartList().get(i).getFoodCount(),
                    CartList.getCartList().get(i).getFoodPrice());
            settleMentList.add(settleMent);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        totalPrice1.setText("¥" + df.format(TotalPrice.getTotalPrice()));
        totalPrice2.setText("¥" + df.format(TotalPrice.getTotalPrice()));
    }

    public void Settlement_Adapter() {
        settleAdapter = new SettleAdapter(this, R.layout.order_item, settleMentList);
        SettlelistView = (ListView) findViewById(R.id.order_fInfo);
        SettlelistView.setAdapter(settleAdapter);
    }

    private void Click() {
        //返回点餐页面
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //选择是否使用金币
        is_jinBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_jinBi.isChecked()) {
                    if (UserData.getJinBi() >= 1000) {
                        TotalPrice.setTotalPrice(TotalPrice.getTotalPrice() - 10);
                        Temp.setReduce_jinBi(1000);
                    }
                    else if (UserData.getJinBi() >= 100 && UserData.getJinBi() < 1000) {
                        TotalPrice.setTotalPrice(TotalPrice.getTotalPrice() -
                                UserData.getJinBi() / 100);
                        Temp.setReduce_jinBi(UserData.getJinBi() / 100 * 100);
                    }
                    else if (UserData.getJinBi() < 100) {
                        Toast.makeText(Settle_Accounts.this, "金币不足！",
                                Toast.LENGTH_SHORT).show();
                        Temp.setReduce_jinBi(0);
                    }
                }
                else {
                    if (Temp.getReduce_jinBi() != 0) {
                        if (Temp.getReduce_jinBi() == 1000) {
                            TotalPrice.setTotalPrice(TotalPrice.getTotalPrice() + 10);
                            Temp.setReduce_jinBi(0);
                        }
                        else {
                            TotalPrice.setTotalPrice(TotalPrice.getTotalPrice() +
                                    UserData.getJinBi() / 100);
                            Temp.setReduce_jinBi(0);
                        }
                    }
                }
                DecimalFormat df = new DecimalFormat("#.00");
                totalPrice1.setText("¥" + df.format(TotalPrice.getTotalPrice()));
                totalPrice2.setText("¥" + df.format(TotalPrice.getTotalPrice()));
            }
        });

        //支付订单
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserData.getUsername() == null) {
                    Toast.makeText(Settle_Accounts.this, "请先登录！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (UserData.getYuE() < TotalPrice.getTotalPrice()) {
                        Toast.makeText(Settle_Accounts.this, "余额不足！", Toast.LENGTH_SHORT).show();
                    } else {
                        UserData.setYuE(UserData.getYuE() - TotalPrice.getTotalPrice());
                        UserData.setJinBi(UserData.getJinBi() - Temp.getReduce_jinBi());
                        Intent intent = new Intent(Settle_Accounts.this, Waiting.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                    }
                }
            }
        });
    }
}
