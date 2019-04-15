package com.example.administrator.maininterface;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.Adapter.OrderAdapter;
import com.example.administrator.Object.DataOrder;
import com.example.administrator.Object.Restaurant;
import com.example.administrator.Object.UserData;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Order extends AppCompatActivity {

    private LinearLayout Wode;
    private LinearLayout Waimai;
    private LinearLayout Faxian;

    //第一次点击事件发生的时间
    private long mExitTime;
    private List<DataOrder> orderList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private String web = "http://192.168.43.13:8080/ELM/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        initData();

        SystemClock.sleep(400);

        recyclerView = (RecyclerView) findViewById(R.id.base_swipe_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (orderList.size() == 0) {
            Log.d("ddd", "为空");
        } else {
            Log.d("ddd", "不为空");
        }
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);


        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener () {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(Order.this, pinglun.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();*/

       Click();
    }

    public void initView() {
        Wode = (LinearLayout) findViewById(R.id.wode);
        Waimai = (LinearLayout) findViewById(R.id.waimai);
        Faxian = (LinearLayout) findViewById(R.id.faxian);
    }

    private void initData() {
        sendRequestWithOkHttp();
    }

    public void Click() {
        //进入我的信息界面
        Wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order.this, MyInfo.class);
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
                Intent intent = new Intent(Order.this, MainInterface.class);
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
                Intent intent = new Intent(Order.this, FaXian.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    //向电脑端服务器发送请求接收数据
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("user", UserData.getUsername())
                            .build();
                    //声明请求
                    Request request = new Request.Builder()
                            .url(web + "QueryDingdan_All")
                            .post(requestBody)
                            .build();

                    //客户端发起请求
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    removeBOM(responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //String O_user = jsonObject.getString("O_user");
                String O_restName = jsonObject.getString("O_restName");
                String O_food = jsonObject.getString("O_food");
                String O_date = jsonObject.getString("O_date");

                String s = jsonObject.getString("O_totalPrice");
                double O_totalPrice = new Double(s);

                DataOrder info = new DataOrder(O_date, O_food,
                        O_restName, O_totalPrice, R.drawable.rest_1);
                orderList.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
