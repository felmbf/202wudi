package com.example.administrator.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.administrator.Adapter.RestaurantAdapter;
import com.example.administrator.Object.RName;
import com.example.administrator.Object.RestaurantName;
import com.example.administrator.maininterface.Ordering_interface;
import com.example.administrator.maininterface.R;
import com.example.administrator.Object.Restaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/11.
 */

public class FoodType_Fragment1 extends Fragment {

    private List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantAdapter restaurantAdapter;
    //private int flag = 0;
    private String web = "http://192.168.43.13:8080/ELM/";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //餐家列表
        if (restaurantList.size() == 0) {
            initRestaurants();    //初始化商家数据
        }

        SystemClock.sleep(300);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_type1, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_rest);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(restaurantAdapter);

        //initRestIcon(container);

        restaurantAdapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                RestaurantName.setRestaurantName(restaurantList.get(position).getName());

                //获取当前点击的商家名称
                RName.setRName(restaurantList.get(position).getName());

                //进入点餐页面
                Intent intent = new Intent(getActivity(), Ordering_interface.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        //排序类型
        Spinner spinner = (Spinner) view.findViewById(R.id.Spinner_ZH);
        String[] mItems = getResources().getStringArray(R.array.sort_order);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TextView tv = new view;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //商家数据初始化函数
    private void initRestaurants() {
        sendRequestWithOkHttp();
    }

    //商家图标初始化函数
   /*public void initRestIcon(final ViewGroup container) {
        new Thread(new Runnable() {
            String url;
            Bitmap bitmap;
            @Override
            public void run() {
                for (int i = 0; i < restaurantList.size(); i++) {
                    url = restaurantList.get(i).getIconUrl();
                    bitmap = getImageBitmap(url);

                }
            }
        }).start();
    }*/

    public Bitmap getImageBitmap(String url) {
        URL imgUrl = null;
        Bitmap bitmap = null;
        try {
            imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }

    //向电脑端服务器发送请求接收数据
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    //声明请求
                    Request request = new Request.Builder()
                            .url(web + "QueryRest_All")
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
                String restName = jsonObject.getString("restName");
                //String restIconUrl = jsonObject.getString("restIconUrl");
                int salesCount = Integer.parseInt(jsonObject.getString("salesCount"));

                if (i == 0 && restaurantList.size() != 0) {
                    restaurantList.clear();
                }
                Restaurant demo = new Restaurant(restName, salesCount);
                restaurantList.add(demo);
                //Log.d("getActivity()", restName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
