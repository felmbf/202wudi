package com.example.administrator.maininterface;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Object.Data;
import com.example.administrator.Object.ForRName;
import com.example.administrator.Object.RName;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QueryActivity extends AppCompatActivity {

    private ImageView Back;
    private EditText editText;
    private TextView search;
    private ImageView restIcon;
    private TextView restName;
    private TextView salesCount;
    private String rName = "";
    private int sCount;
    private String web = "http://192.168.43.13:8080/ELM/";
    private LinearLayout linear;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        //final Data data = (Data)getApplication();

        initView();
        Click();

    }

    public void initView() {
        Back = (ImageView) findViewById(R.id.back);
        editText = (EditText) findViewById(R.id.query_edit) ;
        search = (TextView) findViewById(R.id.search) ;
        restIcon = (ImageView) findViewById(R.id.rest_icon2);
        restName = (TextView) findViewById(R.id.rest_name2);
        linear = (LinearLayout) findViewById(R.id.queryResult);
        salesCount = (TextView) findViewById(R.id.rest_salesCount2);
    }

    public void Click() {
        //查询监听事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                String s = editText.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(QueryActivity.this, "请输入商家名称", Toast.LENGTH_SHORT).show();
                } else {
                    QueryFoodForRest(s);
                    /*while (!rName.equals(s) && i < 50) {
                        QueryFoodForRest(s);
                        Log.d("getActivity()", "读取数据错误");
                        i++;
                    }*/
                    SystemClock.sleep(300);
                    if (rName.equals(s)) {
                        linear.setVisibility(View.VISIBLE);
                        restIcon.setImageResource(R.drawable.rest_1);
                        restName.setText(rName);
                        salesCount.setText("已售 " + sCount);
                        rName = "";
                    } else {
                        Toast.makeText(QueryActivity.this, "抱歉找不到您要的信息呢", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent;
                switch (Data.getFlag()) {
                    case 1:
                        finish();
                        break;
                    case 2:
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

        //进入点餐页面
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RName.setRName(restName.getText().toString());
                Intent intent = new Intent(QueryActivity.this, Ordering_interface.class);
                startActivity(intent);
            }
        });
    }

    private void QueryFoodForRest(final String RName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    //上传RName
                    RequestBody requestBody = new FormBody.Builder()
                            .add("Name", RName).build();

                    Request request = new Request.Builder().url(web + "QueryRestForName")
                            .post(requestBody)
                            .build();
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
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                rName = jsonObject.getString("restName");
                sCount = Integer.parseInt(jsonObject.getString("salesCount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
